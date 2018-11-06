package com.ajth.service;


import org.hibernate.criterion.DetachedCriteria;

import com.ajth.domain.Linkman;
import com.ajth.domain.PageBean;

/*
 * @author xfd
 * 联系人管理业务层接口
 */
public interface LinkmanService {

	//分页查询
	public PageBean<Linkman> findPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	//根据id查询
	public Linkman findById(Long lkm_id);

	//保存联系人
	public void save(Linkman linkman);

	//修改联系人
	public void update(Linkman linkman);

	//删除联系人
	public void delete(Linkman linkman);
}
