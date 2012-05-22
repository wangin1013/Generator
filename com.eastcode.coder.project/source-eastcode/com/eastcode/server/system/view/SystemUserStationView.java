package com.eastcode.server.system.view;

import com.eastcode.base.view.BaseView;

public class SystemUserStationView extends BaseView {

	// 以下为查询条件:功能菜单
	private String searchUser = "";

	// 岗位
	private String searchStation = "";

	/**
	 * @return the searchUser
	 */
	public String getSearchUser() {
		return searchUser;
	}

	/**
	 * @param searchUser
	 *            the searchUser to set
	 */
	public void setSearchUser(String searchUser) {
		this.searchUser = searchUser;
	}

	/**
	 * @return the searchStation
	 */
	public String getSearchStation() {
		return searchStation;
	}

	/**
	 * @param searchStation
	 *            the searchStation to set
	 */
	public void setSearchStation(String searchStation) {
		this.searchStation = searchStation;
	}

}
