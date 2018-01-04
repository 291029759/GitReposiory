package ule.com.etl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Leslie Lee on 2017/12/27
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WarehouseDayDetailSearch {
    int SEQ_ID ;
    String D_DAYID ;
    int ORG_COUNTRY_ID ;
    String ORG_COUNTRY ;
    int ORG_PROVINCE_ID ;
    String ORG_PROVINCE ;
    int ORG_CITY_ID ;
    String ORG_CITY ;
    int ORG_AREA_ID ;
    String ORG_AREA ;
    int ORG_SUBOFFICE_ID ;
    String ORG_SUBOFFICE ;
    int YPS_ORDER_SUM ;
    int ORDERS ;
    int ID ;
    int RANK ;
}
