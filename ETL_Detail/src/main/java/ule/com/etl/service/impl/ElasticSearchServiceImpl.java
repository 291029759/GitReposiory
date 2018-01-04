package ule.com.etl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ule.com.etl.dao.ElasticSearchDao;
import ule.com.etl.dao.EtlBooksDao;
import ule.com.etl.model.*;
import ule.com.etl.service.ElasticSearchService;
import ule.com.etl.service.EtlBooksService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {

    @Autowired
    public ElasticSearchDao elasticSearchDao;

    private ElasticSearchService elasticSearchService;

    public List<WarehouseDayDetailSearch> getWHDDSByDay(String d_dayid) {
        return elasticSearchDao.getWHDDSByDay(d_dayid) ;
    }
}
