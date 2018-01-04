package ule.com.etl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Leslie Lee on 2017/11/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcedureFail {
    private int day_time ;
    private String proc_name ;
    private int table_count ;
    private int count_ods ;
}
