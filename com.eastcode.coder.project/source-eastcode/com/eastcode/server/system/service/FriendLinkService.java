package com.eastcode.server.system.service;

import java.math.BigDecimal;
import java.util.List;

import com.eastcode.base.service.BaseService;
import com.eastcode.server.system.domain.FriendLink;

public interface FriendLinkService extends BaseService {

	public FriendLink load(Integer id, boolean isCreate);

	public FriendLink load(String id, boolean isCreate);

	public FriendLink findById(Integer id);

	public FriendLink findById(String id);

	public FriendLink findById(BigDecimal id);

	public List<FriendLink> queryListByConditions(String conditions, int pageCount, int pageIndex);

	public List<FriendLink> queryListByConditions(String conditions);

	public Long queryCountByConditions(String conditions);

	public void delete(FriendLink FriendLink);

	public int deleteByCondition(String deleteCondition);

}
