package ule.com.etl.service;

import ule.com.etl.model.User;
import ule.com.etl.model.WarehouseDayDetailSearch;

import java.util.List;

/**
 * Created by Leslie Lee on 2017/11/01
 */
public interface ElasticSearchService {

	List<WarehouseDayDetailSearch> getWHDDSByDay(String d_dayid);

}
