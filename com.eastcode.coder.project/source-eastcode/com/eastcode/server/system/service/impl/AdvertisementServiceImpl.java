package com.eastcode.server.system.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eastcode.base.service.impl.BaseServiceImpl;
import com.eastcode.server.system.dao.AdvertisementDao;
import com.eastcode.server.system.domain.Advertisement;
import com.eastcode.server.system.service.AdvertisementService;

@Service
public class AdvertisementServiceImpl extends BaseServiceImpl implements AdvertisementService {

	@Resource
	private AdvertisementDao advertisementDao;

	public Advertisement load(Integer id, boolean isCreate) {
		if (id == null) {
			try {
				if (isCreate)
					return (Advertisement) Advertisement.class.newInstance();
				else
					return null;
			} catch (InstantiationException e) {
				throw new RuntimeException(e.getCause());
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e.getCause());
			}
		}
		return advertisementDao.load(id);
	}

	public Advertisement load(String id, boolean isCreate) {
		if (id == null) {
			try {
				if (isCreate)
					return (Advertisement) Advertisement.class.newInstance();
				else
					return null;
			} catch (InstantiationException e) {
				throw new RuntimeException(e.getCause());
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e.getCause());
			}
		}
		return advertisementDao.load(id);
	}

	public Advertisement findById(Integer id) {
		if (id == null)
			return null;
		return advertisementDao.findById(id);
	}

	public Advertisement findById(String id) {
		if (id == null)
			return null;
		return advertisementDao.findById(id);
	}

	public Advertisement findById(BigDecimal id) {
		if (id == null)
			return null;
		return advertisementDao.findById(id);
	}

	public List<Advertisement> queryListByConditions(String conditions, int pageCount, int pageIndex) {
		return advertisementDao.queryListByConditions(conditions, pageCount, pageIndex);
	}

	public List<Advertisement> queryListByConditions(String conditions) {
		return advertisementDao.queryListByConditions(conditions);
	}

	public Long queryCountByConditions(String conditions) {
		return advertisementDao.queryCountByConditions(conditions);
	}

	public void delete(Advertisement advertisement) {
		advertisementDao.delete(advertisement);
	}

	public int deleteByCondition(String deleteCondition) {
		return advertisementDao.deleteByCondition(deleteCondition);
	}
}
