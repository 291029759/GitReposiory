package ule.com.etl.service;

import org.apache.ibatis.annotations.Param;
import ule.com.etl.model.User;

import java.util.List;

/**
 * Created by Leslie Lee on 2017/11/01
 */
public interface UserService {

	String getPwd(User user);
	List<User> getAllUser() ;           //查询所有用户信息
	User getUserById(int id );			//根据ID查询用户信息
	void insertUser(int id,String username,String pwd,String email,String phone,int flag) ;    //插入用户信息
	void updateUser(int id,String username,String pwd,String email,String phone,int flag);     //更新用户信息
	void deleteUser(int id);  		    //删除用户信息
	int getMaxId();			            //获取最大用户信息ID
	User getUserByName(String username); //根据用户名查询用户信息
}
