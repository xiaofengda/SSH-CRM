package com.ajth.dao.impl;

import java.util.List;
import com.ajth.dao.UserDao;
import com.ajth.domain.User;

/*
 * @author xfd
 * 用户管理DAO接口实现类
 */
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	//调用父类构造方法
	/*public UserDaoImpl() {
		super(User.class);
	}
*/
	//用户登陆
	@SuppressWarnings("unchecked")
	@Override
	public User login(User user) {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code=? and user_password=?", user.getUser_code(),
				user.getUser_password());
		
		//判断
		if(list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
		
	}
}
