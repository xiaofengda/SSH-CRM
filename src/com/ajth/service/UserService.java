package com.ajth.service;

import java.util.List;

import com.ajth.domain.User;

/*
 * @author xfd
 * 用户管理业务接口
 */
public interface UserService {

	//用户注册
	void save(User user);

	//用户登录
	User login(User user);

	//查询所有用户
	List<User> findAll();


}
