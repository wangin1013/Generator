package com.eastcode.server.system.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eastcode.base.service.impl.BaseServiceImpl;
import com.eastcode.server.system.dao.DatatypeDao;
import com.eastcode.server.system.domain.Basedata;
import com.eastcode.server.system.domain.Datatype;
import com.eastcode.server.system.service.DatatypeService;

@Service
public class DatatypeServiceImpl extends BaseServiceImpl implements DatatypeService {

	@Resource
	private DatatypeDao datatypeDao;

	public Datatype load(Integer id, boolean isCreate) {
		if (id == null) {
			try {
				if (isCreate)
					return (Datatype) Datatype.class.newInstance();
				else
					return null;
			} catch (InstantiationException e) {
				throw new RuntimeException(e.getCause());
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e.getCause());
			}
		}
		return datatypeDao.load(id);
	}

	public Datatype load(String id, boolean isCreate) {
		if (id == null) {
			try {
				if (isCreate)
					return (Datatype) Datatype.class.newInstance();
				else
					return null;
			} catch (InstantiationException e) {
				throw new RuntimeException(e.getCause());
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e.getCause());
			}
		}
		return datatypeDao.load(id);
	}

	public Datatype findById(Integer id) {
		if (id == null)
			return null;
		return datatypeDao.findById(id);
	}

	public Datatype findById(String id) {
		if (id == null)
			return null;
		return datatypeDao.findById(id);
	}

	public Datatype findById(BigDecimal id) {
		if (id == null)
			return null;
		return datatypeDao.findById(id);
	}

	public List<Datatype> queryListByConditions(String conditions, int pageCount, int pageIndex) {
		return datatypeDao.queryListByConditions(conditions, pageCount, pageIndex);
	}

	public List<Basedata> queryBasedataListByType(BigDecimal dataTypeId) {
		String condition = " and type ='" + dataTypeId + "' order by code";
		return datatypeDao.findBasedataListByCondition(condition);
	}

	public Long queryCountByConditions(String conditions) {
		return datatypeDao.queryCountByConditions(conditions);
	}

	public void delete(Datatype datatype) {
		datatypeDao.delete(datatype);
	}

	public int deleteByCondition(String deleteCondition, String ids) {

		// 判断是否已经使用
		String condition = " and type in(" + ids + ")";
		int childSize = datatypeDao.findBasedataByCondition(condition);
		if (childSize == 0) {
			return datatypeDao.deleteByCondition(deleteCondition);
		} else {
			return 0;
		}
	}
}
