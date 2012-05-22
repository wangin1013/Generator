package com.eastcode.server.system.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eastcode.base.service.impl.BaseServiceImpl;
import com.eastcode.server.system.dao.BasedataDao;
import com.eastcode.server.system.dao.DatatypeDao;
import com.eastcode.server.system.domain.Basedata;
import com.eastcode.server.system.domain.Datatype;
import com.eastcode.server.system.service.BasedataService;

@Service
public class BasedataServiceImpl extends BaseServiceImpl implements BasedataService {

	@Resource
	private BasedataDao basedataDao;

	@Resource
	private DatatypeDao datatypeDao;

	public Basedata load(Integer id, boolean isCreate) {
		if (id == null) {
			try {
				if (isCreate)
					return (Basedata) Basedata.class.newInstance();
				else
					return null;
			} catch (InstantiationException e) {
				throw new RuntimeException(e.getCause());
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e.getCause());
			}
		}
		return basedataDao.load(id);
	}

	public Basedata load(String id, boolean isCreate) {
		if (id == null) {
			try {
				if (isCreate)
					return (Basedata) Basedata.class.newInstance();
				else
					return null;
			} catch (InstantiationException e) {
				throw new RuntimeException(e.getCause());
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e.getCause());
			}
		}
		return basedataDao.load(id);
	}

	public Basedata findById(Integer id) {
		if (id == null)
			return null;
		return basedataDao.findById(id);
	}

	public Basedata findById(String id) {
		if (id == null)
			return null;
		return basedataDao.findById(id);
	}

	public Basedata findById(BigDecimal id) {
		if (id == null)
			return null;
		return basedataDao.findById(id);
	}

	public Datatype findDatatypeById(BigDecimal id) {
		if (id == null)
			return null;

		return datatypeDao.findById(id);
	}

	public List<Basedata> queryListByConditions(String conditions, int pageCount, int pageIndex) {
		return basedataDao.queryListByConditions(conditions, pageCount, pageIndex);
	}

	public List<Basedata> queryListByConditions(String conditions) {
		return basedataDao.queryListByConditions(conditions);
	}

	public List<Datatype> queryDatatypeListByConditions(String conditions) {
		return datatypeDao.queryListByConditions(conditions);
	}

	public Long queryCountByConditions(String conditions) {
		return basedataDao.queryCountByConditions(conditions);
	}

	public void delete(Basedata datatype) {
		basedataDao.delete(datatype);
	}

	public int deleteByCondition(String deleteCondition) {
		return basedataDao.deleteByCondition(deleteCondition);
	}
}
