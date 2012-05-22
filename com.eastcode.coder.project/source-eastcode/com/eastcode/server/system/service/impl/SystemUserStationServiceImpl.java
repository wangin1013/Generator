package com.eastcode.server.system.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eastcode.base.service.impl.BaseServiceImpl;
import com.eastcode.server.system.dao.SystemUserStationDao;
import com.eastcode.server.system.domain.Station;
import com.eastcode.server.system.domain.SystemUser;
import com.eastcode.server.system.domain.SystemUserStation;
import com.eastcode.server.system.service.SystemUserStationService;

@Service
public class SystemUserStationServiceImpl extends BaseServiceImpl implements SystemUserStationService {

	@Resource
	private SystemUserStationDao systemUserStationDao;

	public SystemUserStation load(Integer id, boolean isCreate) {
		if (id == null) {
			try {
				if (isCreate)
					return (SystemUserStation) SystemUserStation.class.newInstance();
				else
					return null;
			} catch (InstantiationException e) {
				throw new RuntimeException(e.getCause());
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e.getCause());
			}
		}
		return systemUserStationDao.load(id);
	}

	public SystemUserStation load(String id, boolean isCreate) {
		if (id == null) {
			try {
				if (isCreate)
					return (SystemUserStation) SystemUserStation.class.newInstance();
				else
					return null;
			} catch (InstantiationException e) {
				throw new RuntimeException(e.getCause());
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e.getCause());
			}
		}
		return systemUserStationDao.load(id);
	}

	public SystemUserStation findById(Integer id) {
		if (id == null)
			return null;
		return systemUserStationDao.findById(id);
	}

	public SystemUserStation findById(String id) {
		if (id == null)
			return null;
		return systemUserStationDao.findById(id);
	}

	public SystemUserStation findById(BigDecimal id) {
		if (id == null)
			return null;
		return systemUserStationDao.findById(id);
	}

	public List<SystemUserStation> queryListByConditions(String conditions, int pageCount, int pageIndex) {
		return systemUserStationDao.queryListByConditions(conditions, pageCount, pageIndex);
	}

	public Long queryCountByConditions(String conditions) {
		return systemUserStationDao.queryCountByConditions(conditions);
	}

	public void delete(SystemUserStation systemUserStation) {
		systemUserStationDao.delete(systemUserStation);
	}

	public int deleteByCondition(String deleteCondition) {

		// 判断是否已经使用
		return systemUserStationDao.deleteByCondition(deleteCondition);
	}

	public List<Station> querySystemUserListBySystemUser(SystemUser systemUser) {
		return systemUserStationDao.queryStationListBySystemUser(systemUser);
	}
}
