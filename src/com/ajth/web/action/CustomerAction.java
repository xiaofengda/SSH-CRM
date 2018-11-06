package com.ajth.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.ajth.domain.Customer;
import com.ajth.domain.PageBean;
import com.ajth.service.CustomerService;
import com.ajth.utils.UploadUitls;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/*
 * @author xfd
 * 客户管理Action类
 */
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

	private static final long serialVersionUID = 1L;

	//模型驱动使用对象
	private Customer customer = new Customer();
	@Override
	public Customer getModel() {
		return customer;
	}
	
	//注入service
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	/*
	 * 分页使用到的属性：
	 */
	private Integer currPage=1;	//当前页
	private Integer pageSize=3;	//每页显示条数
	public void setCurrPage(Integer currPage) {
		if(currPage == null) {
			currPage = 1;
		}
		this.currPage = currPage;
	}
	public void setPageSize(Integer pageSize) {
		if (pageSize == null) {
			pageSize = 3;
			
		}
		this.pageSize = pageSize;
	}
	
	//客户列表展示带分页
	public String findAll() {
		
		//接收参数：分页参数
		//最好使用DetachedCriteria对象(条件查询--带分页)
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		
		/*
		 * 设置查询条件
		 * 姓名
		 * 级别
		 * 所属行业
		 * 
		 */
		if(customer.getCust_name()!= null && !"".equals(customer.getCust_name())) {
			detachedCriteria.add(Restrictions.like("cust_name", "%"+customer.getCust_name()+"%"));
		}
		if(customer.getBaseDictSource()!= null) {
			if(customer.getBaseDictSource().getDict_id()!=null && !"".equals(customer.getBaseDictSource().getDict_id())) {
				detachedCriteria.add(Restrictions.eq("baseDictSource.dict_id", customer.getBaseDictSource().getDict_id()));
			}
		}
		if(customer.getBaseDictLevel()!= null) {
			if(customer.getBaseDictLevel().getDict_id()!=null && !"".equals(customer.getBaseDictLevel().getDict_id())) {
				detachedCriteria.add(Restrictions.eq("baseDictLevel.dict_id", customer.getBaseDictLevel().getDict_id()));
			}
		}
		if(customer.getBaseDictIndustry()!= null) {
			if(customer.getBaseDictIndustry().getDict_id()!=null && !"".equals(customer.getBaseDictIndustry().getDict_id())) {
				detachedCriteria.add(Restrictions.eq("baseDictIndustry.dict_id", customer.getBaseDictIndustry().getDict_id()));
			}
		}
		/*
		 * 调用业务层查询分页
		 * detachedCriteria, currPage, pageSize
		 * 
		 */
		PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);	//手动压栈可以直接在前台使用pageBean中的属性展示出来
		
		return "findAll";
		
	}
	
	/*
	 * 图片上传
	 */
	  public File upload;//上传文件
	  public String uploadFileName;//接收到的文件名字
	  public String  uploadContentType;//接收到的文件类型
	  
	  public void setUpload(File upload) {
			this.upload = upload;
		}
		public void setUploadFileName(String uploadFileName) {
			this.uploadFileName = uploadFileName;
		}
		public void setUploadContentType(String uploadContentType) {
			this.uploadContentType = uploadContentType;
		}
	
	//跳转添加页面
	public String saveUI() {
		
		return "saveUI";
	}

	//保存客户
	public String save() throws IOException {
		//文件上传
		if(upload != null) {
			//设置文件上传路径
			String path = "D:/upload";
			//随机文件名
			String uuidFileName = UploadUitls.getUUIDFileName(uploadFileName);
			//目录分离
			String realPath = UploadUitls.getPath(uuidFileName);
			//创建目录
			String url = path+realPath;
			File file = new File(url);
			if(!file.exists()) {
				file.mkdirs();
			}
			
			//文件上传
			File dictFile = new File(url+"/"+uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			customer.setCust_images(url+"/"+uuidFileName);
			
		}
		customerService.save(customer);
		return "save";
	}
	
	/*
	 * 根据id删除客户
	 */
	public String deleteById() {
		//先查后删
		customer = customerService.findById(customer.getCust_id());
		
		//删除图片
		String cust_image = customer.getCust_images();
		if(cust_image != null && !"".equals(cust_image)) {
			File file = new File(cust_image);
			if(file.exists()) {
				file.delete();
			}
		}
		//调用业务层删除客户
		customerService.delete(customer);
		return "deleteSuccess";
		
	}
	
	/*
	 * 跳转修改页面
	 */
	public String toUpdate() {
		/*
		 * 数据回显第一种方式：手动压栈
		 
		customer = customerService.findById(customer.getCust_id());
		ActionContext.getContext().getValueStack().push(customer);
		*/
		//数据回显：第二种模型驱动
		customer = customerService.findById(customer.getCust_id());	//model.cust_name
		return "toUpdate";
		
	}
	
	/*
	 *修改 
	 */
	public String update() throws IOException {
		
		if(upload != null) {
			String cust_image = customer.getCust_images();
			if(cust_image != null && !"".equals(cust_image)) {
				File file = new File(cust_image);
				if(file.exists()) {
					file.delete();
				}
			}
			
			//设置文件上传路径
			String path = "D:/upload";
			//随机文件名
			String uuidFileName = UploadUitls.getUUIDFileName(uploadFileName);
			//目录分离
			String realPath = UploadUitls.getPath(uuidFileName);
			//创建目录
			String url = path+realPath;
			File file = new File(url);
			if(!file.exists()) {
				file.mkdirs();
			}
			
			//文件上传
			File dictFile = new File(url+"/"+uuidFileName);
			FileUtils.copyDirectory(upload, dictFile);
			customer.setCust_images(url+"/"+uuidFileName);
			
		}
			
		//调用业务层查询客户
		customerService.update(customer);
		return "update";
	}
	
	//查询所有拜访客户
	public String findAllCustomer() throws IOException {
		//调业务层进行查询
		List<Customer> list = customerService.findAll();
		
		//格式化json数据，把多余的去掉
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] {"baseDictSource","baseDictIndustry","baseDictLevel","linkmans"});
		
		//转成json
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);

		//设置字符集编码
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}
	
	

}
