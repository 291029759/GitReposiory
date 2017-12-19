package ule.com.etl.service;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import ule.com.etl.model.EtlBooks;
import ule.com.etl.model.PRO_ETL_STATU;
import ule.com.etl.model.ProcedureFail;
import ule.com.etl.model.TB_ETL_STATU;

public interface EtlBooksService {
	List<EtlBooks> selectEtlBooks();

	EtlBooks selectEtlBook(String id);

	void deleteEtlBook(String id);
 
	void submitEtlBook(String proc_name, String table_name, int lev, String update_time,
			String update_user, int flag,int table_is_ods);

	void updateEtlBook(String seq_id, String proc_name, String table_name, int lev, String update_time,
			String update_user, int flag,int table_is_ods);

	EtlBooks selectFromEtlBook(String proc_name, String table_name);

	void updateEtlBookFlag(String seq_ID, int lev, String update_time, String update_user, int table_is_ods);

	List<PRO_ETL_STATU> etlReplace();

	PRO_ETL_STATU selectProStatu(String proc_name,int day_time);

	void updateProStatu(String proc_name, int day_time);

	void insertProStatu(String proc_name, int day_time);
	
	List<TB_ETL_STATU> selectTbStatu(String proc_name,int day_time);
	
	void updateTabStatu(String table_name, int day_time);

	void insertTabStatu(String table_name, int day_time);
	/**
	 * 存储过程初始化  ALL
	 */
	void initFirstDelete();
	void initSecondDelete();
	void initFirstInsert();
	void initSecondInsert();
	/**
	 *  存储过程初始化  SINGLE
	 */
	void initFirstDeleteSingle(String proc_name,String day_time);
	void initSecondDeleteSingle(String table_name,String day_time);
	void initFirstInsertSingle(String proc_name,String day_time);
	void initSecondInsertSingle(String proc_name,String day_time);
	/**
	 * 清洗失败的存储过程
	 */
	 List<ProcedureFail> failProcedure();
    /**
     * 重新清洗 存储过程
     */
    void cleanProcedure(String table_name,String day_time);
	/**
	 * 根据表明 或者 存储过程名称查询
	 */
	List<EtlBooks> getByProcName(String proc_name);
	List<EtlBooks> getByTableName(String table_name);
	List<String> getTableByProcName(String proc_name);
	/**
	 * select by tablename
	 */
	List<EtlBooks> selectEtlBooksByName(String table_name);
	List<PRO_ETL_STATU> etlReplaceStatus(String proc_name);

	//List<PRO_ETL_STATU> getByTableAllName(String proc_name);
	Set<String> getByProcAllName(String proc_name);
	List<PRO_ETL_STATU> etlReplaceStatusTable(String table_name);
}
