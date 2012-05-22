package com.eastcode.server.system.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eastcode.base.service.impl.BaseServiceImpl;
import com.eastcode.server.system.dao.StationDao;
import com.eastcode.server.system.dao.StationRuleDao;
import com.eastcode.server.system.dao.SystemMenuDao;
import com.eastcode.server.system.domain.Station;
import com.eastcode.server.system.domain.StationRule;
import com.eastcode.server.system.domain.SystemMenu;
import com.eastcode.server.system.service.StationService;

@Service
public class StationServiceImpl extends BaseServiceImpl implements StationService {

	@Resource
	private StationDao stationDao;

	@Resource
	private StationRuleDao stationRuleDao;

	@Resource
	private SystemMenuDao systemMenuDao;

	public Station load(Integer id, boolean isCreate) {
		if (id == null) {
			try {
				if (isCreate)
					return (Station) Station.class.newInstance();
				else
					return null;
			} catch (InstantiationException e) {
				throw new RuntimeException(e.getCause());
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e.getCause());
			}
		}
		return stationDao.load(id);
	}

	public Station load(String id, boolean isCreate) {
		if (id == null) {
			try {
				if (isCreate)
					return (Station) Station.class.newInstance();
				else
					return null;
			} catch (InstantiationException e) {
				throw new RuntimeException(e.getCause());
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e.getCause());
			}
		}
		return stationDao.load(id);
	}

	public Station findById(Integer id) {
		if (id == null)
			return null;
		return stationDao.findById(id);
	}

	public Station findById(String id) {
		if (id == null)
			return null;
		return stationDao.findById(id);
	}

	public Station findById(BigDecimal id) {
		if (id == null)
			return null;
		return stationDao.findById(id);
	}

	public List<Station> queryListByConditions(String conditions, int pageCount, int pageIndex) {
		return stationDao.queryListByConditions(conditions, pageCount, pageIndex);
	}

	public List<Station> queryListByConditions(String conditions) {
		return stationDao.queryListByConditions(conditions);
	}

	public Long queryCountByConditions(String conditions) {
		return stationDao.queryCountByConditions(conditions);
	}

	public void delete(Station station) {
		stationDao.delete(station);
	}

	public int deleteByCondition(String deleteCondition) {
		return stationDao.deleteByCondition(deleteCondition);
	}

	public int saveSystemMenus(Station station, String[] systemMenus) {
		String condition = " and station_id='" + station.getId() + "'";
		stationRuleDao.deleteByCondition(condition);

		for (int i = 0; systemMenus != null && i < systemMenus.length; i++) {
			if (null != systemMenus[i] && systemMenus[i].length() > 0) {
				SystemMenu systemMenu = systemMenuDao.findById(systemMenus[i]);
				StationRule stationRule = new StationRule();
				stationRule.setStation(station);
				stationRule.setSystemMenu(systemMenu);
				stationRule.setCreateTime(new Date());
				stationRuleDao.save(stationRule);
			}
		}

		return 0;
	}
}
