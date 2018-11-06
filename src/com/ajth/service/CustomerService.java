package com.ajth.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ajth.domain.Customer;
import com.ajth.domain.PageBean;

/*
 * @author xfd
 * 客户管理业务层接口
 */
public interface CustomerService {

	//保存客户
	void save(Customer customer);

	//分页查询客户的方法
	PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	//删除一条记录
	void delete(Customer customer);

	//根据Id查
	Customer findById(Long cust_id);

	//修改
	void update(Customer customer);

	//查询所有
	List<Customer> findAll();

}
