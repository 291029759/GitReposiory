package ule.com.etl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ule.com.etl.dao.UserDao;
import ule.com.etl.model.User;
import ule.com.etl.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserDao userDao;
	
	public String getPwd(User user) {
		// TODO Auto-generated method stub
		return userDao.getPwd(user);
	}
	//查询所有用户信息
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}
	//根据ID查询用户信息
	public User getUserById(int id) { return userDao.getUserById(id); }

    //插入用户信息
    public void insertUser(int id,String username,String pwd,String email,String phone,int flag) {
	    userDao.insertUser(id,username,pwd,email,phone,flag);
    }

    //更新用户信息
	public void updateUser(int id,String username,String pwd,String email,String phone,int flag) {
		userDao.updateUser(id,username,pwd,email,phone,flag);
	}
	//删除用户信息
	public void deleteUser(int id) {
		userDao.deleteUser(id);
	}
	//获取最大用户信息ID
	public int getMaxId() { return userDao.getMaxId();}

	public User getUserByName(String username) { return userDao.getUserByName(username); }

}
