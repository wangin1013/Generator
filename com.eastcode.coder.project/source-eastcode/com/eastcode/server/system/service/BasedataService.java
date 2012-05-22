package com.eastcode.server.system.service;

import java.math.BigDecimal;
import java.util.List;

import com.eastcode.base.service.BaseService;
import com.eastcode.server.system.domain.Basedata;
import com.eastcode.server.system.domain.Datatype;

public interface BasedataService extends BaseService {

	public Basedata load(Integer id, boolean isCreate);

	public Basedata load(String id, boolean isCreate);

	public Basedata findById(Integer id);

	public Basedata findById(String id);

	public Basedata findById(BigDecimal id);

	public Datatype findDatatypeById(BigDecimal id);

	public List<Basedata> queryListByConditions(String conditions, int pageCount, int pageIndex);

	public List<Basedata> queryListByConditions(String conditions);

	public List<Datatype> queryDatatypeListByConditions(String conditions);

	public Long queryCountByConditions(String conditions);

	public void delete(Basedata basedata);

	public int deleteByCondition(String deleteCondition);

}
