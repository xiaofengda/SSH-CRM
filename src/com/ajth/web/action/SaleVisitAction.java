package com.ajth.web.action;

import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.ajth.domain.PageBean;
import com.ajth.domain.SaleVisit;
import com.ajth.service.SaleVisitService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/*
 * @author xfd
 * 拜访记录Action类
 */
public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {

	//模型驱动使用对象
	private SaleVisit saleVisit = new SaleVisit();
	@Override
	public SaleVisit getModel() {
		return saleVisit;
	}
	
	//注入service
	private SaleVisitService saleVisitService;
	public void setSaleVisitService(SaleVisitService saleVisitService) {
		this.saleVisitService = saleVisitService;
	}
	
	//跳转添加拜访记录页面
	public String saveUI() {
		return "saveUI";
	}
	
	//保存拜访记录
	public String save() {
		//调用业务层进行保存
		saleVisitService.save(saleVisit);
		return "save";
		
	}
	
	//分页属性
	private Integer currPage=1;	//当前页
	private Integer pageSize=3;	//每页显示条数
	
	
	public void setCurrPage(Integer currPage) {
		if(currPage == null) {
			currPage = 1;
		}
		this.currPage = currPage;
	}

	public void setPageSize(Integer pageSize) {
		if(pageSize == null) {
			pageSize = 3;
		}
		this.pageSize = pageSize;
	}
	
	//接收数据
	private Date visit_end_time;

	public Date getVisit_end_time() {
		return visit_end_time;
	}

	public void setVisit_end_time(Date visit_end_time) {
		this.visit_end_time = visit_end_time;
	}

	//分页查询拜访列表
	public String findAll() {
		//条件查询带分页
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);
		
		//查询条件
		if(saleVisit.getVisit_detail()!= null && !"".equals(saleVisit.getVisit_detail())) {
			detachedCriteria.add(Restrictions.ge("visit_time", saleVisit.getVisit_time()));
		}
		if(visit_end_time != null) {
			detachedCriteria.add(Restrictions.le("visit_time", visit_end_time));
		}
		//调用业务层查询分页
		PageBean<SaleVisit> pageBean = saleVisitService.findByPage(detachedCriteria, currPage, pageSize);
		//存入值栈
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	//跳转拜访记录修改页面
	public String toUpdate() {
		saleVisit = saleVisitService.findById(saleVisit.getVisit_id());
		ActionContext.getContext().getValueStack().push(saleVisit);
		return "toUpdate";
	}
	
	//修改
	public String update() {
		saleVisitService.update(saleVisit);
		return "update";
		
	}
	
	//删除
	public String delete() {
		saleVisit = saleVisitService.findById(saleVisit.getVisit_id());
		saleVisitService.delete(saleVisit);
		return "delete";
	}
	

}
