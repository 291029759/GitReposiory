package ule.com.etl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ule.com.etl.model.*;

public interface EtlBooksDao {

	List<EtlBooks> selectEtlBooks();

	EtlBooks selectEtlBook(String id);

	void deleteEtlBook(String id);

	void submitEtlBook(@Param("proc_name") String proc_name, @Param("table_name") String tb_name,
			@Param("lev") int etl_level, @Param("update_time") String update_time,
			@Param("update_user") String update_user, @Param("flag") int flag, @Param("table_is_ods") int table_is_ods,@Param("result_table") String result_table);

	void updateEtlBook(@Param("seq_id") String seq_id, @Param("proc_name") String proc_name,
			@Param("table_name") String table_name, @Param("lev") int lev, @Param("update_time") String update_time,
			@Param("update_user") String update_user, @Param("flag") int flag, @Param("table_is_ods") int table_is_ods,@Param("result_table") String result_table);

	EtlBooks selectFromEtlBook(@Param("proc_name") String proc_name, @Param("table_name") String table_name);

	void updateEtlBookFlag(@Param("seq_ID") String seq_ID, @Param("lev") int lev,
			@Param("update_time") String update_time, @Param("update_user") String update_user,
			@Param("table_is_ods") int table_is_ods,@Param("result_table") String result_table);

	List<PRO_ETL_STATU> etlReplace();

	PRO_ETL_STATU selectProStatu(@Param("proc_name") String proc_name,@Param("day_time") int day_time);

	void updateProStatu(@Param("proc_name") String proc_name,@Param("day_time") int day_time);

	void insertProStatu(@Param("proc_name") String proc_name,@Param("day_time") int day_time);
	
	List<TB_ETL_STATU> selectTabStatu(@Param("proc_name") String proc_name,@Param("day_time") int day_time);

	void updateTabStatu(@Param("lev") int lev ,@Param("table_name") String table_name,@Param("day_time") int day_time);

	void insertTabStatu(@Param("lev") int lev ,@Param("table_name") String table_name,@Param("day_time") int day_time);

	/**
	 *  存储过程初始化  ALL
	 */
	 void firstInitDelete();
	 void initSecondDelete();
	 void initFirstInsert();
	 void initSecondInsert();
	/**
	 *  存储过程初始化  SINGLE
	 */
	void initFirstDeleteSingle(@Param("proc_name") String proc_name,@Param("day_time") String day_time);
	void initSecondDeleteSingle(@Param("table_name") String table_name,@Param("day_time") String day_time);
	void initFirstInsertSingle(@Param("proc_name") String proc_name,@Param("day_time") String day_time);
	void initSecondInsertSingle(@Param("proc_name") String proc_name,@Param("day_time") String day_time);
	/**
	 * 清洗失败的存储过程
	 */
	List<ProcedureFail> failProcedure();
	/**
	 * 重新清洗 存储过程
	 */
	void cleanProcedure(@Param("table_name") String table_name,@Param("day_time") String day_time);
	/**
	 * 根据表明 或者 存储过程名称查询
	 */
	List<EtlBooks> getByProcName(@Param("proc_name") String proc_name);
	List<EtlBooks> getByTableName(@Param("table_name") String table_name);
	List<String> getTableByProcName(@Param("proc_name") String proc_name);
	/**
	 *select by tablename
	 */
	List<EtlBooks> selectEtlBooksByName(@Param("table_name") String table_name);
	List<EtlBooks> selectEtlBooksByProcName(@Param("proc_name") String proc_name);
	List<EtlBooks> selectEtlBooksByResultTable(@Param("result_table") String result_table);

	List<PRO_ETL_STATU> etlReplaceStatus(@Param("proc_name") String proc_name);
	List<PRO_ETL_STATU> etlReplaceStatusTable(@Param("table_name") String table_name);

	/**
	 * TASK_INFO
	 */
	List<TRACK_TASK_INFO> allTaskInfos();
}
