package com.eastcode.server.system.service;

import java.math.BigDecimal;
import java.util.List;

import com.eastcode.base.service.BaseService;
import com.eastcode.server.system.domain.SystemParam;

public interface SystemParamService extends BaseService {

	public SystemParam load(Integer id, boolean isCreate);

	public SystemParam load(String id, boolean isCreate);

	public SystemParam findById(Integer id);

	public SystemParam findById(String id);

	public SystemParam findById(BigDecimal id);

	public List<SystemParam> queryListByConditions(String conditions,
			int pageCount, int pageIndex);

	public Long queryCountByConditions(String conditions);

	public void delete(SystemParam systemParam);

	public int deleteByCondition(String deleteCondition);

}
