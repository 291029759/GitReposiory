package ule.com.etl.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ule.com.etl.model.WarehouseDayDetailSearch;
import ule.com.etl.service.ElasticSearchService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ElasticSearchController {
    // private static Logger log = Logger.getLogger(ElasticSearchController.class);

    //  private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
    //  private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    @Autowired
    private ElasticSearchService elasticSearchService;

    @RequestMapping(value = "etl/test")
    @ResponseBody
    public String etlBooksModel(HttpServletRequest request) {
        Map<String, String>  wrapIncrementMessage = new HashMap<String, String>();
        Map<String, String> messageMap = new HashMap<String, String>();
        String _rt_indexName = request.getParameter("indexName ");      // 索引名称
        String _rt_docId = request.getParameter("docIdField ");        // 索引主键
        String _rt_changeFlg = request.getParameter("changFlag ");     // 操作类型
        String d_dayid = request.getParameter("d_dayid ");     // 操作类型

        wrapIncrementMessage.put("_rt_indexName", _rt_indexName);
        wrapIncrementMessage.put("_rt_docId", _rt_docId);
        wrapIncrementMessage.put("_rt_changeFlg", _rt_changeFlg);

        List<WarehouseDayDetailSearch> store = elasticSearchService.getWHDDSByDay(d_dayid);
        for (int i=0 ;i< store.size() ;i++){
            WarehouseDayDetailSearch bean =  store.get(i);
            messageMap.put("SEQ_ID", String.valueOf(bean.getSEQ_ID()));
            messageMap.put("D_DAYID",bean.getD_DAYID());
            messageMap.put("ORG_COUNTRY_ID",String.valueOf(bean.getORG_COUNTRY_ID()));
            messageMap.put("ORG_COUNTRY",bean.getORG_COUNTRY());
            messageMap.put("ORG_PROVINCE_ID",String.valueOf(bean.getORG_PROVINCE_ID()));
            messageMap.put("ORG_PROVINCE",bean.getORG_PROVINCE());
            messageMap.put("ORG_CITY_ID",String.valueOf(bean.getORG_CITY_ID()));
            messageMap.put("ORG_CITY",bean.getORG_CITY());
            messageMap.put("ORG_AREA_ID",String.valueOf(bean.getORG_AREA_ID()));
            messageMap.put("ORG_AREA",bean.getORG_AREA());
            messageMap.put("ORG_SUBOFFICE_ID",String.valueOf(bean.getORG_SUBOFFICE_ID()));
            messageMap.put("ORG_SUBOFFICE",bean.getORG_SUBOFFICE());
            messageMap.put("YPS_ORDER_SUM",String.valueOf(bean.getYPS_ORDER_SUM()));
            messageMap.put("ORDERS",String.valueOf(bean.getORDERS()));
            messageMap.put("ID",String.valueOf(bean.getID()));
            messageMap.put("RANK",String.valueOf(bean.getRANK()));
        }

        return null;
    }

}
