package com.zcs.storems.util;

import java.util.List;

@SuppressWarnings("rawtypes")
public class Pagination {
	private Integer pageSize = 10;
	private Integer currentPage = 1;
	private Integer pageCount;
	private Integer resultCount;
	private Integer firstResult;
	private String[] orders;
	private List list;

	// default constructor
	public Pagination() {
	}

	// order constructor
	public Pagination(String[] orders) {
		this.orders = orders;
	}

	// full constructor
	public Pagination(Integer pageSize, Integer currentPage, Integer pageCount, Integer resultCount, Integer firstResult, List list) {
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.pageCount = pageCount;
		this.resultCount = resultCount;
		this.firstResult = firstResult;
		this.list = list;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageCount() {
		if (resultCount % pageSize == 0) {
			this.pageCount = resultCount / pageSize;
		} else {
			this.pageCount = resultCount / pageSize + 1;
		}
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getResultCount() {
		return resultCount;
	}

	public void setResultCount(Integer resultCount) {
		this.resultCount = resultCount;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Integer getFirstResult() {
		this.firstResult = this.pageSize * (this.currentPage - 1);
		return firstResult;
	}

	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}

	public String[] getOrders() {
		return orders;
	}

	public void setOrders(String[] orders) {
		this.orders = orders;
	}
}
