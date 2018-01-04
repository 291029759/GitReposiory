package ule.com.etl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtlBooks {
	private String SEQ_ID;
	private String PROC_NAME;
	private String TABLE_NAME;
	private int LEV;
	private String UPDATE_TIME;
	private String UPDATE_USER;
	private String FLAG;
	private int TABLE_IS_ODS;
	private String RESULT_TABLE ;
}
