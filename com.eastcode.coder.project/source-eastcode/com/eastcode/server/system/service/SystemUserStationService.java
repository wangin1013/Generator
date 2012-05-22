package com.eastcode.server.system.service;

import java.math.BigDecimal;
import java.util.List;

import com.eastcode.base.service.BaseService;
import com.eastcode.server.system.domain.Station;
import com.eastcode.server.system.domain.SystemUser;
import com.eastcode.server.system.domain.SystemUserStation;

public interface SystemUserStationService extends BaseService {

	public SystemUserStation load(Integer id, boolean isCreate);

	public SystemUserStation load(String id, boolean isCreate);

	public SystemUserStation findById(Integer id);

	public SystemUserStation findById(String id);

	public SystemUserStation findById(BigDecimal id);

	public List<SystemUserStation> queryListByConditions(String conditions, int pageCount, int pageIndex);

	public Long queryCountByConditions(String conditions);

	public void delete(SystemUserStation systemUserStation);

	public int deleteByCondition(String deleteCondition);

	public List<Station> querySystemUserListBySystemUser(SystemUser systemUser);
}
