package ule.com.etl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Leslie Lee on 2017/11/01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private int id;
	private String username;
	private String pwd;
	private String email;
	private String phone;
	private int flag;
}
