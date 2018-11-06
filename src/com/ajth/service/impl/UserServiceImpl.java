package com.ajth.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ajth.dao.UserDao;
import com.ajth.domain.User;
import com.ajth.service.UserService;
import com.ajth.utils.MD5Utils;

/*
 * @author xfd
 * 用户管理业务接口实现类
 */
@Transactional
public class UserServiceImpl implements UserService {

	//注入dao
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	//用户注册
	@Override
	public void save(User user) {
		//使用MD5对加密进行加密
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		user.setUser_state('1');	//'1'正常，'0'暂停
		
		//调用dao
		userDao.save(user);
	}

	//用户登录
	@Override
	public User login(User user) {
		user.setUser_password(MD5Utils.md5(user.getUser_password()));

		//调用dao
		return userDao.login(user);
	}

	//查询所有的用户
	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

}
