package com.eastcode.server.main.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eastcode.base.dao.BaseDao;
import com.eastcode.server.system.domain.SystemMenu;

/**
 * 该Dao主要服务于MainService
 * 
 * @author 王一进
 */
@Repository
public class MainDao extends BaseDao {

	public List<SystemMenu> queryMenuList(String conditions) throws Exception {

		String sql = "select systemMenu.* from SYSTEM_USER_STATION systemUserStation "
				+ " left join STATION_RULE stationRule on systemUserStation.STATION_ID=stationRule.STATION_ID"
				+ " left join SYSTEM_MENU systemMenu on stationRule.FUNCTION_ID=systemMenu.ID"
				+ " left join SYSTEM_USER systemUser on systemUser.ID =systemUserStation.SYSTEM_USER_ID"
				+ " where 1=1 " + conditions + " order by systemMenu.CODE asc ";
		List<Map<String, Object>> queryList = getJdbcTemplate().queryForList(sql);

		List<SystemMenu> resultList = new ArrayList<SystemMenu>();

		for (int i = 0; queryList != null && i < queryList.size(); i++) {
			Map<String, Object> resultMap = queryList.get(i);
			Field[] fields = SystemMenu.class.getDeclaredFields();
			SystemMenu systemMenu = new SystemMenu();
			for (int j = 0; fields != null && j < fields.length; j++) {
				String fieldName = fields[j].getName();
				Object object = resultMap.get(fieldName.toUpperCase());
				if (object != null) {

					fieldName = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
					if (object.getClass().equals(Integer.class)) {
						Method method = systemMenu.getClass().getMethod("set" + fieldName, BigDecimal.class);
						method.invoke(systemMenu, new BigDecimal(object.toString()));
					} else if (object.getClass().equals(BigDecimal.class)) {
						Method method = systemMenu.getClass().getMethod("set" + fieldName, BigDecimal.class);
						method.invoke(systemMenu, object);
					} else {
						Method method = systemMenu.getClass().getMethod("set" + fieldName, object.getClass());
						method.invoke(systemMenu, object);
					}
				}
			}

			if (resultMap.get("PARENT_ID") != null && resultMap.get("PARENT_ID").getClass().equals(Integer.class)) {
				systemMenu.setParentId(new BigDecimal(resultMap.get("PARENT_ID").toString()));
			} else {
				systemMenu.setParentId((BigDecimal) resultMap.get("PARENT_ID"));
			}
			resultList.add(systemMenu);
		}

		return resultList;
	}
}