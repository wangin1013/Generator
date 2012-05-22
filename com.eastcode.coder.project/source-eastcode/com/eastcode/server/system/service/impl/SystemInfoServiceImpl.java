package com.eastcode.server.system.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eastcode.base.service.impl.BaseServiceImpl;
import com.eastcode.server.system.dao.SystemInfoDao;
import com.eastcode.server.system.domain.SystemInfo;
import com.eastcode.server.system.service.SystemInfoService;

@Service
public class SystemInfoServiceImpl extends BaseServiceImpl implements SystemInfoService {

	@Resource
	private SystemInfoDao systemInfoDao;

	public SystemInfo load(Integer id, boolean isCreate) {
		if (id == null) {
			try {
				if (isCreate)
					return (SystemInfo) SystemInfo.class.newInstance();
				else
					return null;
			} catch (InstantiationException e) {
				throw new RuntimeException(e.getCause());
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e.getCause());
			}
		}
		return systemInfoDao.load(id);
	}

	public SystemInfo load(String id, boolean isCreate) {
		if (id == null) {
			try {
				if (isCreate)
					return (SystemInfo) SystemInfo.class.newInstance();
				else
					return null;
			} catch (InstantiationException e) {
				throw new RuntimeException(e.getCause());
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e.getCause());
			}
		}
		return systemInfoDao.load(id);
	}

	public SystemInfo findById(Integer id) {
		if (id == null)
			return null;
		return systemInfoDao.findById(id);
	}

	public SystemInfo findById(String id) {
		if (id == null)
			return null;
		return systemInfoDao.findById(id);
	}

	public SystemInfo findById(BigDecimal id) {
		if (id == null)
			return null;
		return systemInfoDao.findById(id);
	}

	public List<SystemInfo> queryListByConditions(String conditions, int pageCount, int pageIndex) {
		return systemInfoDao.queryListByConditions(conditions, pageCount, pageIndex);
	}

	public List<SystemInfo> queryListByConditions(String conditions) {
		return systemInfoDao.queryListByConditions(conditions);
	}

	public Long queryCountByConditions(String conditions) {
		return systemInfoDao.queryCountByConditions(conditions);
	}

	public void delete(SystemInfo systemInfo) {
		systemInfoDao.delete(systemInfo);
	}

	public int deleteByCondition(String deleteCondition) {
		return systemInfoDao.deleteByCondition(deleteCondition);
	}
}
