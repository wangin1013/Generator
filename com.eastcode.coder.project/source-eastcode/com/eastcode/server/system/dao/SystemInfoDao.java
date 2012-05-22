package com.eastcode.server.system.dao;

import com.eastcode.base.dao.BaseDao;
import com.eastcode.server.system.domain.SystemInfo;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * SystemInfo的私有Dao，继承自BaseDao
 * 
 * @see com.eastcode.server.system.domain.SystemInfo
 * 
 * @author 王一进
 */
@Repository
public class SystemInfoDao extends BaseDao {

	public SystemInfo load(java.lang.Integer id) {
		Object instance = getHibernateTemplate().load(SystemInfo.class, id);
		return (SystemInfo) instance;
	}

	public SystemInfo load(java.lang.String id) {
		Object instance = getHibernateTemplate().load(SystemInfo.class, id);
		return (SystemInfo) instance;
	}

	public SystemInfo load(BigDecimal id) {
		Object instance = getHibernateTemplate().load(SystemInfo.class, id);
		return (SystemInfo) instance;
	}

	public SystemInfo findById(Integer id) {
		SystemInfo instance = (SystemInfo) getHibernateTemplate().get(SystemInfo.class.getName(), id);
		return instance;
	}

	public SystemInfo findById(String id) {
		SystemInfo instance = (SystemInfo) getHibernateTemplate().get(SystemInfo.class.getName(), new BigDecimal(id));
		return instance;
	}

	public SystemInfo findById(BigDecimal id) {
		SystemInfo instance = (SystemInfo) getHibernateTemplate().get(SystemInfo.class.getName(), id);
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
	public List<SystemInfo> queryListByConditions(String conditions, int count, int pageIndex) {
		String queryString = "from SystemInfo systemInfo where 1=1 " + conditions + " order by type,code asc";
		return queryObject(queryString, count, pageIndex);
	}

	/**
	 * 根据条件查询记录条数
	 * 
	 * @param conditions
	 * @return
	 */
	public Long queryCountByConditions(String conditions) {
		String queryString = "select count(*) as count from SystemInfo systemInfo where 1=1 " + conditions
				+ " order by type,code asc";

		Query queryObject = getSession().createQuery(queryString);
		return (Long) queryObject.uniqueResult();
	}

	/**
	 * 普通条件查询
	 * 
	 * @param conditions
	 * @return
	 */
	public List<SystemInfo> queryListByConditions(String conditions) {
		String queryString = "from SystemInfo systemInfo where 1=1 " + conditions + " order by type,createTime desc";
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
		String queryString = "delete from SystemInfo systemInfo where 1=1 " + condition;

		Query queryObject = getSession().createQuery(queryString);
		return queryObject.executeUpdate();
	}
}