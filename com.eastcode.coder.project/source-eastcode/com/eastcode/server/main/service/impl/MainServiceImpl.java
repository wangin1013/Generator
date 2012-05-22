package com.eastcode.server.main.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eastcode.base.service.impl.BaseServiceImpl;
import com.eastcode.server.main.dao.MainDao;
import com.eastcode.server.main.service.MainService;
import com.eastcode.server.system.dao.SystemMenuDao;
import com.eastcode.server.system.domain.SystemMenu;
import com.eastcode.server.system.domain.SystemUser;
import com.eastcode.utils.Constants;

@Service
public class MainServiceImpl extends BaseServiceImpl implements MainService {

	@Resource
	private SystemMenuDao systemMenuDao;

	@Resource
	private MainDao mainDao;

	public List<SystemMenu> querySysteMenu(SystemUser systemUser) {
		List<SystemMenu> resultList = null;
		Map<BigDecimal, SystemMenu> resultMap = new TreeMap<BigDecimal, SystemMenu>();
		try {

			// 检索所有的菜单项目
			List<SystemMenu> allResultList = systemMenuDao.queryListByConditions(" and status='" + Constants.ENABLE
					+ "'");
			for (int i = 0; allResultList != null && i < allResultList.size(); i++) {
				SystemMenu systemMenu = allResultList.get(i);
				resultMap.put(systemMenu.getId(), systemMenu);
			}

			// 根据权限检索该用户对应的授权菜单
			String conditions = " and systemUser.id='" + systemUser.getId() + "' and systemMenu.status='"
					+ Constants.ENABLE + "'";
			resultList = mainDao.queryMenuList(conditions);
			Map<BigDecimal, SystemMenu> newResultMap = new TreeMap<BigDecimal, SystemMenu>();
			for (int i = 0; resultList != null && i < resultList.size(); i++) {
				SystemMenu systemMenu = resultList.get(i);
				while (systemMenu.getParentId() != null) {
					SystemMenu parent = resultMap.get(systemMenu.getParentId());
					parent.addChild(systemMenu);
					newResultMap.put(parent.getId(), parent);
					systemMenu = parent;
				}
			}

			// 获取父节点
			Iterator<Map.Entry<BigDecimal, SystemMenu>> iterator = newResultMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<BigDecimal, SystemMenu> entry = iterator.next();
				resultList.add(entry.getValue());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		List<SystemMenu> newResultList = new ArrayList<SystemMenu>();
		// 将变动过叶子节点的复制到结果集中
		for (int i = 0; resultList != null && i < resultList.size(); i++) {
			SystemMenu systeMenu = resultList.get(i);
			newResultList.add(resultMap.get(systeMenu.getId()));
		}
		return newResultList;
	}
}
