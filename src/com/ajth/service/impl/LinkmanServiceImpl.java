package com.ajth.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.ajth.dao.LinkmanDao;
import com.ajth.domain.Linkman;
import com.ajth.domain.PageBean;
/*
 * @author xfd
 * 联系人管理业务层接口实现类
 */
import com.ajth.service.LinkmanService;

//开启事务
@Transactional
public class LinkmanServiceImpl implements LinkmanService {
	
	//注入dao
	private LinkmanDao linkmanDao;

	public void setLinkmanDao(LinkmanDao linkmanDao) {
		this.linkmanDao = linkmanDao;
	}
	
	//联系人管理分页查询//封装当前页
	@Override
	public PageBean<Linkman> findPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		//创建对象
		PageBean<Linkman> pageBean = new PageBean<Linkman>();
		//封装当前页
		pageBean.setCurrPage(currPage);
		//封装每页显示条数
		pageBean.setPageSize(pageSize);
		//封装总记录条数：
		Integer tatalcount =  linkmanDao.findCount(detachedCriteria);
		pageBean.setTatalcount(tatalcount);
		//封装总页数
		Double tc = tatalcount.doubleValue();
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		//封装每页显示数据的集合
		Integer begin = (currPage - 1) * pageSize;
		List<Linkman> list = linkmanDao.findByPage(detachedCriteria, begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//根据id查询
	@Override
	public Linkman findById(Long lkm_id) {
		//调用dao层
		return linkmanDao.findById(lkm_id);
	}

	//保存联系人
	@Override
	public void save(Linkman linkman) {
		//调用dao
		linkmanDao.save(linkman);
	}

	//修改联系人
	@Override
	public void update(Linkman linkman) {
		//调用dao
		linkmanDao.update(linkman);
		
	}

	//删除联系人
	@Override
	public void delete(Linkman linkman) {
		//调用dao
		linkmanDao.delete(linkman);
	}
}
