package ule.com.etl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Leslie Lee on 2017/12/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TRACK_TASK_INFO {
    private int id  ;
    private String task_type ;
    private int task_result ;
    private String task_desc ;
    private int task_num ;
    private String create_date ;
    private String task_date ;
    private String etl_user_name ;
    private int etl_time ;
}
