package com.ajth.domain;

import java.util.List;

/*
 * @author xfd
 * 分页pojo类
 */
public class PageBean<T> {
	private Integer tatalcount; //总记录条数
	private Integer totalPage;	//总页数
	private Integer currPage; //当前页
	private Integer pageSize; //每页显示条数
	private List<T> list; //每页查询到的数据集合
	public Integer getTatalcount() {
		return tatalcount;
	}
	public void setTatalcount(Integer tatalcount) {
		this.tatalcount = tatalcount;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getCurrPage() {
		return currPage;
	}
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}

	

}
