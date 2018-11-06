<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>添加客户</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入css样式 -->
<LINK href="${pageContext.request.contextPath }/css/Style.css" type="text/css"rel="stylesheet">
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type="text/css"rel="stylesheet">
<!-- 引入js库 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>

<script type="text/javascript">
	$(function(){
		//页面加载完就会执行
		//页面加载，异步查询字典数据
		
		//加载客户来源
		$.post("${pageContext.request.contextPath}/baseDict_findByTypeCode.action",{"dict_type_code":"002"},function(data){
			//遍历JSON
			$(data).each(function(i,n){
				$("#cust_source").append("<option value='"+n.dict_id+"'>"+n.dict_item_name+"</option>");
			});
			
		},"json");
		
		//加载所属行业
		$.post("${pageContext.request.contextPath}/baseDict_findByTypeCode.action",{"dict_type_code":"001"},function(data){
			//遍历JSON
			$(data).each(function(i,n){
				$("#cust_industry").append("<option value='"+n.dict_id+"'>"+n.dict_item_name+"</option>");
			});
		},"json");
		
		//加载客户级别
		$.post("${pageContext.request.contextPath}/baseDict_findByTypeCode.action",{"dict_type_code":"006"},function(data){
			//遍历JSON
			$(data).each(function(i,n){
				$("#cust_level").append("<option value='"+n.dict_id+"'>"+n.dict_item_name+"</option>");
			})
			
		},"json");
		
	});
	
	
	/*
	判断：客户名称，客户级别，来源信息，所属行业，移动电话
	*/
	function check(){
		if(form.cust_name.value==''){
			alert('客户名称不能为空！');
			form.cust_name.focus();
			return false;
		}
		if(form.cust_level.value==''){
			alert('客户级别不能为空');
			form.cust_level.focus();
			return false;
		}
		if(form.cust_source.value==''){
			alert('来源信息不能为空');
			form.cust_source.focus();
			return false;
		}
		if(form.cust_industry.value==''){
			alert('所属行业不能为空');
			form.cust_industry.focus();
			return false;
		}
		if(form.cust_mobile.value==''){
			alert('移动电话不能为空');
			form.cust_mobile.focus();
			return false;
		}
		return true;
}
</script>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<s:fielderror/>
	<FORM id="form" name="form"
		action="${pageContext.request.contextPath }/customer_save.action" enctype="multipart/form-data"
		method="post"  onSubmit="return check();">
		

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
								<TD class=manageHead>当前位置：客户管理 &gt; 添加客户</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						
						<TABLE cellSpacing=0 cellPadding=5  border=0>
						  
						    
							<TR>
								<td>客户名称：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength="50" name="cust_name">
								</td>
								<td>客户级别 ：</td>
								<td>
									<select id="cust_level" name="baseDictLevel.dict_id">
										<option value="">--请选择客户级别--</option>
									</select>
								</td>
							</TR>
							
							<TR>
								
								<td>信息来源 ：</td>
								<td>
									<select id="cust_source" name="baseDictSource.dict_id">
										<option value="">--请选择信息来源--</option>
									</select>
								</td>
								<td>所属行业 ：</td>
								<td>
									<select id="cust_industry" name="baseDictIndustry.dict_id">
										<option value="">--请选择所属行业--</option>
									</select>
								</td>
							</TR>
							
							<TR>
								
								
								<td>固定电话 ：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="cust_phone">
								</td>
								<td>移动电话 ：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="cust_mobile">
								</td>
							</TR>
							
							<TR>
								<td>客户资质 ：</td>
								<td>
								<INPUT type="file" name="upload">
								</td>
							</TR>
							
							
							<tr>
								<td rowspan=2>
								<INPUT class=button id=sButton2 type=submit
														value=" 保存 " name=sButton2">
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
	</FORM>
</BODY>
</HTML>
