package ule.com.etl.model;

import org.springframework.stereotype.Component;

@Component
public class EtlBooks {
	private String SEQ_ID;
	private String PROC_NAME;
	private String TABLE_NAME;



	private int LEV;
	private String UPDATE_TIME;
	private String UPDATE_USER;
	private String FLAG;
	private int TABLE_IS_ODS;
	
	public String getSEQ_ID() {
		return SEQ_ID;
	}
	public void setSEQ_ID(String sEQ_ID) {
		SEQ_ID = sEQ_ID;
	}
	public String getPROC_NAME() {
		return PROC_NAME;
	}
	public void setPROC_NAME(String pROC_NAME) {
		PROC_NAME = pROC_NAME;
	}
	public String getTABLE_NAME() {
		return TABLE_NAME;
	}
	public void setTABLE_NAME(String tABLE_NAME) {
		TABLE_NAME = tABLE_NAME;
	}
	public int getLEV() {
		return LEV;
	}
	public void setLEV(int lEV) {
		LEV = lEV;
	}
	public String getUPDATE_TIME() {
		return UPDATE_TIME;
	}
	public void setUPDATE_TIME(String uPDATE_TIME) {
		UPDATE_TIME = uPDATE_TIME;
	}
	public String getUPDATE_USER() {
		return UPDATE_USER;
	}
	public void setUPDATE_USER(String uPDATE_USER) {
		UPDATE_USER = uPDATE_USER;
	}
	public String getFLAG() {
		return FLAG;
	}
	public void setFLAG(String fLAG) {
		FLAG = fLAG;
	}
	public int getTABLE_IS_ODS() {
		return TABLE_IS_ODS;
	}
	public void setTABLE_IS_ODS(int tABLE_IS_ODS) {
		TABLE_IS_ODS = tABLE_IS_ODS;
	}
	/*@Override
	public String toString() {
		return "EtlBooks{" +
				"SEQ_ID='" + SEQ_ID + '\'' +
				", PROC_NAME='" + PROC_NAME + '\'' +
				", TABLE_NAME='" + TABLE_NAME + '\'' +
				", LEV=" + LEV +
				", UPDATE_TIME='" + UPDATE_TIME + '\'' +
				", UPDATE_USER='" + UPDATE_USER + '\'' +
				", FLAG='" + FLAG + '\'' +
				", TABLE_IS_ODS=" + TABLE_IS_ODS +
				'}';
	}*/
}
