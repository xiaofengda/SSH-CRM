package com.ajth.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.ajth.domain.User;
import com.ajth.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
/*
 * @author xfd
 * 用户Action类
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {
	
	private static final long serialVersionUID = 1L;
	
	//模型驱动使用的对象
	private User user = new User();
	@Override
	public User getModel() {
		
		return user;
	}
	
	//注入service
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	//用户注册
	public String regist() throws Exception{
		
		//调用业务层
		userService.save(user);
		return "save";
	}
	
	//登录
	public String login() {
		//调用业务层查询用户
		User existUser = userService.login(user);
		
		if(existUser == null) {	//登录失败
			//添加错误信息提示
			this.addActionError("用户名或者密码错误！");	
			return LOGIN;
		}else {	//登陆成功
			//原生方式向Session中保存数据
			ServletActionContext.getRequest().getSession().setAttribute("existUser",existUser);
			return SUCCESS;
		}
		
	}
	
	//查询拜访记录中所有的业务员
	public String findAllUser() throws IOException {
		List<User> list = userService.findAll();
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] {"user_password","user_state","user_code"});
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}
	
	//修改密码
	public String toUpdate() {
		System.out.println("用户模块的修改密码功能方法执行了。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。");
		return "toUpdate";
	}
}
