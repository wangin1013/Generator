package com.eastcode.server.system.view;

import java.util.List;

import com.eastcode.base.view.BaseView;
import com.eastcode.server.system.domain.Datatype;

public class SystemParamView extends BaseView {

	// 以下为查询条件:类型名称
	private String searchName = "";

	private String searchValue = "";

	// 类型编码
	private String searchCode = "";

	private String searchDatatype = "";

	// 状态
	private String searchStatus = "";

	private List<Datatype> datatypeList;

	public String getSearchCode() {
		return searchCode;
	}

	public void setSearchCode(String searchCode) {
		this.searchCode = searchCode;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getSearchDatatype() {
		return searchDatatype;
	}

	public void setSearchDatatype(String searchDatatype) {
		this.searchDatatype = searchDatatype;
	}

	public String getSearchStatus() {
		return searchStatus;
	}

	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public List<Datatype> getDatatypeList() {
		return datatypeList;
	}

	public void setDatatypeList(List<Datatype> datatypeList) {
		this.datatypeList = datatypeList;
	}
}
