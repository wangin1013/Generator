package com.eastcode.server.system.view;

import java.util.List;

import com.eastcode.base.view.BaseView;
import com.eastcode.server.system.domain.Basedata;

public class AdvertisementView extends BaseView {

	// 以下为查询条件:广告标题
	private String searchTitle = "";

	// 类型编码
	private String searchCode = "";

	// 状态
	private String searchStatus = "";

	private List<Basedata> basedataList;

	public String getSearchTitle() {
		return searchTitle;
	}

	public void setSearchTitle(String searchTitle) {
		this.searchTitle = searchTitle;
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

}
