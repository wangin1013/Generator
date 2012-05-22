package com.eastcode.server.system.service;

import java.math.BigDecimal;
import java.util.List;

import com.eastcode.base.service.BaseService;
import com.eastcode.server.system.domain.Station;
import com.eastcode.server.system.domain.StationRule;
import com.eastcode.server.system.domain.SystemMenu;

public interface StationRuleService extends BaseService {

	public StationRule load(Integer id, boolean isCreate);

	public StationRule load(String id, boolean isCreate);

	public StationRule findById(Integer id);

	public StationRule findById(String id);

	public StationRule findById(BigDecimal id);

	public List<StationRule> queryListByConditions(String conditions, int pageCount, int pageIndex);

	public List<StationRule> queryListByConditions(String conditions);

	public Long queryCountByConditions(String conditions);

	public void delete(StationRule stationRule);

	public int deleteByCondition(String deleteCondition);

	public List<SystemMenu> querySystemMenuListByStation(Station station);

}
