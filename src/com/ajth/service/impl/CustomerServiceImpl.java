package com.ajth.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.ajth.dao.CustomerDao;
import com.ajth.domain.Customer;
import com.ajth.domain.PageBean;
import com.ajth.service.CustomerService;

/*
 * @author xfd
 * 客户管理业务层接口实现类
 */
@Transactional
public class CustomerServiceImpl implements CustomerService {

	//注入Dao
	private CustomerDao customerDao;
	
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}


	//保存客户
	@Override
	public void save(Customer customer) {
		
		//调用dao
		customerDao.save(customer);
		
	}

	//业务层分页查询客户的方法
	@Override
	public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<Customer> pageBean = new PageBean<Customer>();
		//封装当前页
		pageBean.setCurrPage(currPage);
		//封装每页显示条数
		pageBean.setPageSize(pageSize);
		//封装总记录条数：
		//这里需要调用Dao进行查询:
		Integer tatalcount = customerDao.findCount(detachedCriteria);
		pageBean.setTatalcount(tatalcount);
		//封装总页数
		Double tc = tatalcount.doubleValue();
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		//封装每页显示数据的集合
		Integer begin = (currPage - 1) * pageSize;
		List<Customer> list = customerDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}


	//删除一条记录
	@Override
	public void delete(Customer customer) {
		//调用Dao层
		customerDao.delete(customer);
		
	}

	//根据id查
	@Override
	public Customer findById(Long cust_id) {
		return customerDao.findById(cust_id);
	}

	//修改客户
	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
	}

	//查询所有客户
	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

}
