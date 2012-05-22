package com.eastcode.server.main.view;

import java.util.List;

import com.eastcode.base.view.BaseView;
import com.eastcode.server.system.domain.SystemMenu;

public class MainView extends BaseView {

	private List<SystemMenu> resultList;

	private String javaVersion;

	private String osName;

	private String osArch;

	private String osVersion;

	private String userDir;

	private String tempDir;

	public List<SystemMenu> getResultList() {
		return resultList;
	}

	public void setResultList(List<SystemMenu> resultList) {
		this.resultList = resultList;
	}

	public String getJavaVersion() {
		return javaVersion;
	}

	public void setJavaVersion(String javaVersion) {
		this.javaVersion = javaVersion;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getOsArch() {
		return osArch;
	}

	public void setOsArch(String osArch) {
		this.osArch = osArch;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getUserDir() {
		return userDir;
	}

	public void setUserDir(String userDir) {
		this.userDir = userDir;
	}

	public String getTempDir() {
		return tempDir;
	}

	public void setTempDir(String tempDir) {
		this.tempDir = tempDir;
	}
}
