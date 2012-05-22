package com.eastcode.server.system.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eastcode.base.service.impl.BaseServiceImpl;
import com.eastcode.server.system.dao.SystemMenuDao;
import com.eastcode.server.system.domain.SystemMenu;
import com.eastcode.server.system.service.SystemMenuService;

@Service
public class SystemMenuServiceImpl extends BaseServiceImpl implements SystemMenuService {

	@Resource
	private SystemMenuDao systemMenuDao;

	public SystemMenu load(Integer id, boolean isCreate) {
		if (id == null) {
			try {
				if (isCreate)
					return (SystemMenu) SystemMenu.class.newInstance();
				else
					return null;
			} catch (InstantiationException e) {
				throw new RuntimeException(e.getCause());
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e.getCause());
			}
		}
		return systemMenuDao.load(id);
	}

	public SystemMenu load(String id, boolean isCreate) {
		if (id == null) {
			try {
				if (isCreate)
					return (SystemMenu) SystemMenu.class.newInstance();
				else
					return null;
			} catch (InstantiationException e) {
				throw new RuntimeException(e.getCause());
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e.getCause());
			}
		}
		return systemMenuDao.load(id);
	}

	public SystemMenu findById(Integer id) {
		if (id == null)
			return null;
		return systemMenuDao.findById(id);
	}

	public SystemMenu findById(String id) {
		if (id == null)
			return null;
		return systemMenuDao.findById(id);
	}

	public SystemMenu findById(BigDecimal id) {
		if (id == null)
			return null;
		return systemMenuDao.findById(id);
	}

	public List<SystemMenu> queryListByConditions(String conditions, int pageCount, int pageIndex) {
		return systemMenuDao.queryListByConditions(conditions, pageCount, pageIndex);
	}

	public Long queryCountByConditions(String conditions) {
		return systemMenuDao.queryCountByConditions(conditions);
	}

	public void delete(SystemMenu systemMenu) {
		systemMenuDao.delete(systemMenu);
	}

	public int deleteByCondition(String deleteCondition) {
		return systemMenuDao.deleteByCondition(deleteCondition);
	}

	public List<SystemMenu> queryListByConditions(String conditions) {
		return systemMenuDao.queryListByConditions(conditions);
	}
}
