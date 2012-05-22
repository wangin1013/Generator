package com.eastcode.server.system.view;

import java.util.List;

import com.eastcode.base.view.BaseView;
import com.eastcode.server.system.domain.Station;

public class SystemUserView extends BaseView {

	// 以下为查询条件:类型名称
	private String searchName = "";

	// 类型编码
	private String searchCode = "";

	// 状态
	private String searchStatus = "";

	private List<Station> stationList;

	private List<Station> stationAllList;

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

	public List<Station> getStationList() {
		return stationList;
	}

	public void setStationList(List<Station> stationList) {
		this.stationList = stationList;
	}

	public List<Station> getStationAllList() {
		return stationAllList;
	}

	public void setStationAllList(List<Station> stationAllList) {
		this.stationAllList = stationAllList;
	}

}
