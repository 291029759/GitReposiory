package ule.com.etl.dao;

import org.apache.ibatis.annotations.Param;
import ule.com.etl.model.User;

import java.util.List;
/**
 * Created by Leslie Lee on 2017/11/01
 */
public interface UserDao {

	String getPwd(User user);
	List<User> getAllUser() ;    //查询所有用户信息
	User getUserById(@Param("id") int id);			 //根据ID查询用户信息
	void insertUser(@Param("id") int id,@Param("username") String username,@Param("pwd") String pwd ,@Param("email") String email,@Param("phone") String phone,@Param("flag") int flag) ;    //插入用户信息
	void updateUser(@Param("id") int id,@Param("username") String username,@Param("pwd") String pwd ,@Param("email") String email,@Param("phone") String phone,@Param("flag") int flag);     //更新用户信息
	void deleteUser(@Param("id") int id);  		 //删除用户信息
	int getMaxId();   			//获取最大用户信息ID
	User getUserByName(@Param("username") String username);
}
