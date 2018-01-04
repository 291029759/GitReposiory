package ule.com.etl.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ule.com.etl.dao.EtlBooksDao;
import ule.com.etl.model.*;
import ule.com.etl.service.EtlBooksService;

@Service
public class EtlBooksServiceImpl implements EtlBooksService {

    @Autowired
    public EtlBooksDao etlBooksDao;

    private EtlBooksService etlBooksService;

    public List<EtlBooks> selectEtlBooks() {
        return etlBooksDao.selectEtlBooks();
    }

    public EtlBooks selectEtlBook(String id) {
        return etlBooksDao.selectEtlBook(id);
    }

    public void deleteEtlBook(String id) {
        etlBooksDao.deleteEtlBook(id);
    }

    public void submitEtlBook(String proc_name, String table_name, int lev, String update_time, String update_user,
                              int flag, int table_is_ods,String result_table) {
        etlBooksDao.submitEtlBook(proc_name, table_name, lev, update_time, update_user, flag, table_is_ods,result_table);
    }

    public void updateEtlBook(String seq_id, String proc_name, String table_name, int lev, String update_time, String update_user,
                              int flag, int table_is_ods,String result_table) {
        etlBooksDao.updateEtlBook(seq_id, proc_name, table_name, lev, update_time, update_user, flag, table_is_ods,result_table);
    }

    public EtlBooks selectFromEtlBook(String proc_name, String table_name) {
        return etlBooksDao.selectFromEtlBook(proc_name, table_name);
    }

    public void updateEtlBookFlag(String seq_ID, int lev, String update_time, String update_user, int table_is_ods,String result_table) {
        etlBooksDao.updateEtlBookFlag(seq_ID, lev, update_time, update_user, table_is_ods,result_table);
    }

    public List<PRO_ETL_STATU> etlReplace() {
        return etlBooksDao.etlReplace();
    }

    public PRO_ETL_STATU selectProStatu(String proc_name, int day_time) {
        return etlBooksDao.selectProStatu(proc_name, day_time);
    }

    public void updateProStatu(String proc_name, int day_time) {
        etlBooksDao.updateProStatu(proc_name, day_time);
    }

    public void insertProStatu(String proc_name, int day_time) {
        etlBooksDao.insertProStatu(proc_name, day_time);
    }

    public List<TB_ETL_STATU> selectTabStatu(String proc_name, int day_time) {
        return etlBooksDao.selectTabStatu(proc_name, day_time);
    }

    public void updateTabStatu(int lev ,String table_name, int day_time) {
        etlBooksDao.updateTabStatu(lev ,table_name, day_time);
    }

    public void insertTabStatu(int lev ,String table_name, int day_time) {
        etlBooksDao.insertTabStatu(lev ,table_name, day_time);
    }

    /**
     * 存储过程初始化 ALL
     */

    public void firstInitDelete() { etlBooksDao.firstInitDelete(); }

    public void initSecondDelete() {
        etlBooksDao.initSecondDelete();
    }

    public void initFirstInsert() {
        etlBooksDao.initFirstInsert();
    }

    public void initSecondInsert() {
        etlBooksDao.initSecondInsert();
    }

    /**
     * 存储过程初始化 SINGLE
     */
    public void initFirstDeleteSingle(String proc_name, String day_time) {
        etlBooksDao.initFirstDeleteSingle(proc_name, day_time);
    }

    public void initSecondDeleteSingle(String table_name, String day_time) {
        etlBooksDao.initSecondDeleteSingle(table_name, day_time);
    }

    public void initFirstInsertSingle(String proc_name, String day_time) {
        etlBooksDao.initFirstInsertSingle(proc_name, day_time);
    }

    public void initSecondInsertSingle(String proc_name, String day_time) {
        etlBooksDao.initSecondInsertSingle(proc_name, day_time);
    }

    /**
     * 清洗失败的存储过程
     */
    public List<ProcedureFail> failProcedure() {
        return etlBooksDao.failProcedure();
    }

    /**
     * 重新清洗 存储过程
     */
    public void cleanProcedure(String table_name, String day_time) {
        etlBooksDao.cleanProcedure(table_name, day_time);
    }

    /**
     * 根据表明 或者 存储过程名称查询
     */
    public List<EtlBooks> getByProcName(String proc_name) {
        return etlBooksDao.getByProcName(proc_name);
    }

     public Set<String> getByProcAllName(String proc_name) {
        List<EtlBooks> store_list = new ArrayList<EtlBooks>();
        List<EtlBooks> procNameList = etlBooksDao.getByProcName(proc_name);
        Set<String> list_proc = new HashSet();
        List<EtlBooks> proc_list = new ArrayList<EtlBooks>();
        if (procNameList.size() > 0) {
            for (int i = 0; i < procNameList.size(); i++) {
                store_list.add(procNameList.get(i));   //第一次将查询结果存储过程和表名 add进list，然后以表名开始重新遍历
                proc_list = etlBooksDao.getByTableName(procNameList.get(i).getTABLE_NAME());//这里查询到第一次 表名和所有依赖的存储过程
                for (EtlBooks list:proc_list){
                    list_proc.add(list.getPROC_NAME());
                }
            }
                if (proc_list.size() > 0) { //判断数量，表名和查出的存储过程名。
                    for (int k = 0; k < proc_list.size(); k++) {
                        if (!list_proc.contains(proc_list.get(k).getPROC_NAME())) { //判断以表名查出的存储过程名是否与传进来的相同，相同则不反回 不相同则第二次返回存储过程名和表名
                            List<EtlBooks> tab_list = etlBooksDao.getByProcName(proc_list.get(k).getPROC_NAME());//第二次查询到 存储过程和表名
                            if (tab_list.size() > 0) {
                                for (int j = 0; j < tab_list.size(); j++) {
                                    getByProcAllName(tab_list.get(j).getPROC_NAME());
                                }
                            }
                        }
                    }
                }
            }
        return list_proc;
    }

    public List<PRO_ETL_STATU> etlReplaceStatusTable(String table_name) {
        return etlBooksDao.etlReplaceStatusTable(table_name);
    }

    public List<EtlBooks> getByTableName(String table_name) {
        return etlBooksDao.getByTableName(table_name);
    }
    public List<String> getTableByProcName(String proc_name) {
        return etlBooksDao.getTableByProcName(proc_name);
    }
    /**
     * select by tablename
     */
    public List<EtlBooks> selectEtlBooksByName(String table_name) {
        return etlBooksDao.selectEtlBooksByName(table_name);
    }
    public List<EtlBooks> selectEtlBooksByProcName(String proc_name) {
        return etlBooksDao.selectEtlBooksByProcName(proc_name);
    }
    public List<EtlBooks> selectEtlBooksByResultTable(String result_table) {
        return etlBooksDao.selectEtlBooksByResultTable(result_table);
    }
    public List<PRO_ETL_STATU> etlReplaceStatus(String proc_name) {
        return etlBooksDao.etlReplaceStatus(proc_name);
    }

    public List<TRACK_TASK_INFO> allTaskInfos() {
        return etlBooksDao.allTaskInfos() ;
    }
}
