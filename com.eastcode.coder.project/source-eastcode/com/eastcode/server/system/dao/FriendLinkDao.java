package com.eastcode.server.system.dao;

import com.eastcode.base.dao.BaseDao;
import com.eastcode.server.system.domain.FriendLink;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * FriendLink的私有Dao，继承自BaseDao
 * 
 * @see com.eastcode.server.system.domain.FriendLink
 * 
 * @author 王一进
 */
@Repository
public class FriendLinkDao extends BaseDao {

	public FriendLink load(java.lang.Integer id) {
		Object instance = getHibernateTemplate().load(FriendLink.class, id);
		return (FriendLink) instance;
	}

	public FriendLink load(java.lang.String id) {
		Object instance = getHibernateTemplate().load(FriendLink.class, id);
		return (FriendLink) instance;
	}

	public FriendLink load(BigDecimal id) {
		Object instance = getHibernateTemplate().load(FriendLink.class, id);
		return (FriendLink) instance;
	}

	public FriendLink findById(Integer id) {
		FriendLink instance = (FriendLink) getHibernateTemplate().get(FriendLink.class.getName(), id);
		return instance;
	}

	public FriendLink findById(String id) {
		FriendLink instance = (FriendLink) getHibernateTemplate().get(FriendLink.class.getName(), new BigDecimal(id));
		return instance;
	}

	public FriendLink findById(BigDecimal id) {
		FriendLink instance = (FriendLink) getHibernateTemplate().get(FriendLink.class.getName(), id);
		return instance;
	}

	/**
	 * 带翻页的查询
	 * 
	 * @param conditions
	 * @param count
	 * @param pageIndex
	 * @return
	 */
	public List<FriendLink> queryListByConditions(String conditions, int count, int pageIndex) {
		String queryString = "from FriendLink friendLink where 1=1 " + conditions
				+ " order by friendLink.orderCode asc ";
		return queryObject(queryString, count, pageIndex);
	}

	/**
	 * 根据条件查询记录条数
	 * 
	 * @param conditions
	 * @return
	 */
	public Long queryCountByConditions(String conditions) {
		String queryString = "select count(*) as count from FriendLink friendLink where 1=1" + conditions
				+ " order by friendLink.orderCode asc ";
		Query queryObject = getSession().createQuery(queryString);
		return (Long) queryObject.uniqueResult();
	}

	/**
	 * 普通条件查询
	 * 
	 * @param conditions
	 * @return
	 */
	public List<FriendLink> queryListByConditions(String conditions) {
		String queryString = "from FriendLink friendLink where 1=1 " + conditions
				+ " order by friendLink.orderCode asc ";
		Query queryObject = getSession().createQuery(queryString);

		return queryObject.list();
	}

	/**
	 * 根据条件删除记录条数
	 * 
	 * @param conditions
	 * @return
	 */
	public int deleteByCondition(String condition) {
		String queryString = "delete from FriendLink friendLink where 1=1 " + condition;

		Query queryObject = getSession().createQuery(queryString);
		return queryObject.executeUpdate();
	}
}