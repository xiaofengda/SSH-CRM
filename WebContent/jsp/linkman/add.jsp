﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%--   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
    <%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>添加联系人</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>


<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<s:form id="form1" name="form1" action="linkman_save" namespace="/" method="post" theme="simple">
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
						 height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：联系人管理 &gt; 添加联系人</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE cellSpacing=0 cellPadding=5  border=0>
						
							<tr>
								<td>所属客户：</td>
								<td colspan="3">
									<s:select list="list" id="cust_id" name="customer.cust_id" headerKey=""
										headerValue="--请选择所属客户--" listKey="cust_id" listValue="cust_name" >
									</s:select>
								</td>
							</tr>
							
							<tr>
							<td>联系人名称：</td>
								<td>
									<s:textfield cssClass="textbox" id="lkm_name" name="lkm_name" cssStyle="WIDTH: 180px" maxLength="50"/>
								</td>
								
								<td>联系人性别：</td>
								<td>
									<s:radio list="#{'1':'男','2':'女' }" id="sChannel2" name="lkm_gender" value="%{model.lkm_gender}"/>
								<td>
							</tr>
							<tr>
								<td>联系人办公电话 ：</td>
								<td>
									<s:textfield cssClass="textbox" id="sChannel2" name="lkm_phone" cssStyle="WIDTH: 180px" maxLength="50"/>
								</td>
								<td>联系人手机 ：</td>
								<td>
									<s:textfield cssClass="textbox" id="sChannel2" name="lkm_mobile" cssStyle="WIDTH: 180px" maxLength="50"/>
								</td>
							</tr>
							
							<tr>
								<td>联系人邮箱 ：</td>
								<td>
									<s:textfield cssClass="textbox" id="sChannel2" name="lkm_email" cssStyle="WIDTH: 180px" maxLength="50"/>
								</td>
								<td>联系人QQ ：</td>
								<td>
									<s:textfield cssClass="textbox" id="sChannel2" name="lkm_qq" cssStyle="WIDTH: 180px" maxLength="50"/>
								</td>
							</tr>
							
							<tr>
								<td>联系人职位 ：</td>
								<td>
									<s:textfield cssClass="textbox" id="sChannel2" name="lkm_position" cssStyle="WIDTH: 180px" maxLength="50"/>
								</td>
								<td>联系人备注 ：</td>
								<td>
									<s:textfield cssClass="textbox" id="sChannel2" name="lkm_memo" cssStyle="WIDTH: 180px" maxLength="50"/>
								</td>
							</tr>
							<tr>
								<td rowspan=2>
								<input class=button id=sButton2 type=submit
														value="保存 " name=sButton2>
								</td>
							</tr>
						</TABLE>
						
						
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
					<IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
						border=0></TD>
					<TD align=middle width="100%"
						background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</s:form>
</BODY>
</HTML>
