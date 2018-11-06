package com.ajth.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.ajth.dao.SaleVisitDao;
import com.ajth.domain.PageBean;
import com.ajth.domain.SaleVisit;
import com.ajth.service.SaleVisitService;

/*
 * @author xfd
 * 客户拜访记录接口实现类
 */
@Transactional
public class SaleVisitServiceImpl implements SaleVisitService {
	
	//注入dao
	private SaleVisitDao saleVisitDao;

	public void setSaleVisitDao(SaleVisitDao saleVisitDao) {
		this.saleVisitDao = saleVisitDao;
	}

	//拜访记录保存
	@Override
	public void save(SaleVisit saleVisit) {
		saleVisitDao.save(saleVisit);
	}

	//拜访记录列表查询带分页
	@Override
	public PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<SaleVisit> pageBean = new PageBean<SaleVisit>();
		//封装当前页
		pageBean.setCurrPage(currPage);
		//封装每页显示条数
		pageBean.setPageSize(pageSize);
		//封装总记录条数
		Integer tatalcount = saleVisitDao.findCount(detachedCriteria);
		pageBean.setTatalcount(tatalcount);
		//封装总页数
		Double tc = tatalcount.doubleValue();
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		//封装每页显示数据的集合
		Integer begin = (currPage - 1) * pageSize;
		List<SaleVisit> list = saleVisitDao.findByPage(detachedCriteria, begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//根据id查询
	@Override
	public SaleVisit findById(String visit_id) {
		return saleVisitDao.findById(visit_id);
	}

	//修改
	@Override
	public void update(SaleVisit saleVisit) {
		saleVisitDao.update(saleVisit);
	}

	//删除
	@Override
	public void delete(SaleVisit saleVisit) {
		saleVisitDao.delete(saleVisit);
	}
	

}
