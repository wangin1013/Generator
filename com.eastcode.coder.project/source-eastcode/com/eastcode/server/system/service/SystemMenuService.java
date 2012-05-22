package com.eastcode.server.system.service;

import java.math.BigDecimal;
import java.util.List;

import com.eastcode.base.service.BaseService;
import com.eastcode.server.system.domain.SystemMenu;

public interface SystemMenuService extends BaseService {

	public SystemMenu load(Integer id, boolean isCreate);

	public SystemMenu load(String id, boolean isCreate);

	public SystemMenu findById(Integer id);

	public SystemMenu findById(String id);

	public SystemMenu findById(BigDecimal id);

	public List<SystemMenu> queryListByConditions(String conditions, int pageCount, int pageIndex);

	public Long queryCountByConditions(String conditions);

	public void delete(SystemMenu systemMenu);

	public int deleteByCondition(String deleteCondition);

	public List<SystemMenu> queryListByConditions(String conditions);
}
