package com.ajth.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;


import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.ajth.dao.BaseDao;

/*
 * @author xfd
 * @date 2018-10-31
 * 通用接口的实现类
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	
	private Class clazz;
	/*
	 * 方案二：不想子类上有构造方法，必须在父类中提供无参的构造，在无参构造中获得具体类型的class
	 * 具体类型的Class是参数类型的实际类型参数
	 */
	public BaseDaoImpl() {
		//反射第一步获得Class
		Class clazz = this.getClass();	//正在被调用那个类的Class，CustomerDaoImpl
		//查看JDK的API
		Type type = clazz.getGenericSuperclass();	//参数化类型：BaseDaoImpl<Customer>
//		System.out.println(type);
		//得到这个type就是一个无参数化的类型，将type强转成参数化的的类型
		ParameterizedType pType = (ParameterizedType) type;
		//通过参数化类型获得实际类型参数：得到一个实际类型参数的数组？Map<String, Integer>
		Type[] types = pType.getActualTypeArguments();
		//只获得第一个实际类型的参数即可
		this.clazz = (Class) types[0];
		
	}
	
	/*
	 * 解决查询所有，分页查询，统计个数
	 * 方案一：在实现类的构造方法中传入一个Class
	 
	//提供构造方法：在构造方法中传入具体类型的class
	public BaseDaoImpl(Class clazz) {
		this.clazz = clazz;
	}
	*/
	//保存
	@Override
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}
	
	//修改
	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	//删除
	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	//根据id查询
	@Override
	public T findById(Serializable id) {
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	//查询所有
	@Override
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from "+clazz.getSimpleName());
	}


	//统计总个数
	@Override
	public Integer findCount(DetachedCriteria detachedCriteria) {
		
		//设置统计个数条件
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if(list.size() > 0) {
			return list.get(0).intValue();
		}
		return null;
	}


	//分页查询
	@Override
	public List<T> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
		
		//detachedCriteria设置为null
		detachedCriteria.setProjection(null);
		List<T> list = (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
		return list;
	}

}
