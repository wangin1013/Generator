package com.eastcode.server.system.service;

import java.math.BigDecimal;
import java.util.List;

import com.eastcode.base.service.BaseService;
import com.eastcode.server.system.domain.SystemUser;

public interface SystemUserService extends BaseService {

	public SystemUser load(Integer id, boolean isCreate);

	public SystemUser load(String id, boolean isCreate);

	public SystemUser findById(Integer id);

	public SystemUser findById(String id);

	public SystemUser findById(BigDecimal id);

	public SystemUser findUserByUserIdAndPassword(String userId, String password);

	public List<SystemUser> queryListByConditions(String conditions, int pageCount, int pageIndex);

	public Long queryCountByConditions(String conditions);

	public void delete(SystemUser systemUser);

	public int deleteByCondition(String deleteCondition);

	public int saveSystemUsersStations(SystemUser systemUser, String[] stations);

}
