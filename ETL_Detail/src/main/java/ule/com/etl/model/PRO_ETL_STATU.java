package ule.com.etl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PRO_ETL_STATU {
	private String id;
	private String proc_name;
	private String table_name;
	private int flag;
	private int day_time;
	private Date create_time ;

}
