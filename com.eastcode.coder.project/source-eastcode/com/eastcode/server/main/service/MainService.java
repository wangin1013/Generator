package com.eastcode.server.main.service;

import java.util.List;

import com.eastcode.base.service.BaseService;
import com.eastcode.server.system.domain.SystemMenu;
import com.eastcode.server.system.domain.SystemUser;

public interface MainService extends BaseService {

	public List<SystemMenu> querySysteMenu(SystemUser systemUser);
}
