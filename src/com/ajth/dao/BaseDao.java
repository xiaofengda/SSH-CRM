package com.ajth.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/*
 * @author xfd
 * @date 2018-10-31
 * 抽取通用dao接口
 */
public interface BaseDao<T> {
	
	//保存方法
	public void save (T t);
	
	//修改修改
	public void update (T t);
	
	//删除方法
	public void delete (T t);
	
	//根据id查询方法
	public T findById(Serializable id);
	
	//查询所有方法
	public List<T> findAll();
	
	//统计个数方法
	public Integer findCount(DetachedCriteria detachedCriteria);
	
	//分页查询方法
	public List<T> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);
	
	
}
