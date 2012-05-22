package com.eastcode.server.system.service;

import java.math.BigDecimal;
import java.util.List;

import com.eastcode.base.service.BaseService;
import com.eastcode.server.system.domain.Station;

public interface StationService extends BaseService {

	public Station load(Integer id, boolean isCreate);

	public Station load(String id, boolean isCreate);

	public Station findById(Integer id);

	public Station findById(String id);

	public Station findById(BigDecimal id);

	public List<Station> queryListByConditions(String conditions, int pageCount, int pageIndex);

	public List<Station> queryListByConditions(String conditions);

	public Long queryCountByConditions(String conditions);

	public void delete(Station station);

	public int deleteByCondition(String deleteCondition);

	public int saveSystemMenus(Station station, String[] systemMenus);

}
