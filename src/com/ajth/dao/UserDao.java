package com.ajth.dao;

import com.ajth.domain.User;

/*
 * @author xfd
 * 用户管理DAO接口
 */
public interface UserDao extends BaseDao<User> {

	//用户登陆
	User login(User user);

}
