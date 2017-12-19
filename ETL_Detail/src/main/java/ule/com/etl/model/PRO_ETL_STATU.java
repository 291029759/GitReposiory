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

	/*public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProc_name() {
		return proc_name;
	}
	public void setProc_name(String proc_name) {
		this.proc_name = proc_name;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getDay_time() {
		return day_time;
	}
	public void setDay_time(int day_time) {
		this.day_time = day_time;
	}*/
}
