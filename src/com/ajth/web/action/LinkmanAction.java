package com.ajth.web.action;


import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.ajth.domain.Customer;
import com.ajth.domain.Linkman;
import com.ajth.domain.PageBean;
import com.ajth.service.CustomerService;
import com.ajth.service.LinkmanService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/*
 * @author xfd
 * 客户管理Action类
 */
public class LinkmanAction extends ActionSupport implements ModelDriven<Linkman> {

	private static final long serialVersionUID = 1L;
	//模型驱动使用对象
	private Linkman linkman = new Linkman();
	@Override
	public Linkman getModel() {
		
		return linkman;
	}
	
	//注入service
	private LinkmanService linkmanService;
	
	public void setLinkmanService(LinkmanService linkmanService) {
		this.linkmanService = linkmanService;
	}
	
	//注入客户管理的service
	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}


	/*
	 * 跳转新建联系人页面
	 */
	public String saveUI() {
		//查询所有客户
		List<Customer> list = customerService.findAll();
		//将list集合保存到值栈中
		ActionContext.getContext().getValueStack().set("list",list);
		return "saveUI";
	}
	
	/*
	 * 保存联系人
	 */
	public String save() {
		//调用业务层
		linkmanService.save(linkman);
		return "save";
	}
	
	//分页所使用的属性
	private Integer currPage=1;	//当前页
	private Integer pageSize=3;	//每页显示条数

	public void setCurrPage(Integer currPage) {
		
		if(currPage == null) {
			currPage=1;
		}
		this.currPage = currPage;
	}


	public void setPageSize(Integer pageSize) {
		
		if (pageSize == null) {
			pageSize=3;
			
		}
		this.pageSize = pageSize;
	}


	/*
	 * 查询联系人列表
	 */
	public String findAll() {
		
		//使用DetachedCriteria对象(条件查询--带分页)
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Linkman.class);
		
		//查询条件:name
		if(linkman.getLkm_name()!=null && !"".equals(linkman.getLkm_name())) {
			detachedCriteria.add(Restrictions.like("lkm_name", "%"+linkman.getLkm_name()+"%"));
		}
		//查询条件：gender
		if(linkman.getLkm_gender()!=null && !"".equals(linkman.getLkm_gender())) {
			detachedCriteria.add(Restrictions.eq("lkm_gender", linkman.getLkm_gender()));
		}
		
		//调用业务层查询分页
		PageBean<Linkman> pageBean = linkmanService.findPage(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	/*
	 * 跳转修改页面
	 */
	public String toUpdate() {
		//查询某个联系人，查询所有客户
		List<Customer> list = customerService.findAll();
		//根据id查询联系人
		linkman = linkmanService.findById(linkman.getLkm_id());
		//将list和linkman的对象带到页面
		ActionContext.getContext().getValueStack().set("list", list);
		//将对象的值存入值栈
		ActionContext.getContext().getValueStack().push(linkman);
		return "toUpdate";
	}
	
	/*
	 * 修改
	 */
	public String update() {
		//调用业务层
		linkmanService.update(linkman);
		return "update";
		
	}
	
	/*
	 * 删除根据id
	 */
	public String delete() {
		//先查询
		linkman = linkmanService.findById(linkman.getLkm_id());
		//删除
		linkmanService.delete(linkman);
		return "delete";
	}

}
