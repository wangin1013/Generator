package com.eastcode.server.system.view;

import com.eastcode.base.view.BaseView;

public class StationRuleView extends BaseView {

	// 以下为查询条件:功能菜单
	private String searchMenu = "";

	// 岗位
	private String searchStation = "";

	/**
	 * @return the searchMenu
	 */
	public String getSearchMenu() {
		return searchMenu;
	}

	/**
	 * @param searchMenu
	 *            the searchMenu to set
	 */
	public void setSearchMenu(String searchMenu) {
		this.searchMenu = searchMenu;
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
