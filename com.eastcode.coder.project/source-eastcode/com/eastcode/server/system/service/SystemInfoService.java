package com.eastcode.server.system.service;

import java.math.BigDecimal;
import java.util.List;

import com.eastcode.base.service.BaseService;
import com.eastcode.server.system.domain.SystemInfo;

public interface SystemInfoService extends BaseService {

	public SystemInfo load(Integer id, boolean isCreate);

	public SystemInfo load(String id, boolean isCreate);

	public SystemInfo findById(Integer id);

	public SystemInfo findById(String id);

	public SystemInfo findById(BigDecimal id);

	public List<SystemInfo> queryListByConditions(String conditions, int pageCount, int pageIndex);

	public List<SystemInfo> queryListByConditions(String conditions);

	public Long queryCountByConditions(String conditions);

	public void delete(SystemInfo systemInfo);

	public int deleteByCondition(String deleteCondition);

}
