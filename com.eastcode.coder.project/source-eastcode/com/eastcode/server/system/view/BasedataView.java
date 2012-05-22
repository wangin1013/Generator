package com.eastcode.server.system.view;

import java.util.List;

import com.eastcode.base.view.BaseView;
import com.eastcode.server.system.domain.Basedata;
import com.eastcode.server.system.domain.Datatype;

public class BasedataView extends BaseView {

	// 以下为查询条件:类型名称
	private String searchName = "";

	// 类型编码
	private String searchCode = "";

	private String searchDatatype = "";

	// 状态
	private String searchStatus = "";

	private List<Datatype> datatypeList;
	
	private List<Basedata> otherList;

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

	/**
	 * @return the otherList
	 */
	public List<Basedata> getOtherList() {
		return otherList;
	}

	/**
	 * @param otherList the otherList to set
	 */
	public void setOtherList(List<Basedata> otherList) {
		this.otherList = otherList;
	}
}
