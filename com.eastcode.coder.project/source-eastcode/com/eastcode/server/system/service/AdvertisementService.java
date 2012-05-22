package com.eastcode.server.system.service;

import java.math.BigDecimal;
import java.util.List;

import com.eastcode.base.service.BaseService;
import com.eastcode.server.system.domain.Advertisement;

public interface AdvertisementService extends BaseService {

	public Advertisement load(Integer id, boolean isCreate);

	public Advertisement load(String id, boolean isCreate);

	public Advertisement findById(Integer id);

	public Advertisement findById(String id);

	public Advertisement findById(BigDecimal id);

	public List<Advertisement> queryListByConditions(String conditions, int pageCount, int pageIndex);

	public List<Advertisement> queryListByConditions(String conditions);

	public Long queryCountByConditions(String conditions);

	public void delete(Advertisement advertisement);

	public int deleteByCondition(String deleteCondition);
}
