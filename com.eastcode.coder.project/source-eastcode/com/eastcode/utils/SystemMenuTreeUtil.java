package com.eastcode.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.eastcode.server.system.domain.SystemMenu;

public class SystemMenuTreeUtil {

	/**
	 * 用于生成子节点的树状结构
	 * 
	 * @param systemMenu
	 * @return
	 */
	public static String createChildMenu(SystemMenu systemMenu) {
		StringBuffer sb = systemMenu.getChildBuf();
		sb.append("nodes:[");
		List<SystemMenu> childList = new ArrayList<SystemMenu>();

		Iterator<Map.Entry<BigDecimal, SystemMenu>> iterator = systemMenu
				.getChildMap().entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<BigDecimal, SystemMenu> entry = iterator.next();
			childList.add(entry.getValue());
		}
		for (int i = 0; childList != null && i < childList.size(); i++) {
			SystemMenu child = childList.get(i);
			sb.append(createMenu(child));
			if (i != childList.size() - 1)
				sb.append(",");
		}
		sb.append("]");
		return sb.toString();
	}

	/**
	 * 用于生成树状节点
	 * 
	 * @return
	 */
	public static String createMenu(SystemMenu systemMenu) {
		StringBuffer sb = new StringBuffer();
		sb.append("{ name:");
		sb.append("\"");
		sb.append(systemMenu.getName());
		sb.append("\"");
		sb.append(",");

		if (systemMenu.getChildMap().size() > 0) {
			sb.append("\n");
			sb.append(createChildMenu(systemMenu));
		} else {
			if (systemMenu.getImg() != null) {
				sb.append("iconSkin:");
				sb.append("\"");
				sb.append(systemMenu.getImg());
				sb.append("\"");
				sb.append(",");
			}

			if (systemMenu.getUrl() != null) {
				sb.append("url:");
				sb.append("\"");
				sb.append(systemMenu.getUrl());
				sb.append("\"");
				sb.append(",");
			}

			sb.append("target:");
			sb.append("\"");
			// sb.append(this.target);
			sb.append("contentFrame");
			sb.append("\"");
		}
		sb.append(" }");
		return sb.toString();
	}
}
