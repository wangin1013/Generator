package com.eastcode.server.system.view;

import java.util.List;

import com.eastcode.base.view.BaseView;
import com.eastcode.server.system.domain.Basedata;
import com.eastcode.server.system.domain.SystemMenu;

public class StationView extends BaseView {

	// 以下为查询条件:类型名称
	private String searchName = "";

	// 类型编码
	private String searchCode = "";

	// 状态
	private String searchStatus = "";

	private List<Basedata> basedataList;

	private List<SystemMenu> systemMenuList;

	private List<SystemMenu> systemMenuAllList;

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

	public List<Basedata> getBasedataList() {
		return basedataList;
	}

	public void setBasedataList(List<Basedata> basedataList) {
		this.basedataList = basedataList;
	}

	public List<SystemMenu> getSystemMenuList() {
		return systemMenuList;
	}

	public void setSystemMenuList(List<SystemMenu> systemMenuList) {
		this.systemMenuList = systemMenuList;
	}

	public List<SystemMenu> getSystemMenuAllList() {
		return systemMenuAllList;
	}

	public void setSystemMenuAllList(List<SystemMenu> systemMenuAllList) {
		this.systemMenuAllList = systemMenuAllList;
	}

}
