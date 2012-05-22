package com.eastcode.server.system.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eastcode.base.service.impl.BaseServiceImpl;
import com.eastcode.server.system.dao.StationRuleDao;
import com.eastcode.server.system.domain.Station;
import com.eastcode.server.system.domain.StationRule;
import com.eastcode.server.system.domain.SystemMenu;
import com.eastcode.server.system.service.StationRuleService;

@Service
public class StationRuleServiceImpl extends BaseServiceImpl implements StationRuleService {

	@Resource
	private StationRuleDao stationRuleDao;

	public StationRule load(Integer id, boolean isCreate) {
		if (id == null) {
			try {
				if (isCreate)
					return (StationRule) StationRule.class.newInstance();
				else
					return null;
			} catch (InstantiationException e) {
				throw new RuntimeException(e.getCause());
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e.getCause());
			}
		}
		return stationRuleDao.load(id);
	}

	public StationRule load(String id, boolean isCreate) {
		if (id == null) {
			try {
				if (isCreate)
					return (StationRule) StationRule.class.newInstance();
				else
					return null;
			} catch (InstantiationException e) {
				throw new RuntimeException(e.getCause());
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e.getCause());
			}
		}
		return stationRuleDao.load(id);
	}

	public StationRule findById(Integer id) {
		if (id == null)
			return null;
		return stationRuleDao.findById(id);
	}

	public StationRule findById(String id) {
		if (id == null)
			return null;
		return stationRuleDao.findById(id);
	}

	public StationRule findById(BigDecimal id) {
		if (id == null)
			return null;
		return stationRuleDao.findById(id);
	}

	public List<StationRule> queryListByConditions(String conditions, int pageCount, int pageIndex) {
		return stationRuleDao.queryListByConditions(conditions, pageCount, pageIndex);
	}

	public List<StationRule> queryListByConditions(String conditions) {
		return stationRuleDao.queryListByConditions(conditions);
	}

	public Long queryCountByConditions(String conditions) {
		return stationRuleDao.queryCountByConditions(conditions);
	}

	public void delete(StationRule stationRule) {
		stationRuleDao.delete(stationRule);
	}

	public int deleteByCondition(String deleteCondition) {

		// 判断是否已经使用
		return stationRuleDao.deleteByCondition(deleteCondition);
	}

	public List<SystemMenu> querySystemMenuListByStation(Station station) {
		return stationRuleDao.querySystemMenuListByStation(station);
	}
}
