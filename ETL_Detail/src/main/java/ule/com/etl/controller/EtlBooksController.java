package ule.com.etl.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSONObject;
import ule.com.etl.action.DeleteFile;
import ule.com.etl.model.*;
import ule.com.etl.service.EtlBooksService;

@Controller
public class EtlBooksController {
    private static Logger log = Logger.getLogger(EtlBooksController.class);
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    @Autowired
    private EtlBooksService etlBooksService;

    @RequestMapping(value = "etl/etlbooks")
    @ResponseBody
    public String etlBooksModel(HttpServletRequest request) {
        //  select all  OR  select by tableName
        String table_name = request.getParameter("table_name");
        String proc_name = request.getParameter("proc_name");
        String result_table = request.getParameter("result_table");
        List<EtlBooks> books = new ArrayList<EtlBooks>();
        if(table_name != null ){
            books = etlBooksService.selectEtlBooksByName(table_name);
        }else if( proc_name != null ){
            books = etlBooksService.selectEtlBooksByProcName(proc_name);
        }else if( result_table != null ){
            books = etlBooksService.selectEtlBooksByResultTable(result_table);
        } else {
            books = etlBooksService.selectEtlBooks();
        }
        JSONObject obj = new JSONObject();
        obj.put("Rows", books);
        obj.put("Total", books.size());
        String bString = obj.toJSONString();
        return bString;
    }

    @RequestMapping(value = "etl/update")
    @ResponseBody
    public ModelAndView update(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String id = request.getParameter("id");
        EtlBooks book = etlBooksService.selectEtlBook(id);
        mv.addObject("book", book);
        mv.setViewName("add");
        return mv;
    }

    @RequestMapping(value = "etl/add")
    public String add(HttpServletRequest request) {
        return "add";
    }

    @RequestMapping(value = "etl/delete")
    @ResponseBody
    public String delete(HttpServletRequest request) {
        String id = request.getParameter("id");
        etlBooksService.deleteEtlBook(id);
        return "1";
    }

    @RequestMapping(value = "etl/etlReplace")
    @ResponseBody
    public ModelAndView etlReplace(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        List<PRO_ETL_STATU> lists = etlBooksService.etlReplace();
        mv.addObject("lists", lists);
        mv.setViewName("etlReplace");
        return mv;
    }

    @RequestMapping(value = "etl/etlReplaceSubmit")
    @Transactional
    public String etlReplaceSubmit(HttpServletRequest request) {
        String times = request.getParameter("time");
        int day_time = 0;
        if (times != null || times != "") {
            day_time = Integer.parseInt(times);
        }
        String[] checkbox = request.getParameterValues("checkbox");
        for (int i = 0; i < checkbox.length; i++) {
            PRO_ETL_STATU statu = etlBooksService.selectProStatu(checkbox[i], day_time);
            if (statu != null) {
                etlBooksService.updateProStatu(checkbox[i], day_time);
            } else {
                etlBooksService.insertProStatu(checkbox[i], day_time);
            }
        }
        for (int i = 0; i < checkbox.length; i++) {
            List<TB_ETL_STATU> list_tb = etlBooksService.selectTabStatu(checkbox[i], day_time);
            for (int j = 0; j < list_tb.size(); j++) {
                String table_name = list_tb.get(j).getTable_name();
                if (table_name.contains(".")) {
                    table_name = table_name.split("\\.")[0];
                    System.out.println(table_name+"=======================");
                    if (list_tb.get(j).getFlag() != null) {
                        System.out.println(list_tb.get(j).getFlag()+"FLAG");
                        if (table_name.contains("ods") || table_name.contains("ODS")) {
                            System.out.println("是否ODS表"+table_name+"++++++++");
                            etlBooksService.updateTabStatu(0, list_tb.get(j).getTable_name(), day_time);
                        } else {
                            etlBooksService.updateTabStatu(2, list_tb.get(j).getTable_name(), day_time);
                            System.out.println("第一个else");
                        }
                    } else {
                        if (table_name.contains("ods") || table_name.contains("ODS")) {
                            System.out.println("是否ODS表"+table_name+"-------------");
                            etlBooksService.insertTabStatu(0, list_tb.get(j).getTable_name(), day_time);
                        } else {
                            etlBooksService.insertTabStatu(2, list_tb.get(j).getTable_name(), day_time);
                            System.out.println("第二个else");
                        }
                    }
                }
            }
        }
        //20170725不需要在调脚本和删除文件
        //DeleteFile.getInstance().delFile(times);
        return "redirect:etlReplace.do";
    }

    /**
     * Created by Leslie Lee on 2017/11/01  modify
     * @param request
     * @return
     */
    @RequestMapping(value = "etl/submit")
    public String submit(HttpServletRequest request) {
        List<ProcedureBean> procedureList = new ArrayList<ProcedureBean>();
        String seq_id = request.getParameter("SEQ_ID");
        String[] proc_names = request.getParameterValues("PROC_NAME");
        String[] table_names = request.getParameterValues("TABLE_NAME");
        String[] apply_flags = request.getParameterValues("FLAG");
        String[] ods_flags = request.getParameterValues("TABLE_IS_ODS");
        String[] priorities = request.getParameterValues("LEV");//优先等级
        String[] result_tables = request.getParameterValues("RESULT_TABLE") ;
        User user = (User) request.getSession().getAttribute("user");

        String update_user = user.getUsername();
        if (proc_names.length > 0) {
            for (int i = 0; i < proc_names.length; i++) {
                ProcedureBean bean = new ProcedureBean();
                bean.setPROC_NAME(proc_names[i].toUpperCase());
                bean.setTABLE_NAME(table_names[i].toUpperCase());
                bean.setTABLE_IS_ODS(Integer.valueOf(ods_flags[i]));
                bean.setFLAG(Integer.valueOf(apply_flags[i]));
                bean.setLEV(Integer.valueOf(priorities[i]));
                bean.setUPDATE_USER(update_user.toUpperCase());
                bean.setRESULT_TABLE(result_tables[i].toUpperCase());
                procedureList.add(bean);
            }
        }
        for (int i = 0; i < procedureList.size(); i++) {
            Date date = new Date(System.currentTimeMillis());
            String update_time = simpleDateFormat.format(date);
            if (seq_id == null || seq_id == "") {
                EtlBooks etlBook = etlBooksService.selectFromEtlBook(procedureList.get(i).getPROC_NAME(), procedureList.get(i).getTABLE_NAME());
                if (etlBook != null) {
                    etlBooksService.updateEtlBookFlag(etlBook.getSEQ_ID(), procedureList.get(i).getLEV(), update_time, procedureList.get(i).getUPDATE_USER(), procedureList.get(i).getTABLE_IS_ODS(),procedureList.get(i).getRESULT_TABLE());
                } else {
                    etlBooksService.submitEtlBook(procedureList.get(i).getPROC_NAME(), procedureList.get(i).getTABLE_NAME(), procedureList.get(i).getLEV(), update_time, update_user, procedureList.get(i).getFLAG(), procedureList.get(i).getTABLE_IS_ODS(),procedureList.get(i).getRESULT_TABLE());
                }
            } else {
                etlBooksService.updateEtlBook(seq_id, procedureList.get(i).getPROC_NAME(), procedureList.get(i).getTABLE_NAME(), procedureList.get(i).getLEV(), update_time, procedureList.get(i).getUPDATE_USER(), procedureList.get(i).getFLAG(), procedureList.get(i).getTABLE_IS_ODS(),procedureList.get(i).getRESULT_TABLE());
            }
        }
        return "redirect:etlbooks.jsp";
    }

    /**
     * 存储过程初始化 ALL
     * @param request
     * @return
     */
    @RequestMapping(value = "etl/initProcedure")
    @Transactional
    public String initProcedure(HttpServletRequest request) {
        System.out.println("FirstDel");
        etlBooksService.firstInitDelete();
        System.out.println("SecondDel");
        etlBooksService.initSecondDelete();
        System.out.println("FirstInsert");
        etlBooksService.initFirstInsert();
        System.out.println("SecondInsert");
        etlBooksService.initSecondInsert();
        return "forward:etlbooks.jsp";
    }

    /**
     * 存储过程初始化  SINGLE
     * @param request
     * @return
     */
    @RequestMapping(value = "etl/singProcleInit")
    public String singProcleInit(HttpServletRequest request) {
        String[] proc_names = request.getParameterValues("PROC_NAME");
        String[] day_times = request.getParameterValues("DAY_TIME");
        for (int i = 0; i < proc_names.length; i++) {
            int day_time = Integer.parseInt(day_times[i]);
            etlBooksService.initFirstDeleteSingle(proc_names[i], String.valueOf(day_time));
            List<String> list = etlBooksService.getTableByProcName(proc_names[i]);
            for (int k = 0; k < list.size(); k++) {
                etlBooksService.initSecondDeleteSingle(list.get(k), String.valueOf(day_time));
            }
            etlBooksService.initFirstInsertSingle(proc_names[i], day_times[i]);
            etlBooksService.initSecondInsertSingle(proc_names[i], day_times[i]);
        }
        return "forward:etlbooks.jsp";
    }

    /**
     * 清洗失败的存储过程 统计
     * @param request
     * @return
     */
    @RequestMapping(value = "etl/failProcedure")
    @ResponseBody
    public String failProcedure(HttpServletRequest request) {
        List<ProcedureFail> list = etlBooksService.failProcedure();
        JSONObject obj = new JSONObject();
        obj.put("Rows", list);
        obj.put("Total", list.size());
        String bString = obj.toJSONString();
        return bString;
    }

    /**
     * 存储过程重新清洗
     * @param request
     * @return
     */
    @RequestMapping(value = "etl/cleanProcedure")
    @ResponseBody
    public String cleanProcedure(HttpServletRequest request) {
        String[] proc_names = request.getParameterValues("PROC_NAME");
        //String[] table_names = request.getParameterValues("TABLE_NAME");
        String[] day_times = request.getParameterValues("DAY_TIME");
        for (int i = 0; i < proc_names.length; i++) {
            String proc_name = proc_names[i];
            //table_name = proc_names[i].concat(".").concat(table_names[i]);
            etlBooksService.cleanProcedure(proc_name, day_times[i]);
        }
        return " THE PROCEDURE HAS BEAN RECLEANED IN SUCESS  ....";
    }

    @RequestMapping(value = "etl/cleanProcedureEntiy")
    @ResponseBody
    public String cleanProcedureEntiy(HttpServletRequest request) {
        String[] proc_names = request.getParameterValues("PROC_NAME");
        String day_time = request.getParameter("day_time");
        for (int i = 0; i < proc_names.length; i++) {
            etlBooksService.cleanProcedure(proc_names[i], day_time);
        }
        return " THE PROCEDURE HAS BEAN RECLEANED IN SUCESS  ....";
    }

    @RequestMapping(value = "etl/getByProcName")
    @ResponseBody
    public ModelAndView getByProcName(HttpServletRequest request) {
        String proc_name = request.getParameter("proc_name");
        ModelAndView mv = new ModelAndView();
        List<EtlBooks> all_proc_list = etlBooksService.getByProcName(proc_name);
        mv.addObject("lists", all_proc_list);
        mv.setViewName("getByProcName");
        return mv;
    }

    @RequestMapping(value = "etl/getByTableName")
    @ResponseBody
    public ModelAndView byTableNmae(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String table_name = request.getParameter("table_name");
        List<EtlBooks> tableNameList = etlBooksService.getByTableName(table_name);
        mv.addObject("lists", tableNameList);
        mv.setViewName("getByTableName");
        return mv;
    }

    @RequestMapping(value = "etl/etlReplaceStatusProc")
    @ResponseBody
    public ModelAndView getByProcAllName(HttpServletRequest request) {
        String proc_name = request.getParameter("proc_name");
        ModelAndView mv = new ModelAndView();
        Set<String> proc_set = etlBooksService.getByProcAllName(proc_name);
        List store_list = new ArrayList();
        for (String name_proc : proc_set) {
            List<PRO_ETL_STATU> obj = etlBooksService.etlReplaceStatus(name_proc);
            for (PRO_ETL_STATU bean : obj) {
                store_list.add(bean);
            }
        }
        mv.addObject("lists", store_list);
        mv.setViewName("etlReplaceStatus");
        return mv;
    }

    @RequestMapping(value = "etl/etlReplaceStatusTable")
    @ResponseBody
    public ModelAndView etlReplaceStatusTable(HttpServletRequest request) {
        String table_name = request.getParameter("table_name");
        ModelAndView mv = new ModelAndView();
        List<PRO_ETL_STATU> proc_list = etlBooksService.etlReplaceStatusTable(table_name);
        mv.addObject("lists", proc_list);
        mv.setViewName("etlReplaceStatus");
        return mv;
    }

    //查询所有的TASK_LOG_INFO
    @RequestMapping(value = "etl/allTaskInfos")
    @ResponseBody
    public String allTaskInfos(HttpServletRequest request) {
        List<TRACK_TASK_INFO> track_task_infos = etlBooksService.allTaskInfos();
        JSONObject obj = new JSONObject();
        obj.put("Rows", track_task_infos);
        obj.put("Total", track_task_infos.size());
        String bString = obj.toJSONString();
        return bString;
    }
}
