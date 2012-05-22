package com.eastcode.server.system.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eastcode.base.service.impl.BaseServiceImpl;
import com.eastcode.server.system.dao.SystemParamDao;
import com.eastcode.server.system.domain.SystemParam;
import com.eastcode.server.system.service.SystemParamService;

@Service
public class SystemParamServiceImpl extends BaseServiceImpl implements SystemParamService {

	@Resource
	private SystemParamDao systemParamDao;

	public SystemParam load(Integer id, boolean isCreate) {
		if (id == null) {
			try {
				if (isCreate)
					return (SystemParam) SystemParam.class.newInstance();
				else
					return null;
			} catch (InstantiationException e) {
				throw new RuntimeException(e.getCause());
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e.getCause());
			}
		}
		return systemParamDao.load(id);
	}

	public SystemParam load(String id, boolean isCreate) {
		if (id == null) {
			try {
				if (isCreate)
					return (SystemParam) SystemParam.class.newInstance();
				else
					return null;
			} catch (InstantiationException e) {
				throw new RuntimeException(e.getCause());
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e.getCause());
			}
		}
		return systemParamDao.load(id);
	}

	public SystemParam findById(Integer id) {
		if (id == null)
			return null;
		return systemParamDao.findById(id);
	}

	public SystemParam findById(String id) {
		if (id == null)
			return null;
		return systemParamDao.findById(id);
	}

	public SystemParam findById(BigDecimal id) {
		if (id == null)
			return null;
		return systemParamDao.findById(id);
	}

	public List<SystemParam> queryListByConditions(String conditions, int pageCount, int pageIndex) {
		return systemParamDao.queryListByConditions(conditions, pageCount, pageIndex);
	}

	public Long queryCountByConditions(String conditions) {
		return systemParamDao.queryCountByConditions(conditions);
	}

	public void delete(SystemParam systemParam) {
		systemParamDao.delete(systemParam);
	}

	public int deleteByCondition(String deleteCondition) {
		return systemParamDao.deleteByCondition(deleteCondition);
	}
}
