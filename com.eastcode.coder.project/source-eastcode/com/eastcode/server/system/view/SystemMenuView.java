package com.eastcode.server.system.view;

import com.eastcode.base.view.BaseView;

public class SystemMenuView extends BaseView {

	// 以下为查询条件:类型名称
	private String searchName = "";

	// 类型编码
	private String searchCode = "";

	private String parentName = "";

	// 是否为叶子节点
	private String isLeaf = "";

	// 状态
	private String searchStatus = "";

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getSearchCode() {
		return searchCode;
	}

	public void setSearchCode(String searchCode) {
		this.searchCode = searchCode;
	}

	public String getSearchStatus() {
		return searchStatus;
	}

	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}
