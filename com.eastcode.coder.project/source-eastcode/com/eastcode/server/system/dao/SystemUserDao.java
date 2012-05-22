package com.eastcode.server.system.dao;

import com.eastcode.base.dao.BaseDao;
import com.eastcode.server.system.domain.SystemUser;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

/**
 * SystemUser的私有Dao，继承自BaseDao
 * 
 * @see com.eastcode.server.system.domain.SystemUser
 * 
 * @author 王一进
 */
@Repository
public class SystemUserDao extends BaseDao {

	public SystemUser load(java.lang.Integer id) {
		Object instance = getHibernateTemplate().load(SystemUser.class, id);
		return (SystemUser) instance;
	}

	public SystemUser load(java.lang.String id) {
		Object instance = getHibernateTemplate().load(SystemUser.class, id);
		return (SystemUser) instance;
	}

	public SystemUser load(BigDecimal id) {
		Object instance = getHibernateTemplate().load(SystemUser.class, id);
		return (SystemUser) instance;
	}

	public SystemUser findById(Integer id) {
		SystemUser instance = (SystemUser) getHibernateTemplate().get(SystemUser.class.getName(), id);
		return instance;
	}

	public SystemUser findById(String id) {
		SystemUser instance = (SystemUser) getHibernateTemplate().get(SystemUser.class.getName(), new BigDecimal(id));
		return instance;
	}

	public SystemUser findByUserId(String userId) {
		String[] params = { "user_id" };
		String[] values = { userId };

		List resultList = getHibernateTemplate().findByNamedParam(
				"from SystemUser user where user.userId=:user_id", params, values);

		SystemUser instance = null;
		if (!CollectionUtils.isEmpty(resultList)) {
			instance = (SystemUser) resultList.get(0);
		}

		return instance;
	}

	public SystemUser findById(BigDecimal id) {
		SystemUser instance = (SystemUser) getHibernateTemplate().get(SystemUser.class.getName(), id);
		return instance;
	}

	public SystemUser findUserByUserIdAndPassword(String userId, String password) {

		String[] params = { "user_id", "password" };
		String[] values = { userId, password };

		List resultList = getHibernateTemplate().findByNamedParam(
				"from SystemUser user where user.userId=:user_id and user.password=:password", params, values);

		SystemUser instance = null;
		if (!CollectionUtils.isEmpty(resultList)) {
			instance = (SystemUser) resultList.get(0);
		}

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
	public List<SystemUser> queryListByConditions(String conditions, int count, int pageIndex) {
		String queryString = "from SystemUser where 1=1 " + conditions;

		return queryObject(queryString, count, pageIndex);
	}

	/**
	 * 普通查询
	 * 
	 * @param conditions
	 * @return
	 */
	public List<SystemUser> queryListByConditions(String conditions) {
		String queryString = "from SystemUser where 1=1 " + conditions;

		Query queryObject = getSession().createQuery(queryString);
		return queryObject.list();

	}

	/**
	 * 根据条件查询记录条数
	 * 
	 * @param conditions
	 * @return
	 */
	public Long queryCountByConditions(String conditions) {
		String queryString = "select count(*) as count from SystemUser where 1=1 " + conditions;

		Query queryObject = getSession().createQuery(queryString);
		return (Long) queryObject.uniqueResult();
	}

	/**
	 * 根据条件删除记录条数
	 * 
	 * @param conditions
	 * @return
	 */
	public int deleteByCondition(String condition) {
		String queryString = "delete from SystemUser where 1=1 " + condition;

		Query queryObject = getSession().createQuery(queryString);
		return queryObject.executeUpdate();
	}
}