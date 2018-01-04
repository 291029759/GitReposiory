package ule.com.etl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Leslie Lee on 2017/11/1
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProcedureBean {
    private String PROC_NAME;
    private String TABLE_NAME;
    private String UPDATE_USER;
    private int LEV;
    private int FLAG;
    private int TABLE_IS_ODS;
    private String RESULT_TABLE ;
}
