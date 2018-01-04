package ule.com.etl.dao;

import org.apache.ibatis.annotations.Param;
import ule.com.etl.model.*;

import java.util.List;

public interface ElasticSearchDao {

	List<WarehouseDayDetailSearch> getWHDDSByDay(@Param("d_dayid") String d_dayid);

}
