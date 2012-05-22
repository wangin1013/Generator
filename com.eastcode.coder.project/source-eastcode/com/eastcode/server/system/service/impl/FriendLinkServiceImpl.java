package com.eastcode.server.system.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eastcode.base.service.impl.BaseServiceImpl;
import com.eastcode.server.system.dao.FriendLinkDao;
import com.eastcode.server.system.domain.FriendLink;
import com.eastcode.server.system.service.FriendLinkService;

@Service
public class FriendLinkServiceImpl extends BaseServiceImpl implements FriendLinkService {

	@Resource
	private FriendLinkDao friendLinkDao;

	public FriendLink load(Integer id, boolean isCreate) {
		if (id == null) {
			try {
				if (isCreate)
					return (FriendLink) FriendLink.class.newInstance();
				else
					return null;
			} catch (InstantiationException e) {
				throw new RuntimeException(e.getCause());
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e.getCause());
			}
		}
		return friendLinkDao.load(id);
	}

	public FriendLink load(String id, boolean isCreate) {
		if (id == null) {
			try {
				if (isCreate)
					return (FriendLink) FriendLink.class.newInstance();
				else
					return null;
			} catch (InstantiationException e) {
				throw new RuntimeException(e.getCause());
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e.getCause());
			}
		}
		return friendLinkDao.load(id);
	}

	public FriendLink findById(Integer id) {
		if (id == null)
			return null;
		return friendLinkDao.findById(id);
	}

	public FriendLink findById(String id) {
		if (id == null)
			return null;
		return friendLinkDao.findById(id);
	}

	public FriendLink findById(BigDecimal id) {
		if (id == null)
			return null;
		return friendLinkDao.findById(id);
	}

	public List<FriendLink> queryListByConditions(String conditions, int pageCount, int pageIndex) {
		return friendLinkDao.queryListByConditions(conditions, pageCount, pageIndex);
	}

	public List<FriendLink> queryListByConditions(String conditions) {
		return friendLinkDao.queryListByConditions(conditions);
	}

	public Long queryCountByConditions(String conditions) {
		return friendLinkDao.queryCountByConditions(conditions);
	}

	public void delete(FriendLink datatype) {
		friendLinkDao.delete(datatype);
	}

	public int deleteByCondition(String deleteCondition) {
		return friendLinkDao.deleteByCondition(deleteCondition);
	}
}
