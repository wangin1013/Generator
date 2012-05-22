package com.eastcode.client.base.view;

import java.util.List;

import com.eastcode.base.view.BaseView;
import com.eastcode.utils.Constants;

public class ClientBaseView extends BaseView {
	private String server = "";

	private String url = "";

	private String queryString = "";

	private String extension;

	private String pageNextIndex = "1";

	private String pagePreIndex = "1";

	private String typeId;

	private String typeName;

	private String showInfo;

	private String title = Constants.SITE_NAME;

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public String getPageNextIndex() {
		return pageNextIndex;
	}

	public void setPageNextIndex(String pageNextIndex) {
		this.pageNextIndex = pageNextIndex;
	}

	public String getPagePreIndex() {
		return pagePreIndex;
	}

	public void setPagePreIndex(String pagePreIndex) {
		this.pagePreIndex = pagePreIndex;
	}

	public String getExtension() {
		extension = ".shtml";
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getShowInfo() {
		return showInfo;
	}

	public void setShowInfo(String showInfo) {
		this.showInfo = showInfo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
