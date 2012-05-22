package com.eastcode.server.system.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eastcode.base.service.impl.BaseServiceImpl;
import com.eastcode.server.system.dao.StationDao;
import com.eastcode.server.system.dao.SystemUserDao;
import com.eastcode.server.system.dao.SystemUserStationDao;
import com.eastcode.server.system.domain.Station;
import com.eastcode.server.system.domain.SystemUser;
import com.eastcode.server.system.domain.SystemUserStation;
import com.eastcode.server.system.service.SystemUserService;

@Service
public class SystemUserServiceImpl extends BaseServiceImpl implements SystemUserService {

	@Resource
	private SystemUserDao systemUserDao;

	@Resource
	private StationDao stationDao;

	@Resource
	private SystemUserStationDao systemUserStationDao;

	public SystemUser load(Integer id, boolean isCreate) {
		if (id == null) {
			try {
				if (isCreate)
					return (SystemUser) SystemUser.class.newInstance();
				else
					return null;
			} catch (InstantiationException e) {
				throw new RuntimeException(e.getCause());
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e.getCause());
			}
		}
		return systemUserDao.load(id);
	}

	public SystemUser load(String id, boolean isCreate) {
		if (id == null) {
			try {
				if (isCreate)
					return (SystemUser) SystemUser.class.newInstance();
				else
					return null;
			} catch (InstantiationException e) {
				throw new RuntimeException(e.getCause());
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e.getCause());
			}
		}
		return systemUserDao.load(id);
	}

	public SystemUser findById(Integer id) {
		if (id == null)
			return null;
		return systemUserDao.findById(id);
	}

	public SystemUser findById(String id) {
		if (id == null)
			return null;
		return systemUserDao.findById(id);
	}

	public SystemUser findById(BigDecimal id) {
		if (id == null)
			return null;
		return systemUserDao.findById(id);
	}

	public SystemUser findUserByUserIdAndPassword(String userId, String password) {
		if (userId == null)
			return null;
		return systemUserDao.findUserByUserIdAndPassword(userId, password);
	}

	public List<SystemUser> queryListByConditions(String conditions, int pageCount, int pageIndex) {
		return systemUserDao.queryListByConditions(conditions, pageCount, pageIndex);
	}

	public Long queryCountByConditions(String conditions) {
		return systemUserDao.queryCountByConditions(conditions);
	}

	public void delete(SystemUser datatype) {
		systemUserDao.delete(datatype);
	}

	public int deleteByCondition(String deleteCondition) {
		return systemUserDao.deleteByCondition(deleteCondition);
	}

	public int saveSystemUsersStations(SystemUser systemUser, String[] stations) {
		String condition = " and system_user_id='" + systemUser.getId() + "'";
		systemUserStationDao.deleteByCondition(condition);

		for (int i = 0; stations != null && i < stations.length; i++) {
			String stationId = stations[i];
			if (stationId != null && stationId.length() > 0) {
				SystemUserStation systemUserStation = new SystemUserStation();
				systemUserStation.setSystemUser(systemUser);
				Station station = stationDao.findById(stationId);
				systemUserStation.setStation(station);
				systemUserStation.setCreateTime(new Date());
				systemUserStationDao.save(systemUserStation);
			}
		}

		return 0;
	}
}
