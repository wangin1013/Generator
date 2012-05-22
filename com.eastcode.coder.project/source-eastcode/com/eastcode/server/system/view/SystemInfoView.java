package com.eastcode.server.system.view;

import java.util.List;

import com.eastcode.base.view.BaseView;
import com.eastcode.server.system.domain.Datatype;

public class SystemInfoView extends BaseView {

	// 以下为查询条件:信息类型
	private String searchType = "";

	// 类型编码
	private String searchCode = "";

	private String searchDatatype = "";

	// 状态
	private String searchStatus = "";

	private List<Datatype> datatypeList;

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
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

	public String getSearchDatatype() {
		return searchDatatype;
	}

	public void setSearchDatatype(String searchDatatype) {
		this.searchDatatype = searchDatatype;
	}

	public List<Datatype> getDatatypeList() {
		return datatypeList;
	}

	public void setDatatypeList(List<Datatype> datatypeList) {
		this.datatypeList = datatypeList;
	}
}
