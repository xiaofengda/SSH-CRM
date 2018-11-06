package com.ajth.service;

import org.hibernate.criterion.DetachedCriteria;

import com.ajth.domain.PageBean;
import com.ajth.domain.SaleVisit;

/*
 * @author xfd
 * 拜访记录接口
 */
public interface SaleVisitService {

	//拜访记录保存
	void save(SaleVisit saleVisit);

	//拜访记录列表查询带分页
	PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	//根据id查询
	SaleVisit findById(String visit_id);

	//修改
	void update(SaleVisit saleVisit);

	//删除
	void delete(SaleVisit saleVisit);

}
