package com.ajth.web.action.interceptor;

import org.apache.struts2.ServletActionContext;

import com.ajth.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class PrivilegeInterceptor extends MethodFilterInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//判断session中是否有登陆的用户信息existUser
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(existUser == null) {
			//有错误信息，页面跳转登录页
			ActionSupport actionSupport = (ActionSupport) invocation.getAction();
			actionSupport.addActionError("您还没有登录！没有访问权限");
			return actionSupport.LOGIN;
		}else {
			//以登陆
			return invocation.invoke();
		}
	}

}
