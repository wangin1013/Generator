package com.eastcode.server.system.service;

import java.math.BigDecimal;
import java.util.List;

import com.eastcode.base.service.BaseService;
import com.eastcode.server.system.domain.Basedata;
import com.eastcode.server.system.domain.Datatype;

public interface DatatypeService extends BaseService {

	public Datatype load(Integer id, boolean isCreate);

	public Datatype load(String id, boolean isCreate);

	public Datatype findById(Integer id);

	public Datatype findById(String id);

	public Datatype findById(BigDecimal id);

	public List<Datatype> queryListByConditions(String conditions,
			int pageCount, int pageIndex);

	public List<Basedata> queryBasedataListByType(BigDecimal dataTypeId);

	public Long queryCountByConditions(String conditions);

	public void delete(Datatype datatype);

	public int deleteByCondition(String deleteCondition, String ids);

}
