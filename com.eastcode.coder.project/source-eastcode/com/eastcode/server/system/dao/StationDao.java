package com.eastcode.server.system.dao;

import com.eastcode.base.dao.BaseDao;
import com.eastcode.server.system.domain.Station;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Station的私有Dao，继承自BaseDao
 * 
 * @see com.eastcode.server.system.domain.Station
 * 
 * @author 王一进
 */
@Repository
public class StationDao extends BaseDao {

	public Station load(java.lang.Integer id) {
		Object instance = getHibernateTemplate().load(Station.class, id);
		return (Station) instance;
	}

	public Station load(java.lang.String id) {
		Object instance = getHibernateTemplate().load(Station.class, id);
		return (Station) instance;
	}

	public Station load(BigDecimal id) {
		Object instance = getHibernateTemplate().load(Station.class, id);
		return (Station) instance;
	}

	public Station findById(Integer id) {
		Station instance = (Station) getHibernateTemplate().get(Station.class.getName(), id);
		return instance;
	}

	public Station findById(String id) {
		Station instance = (Station) getHibernateTemplate().get(Station.class.getName(), new BigDecimal(id));
		return instance;
	}

	public Station findById(BigDecimal id) {
		Station instance = (Station) getHibernateTemplate().get(Station.class.getName(), id);
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
	public List<Station> queryListByConditions(String conditions, int count, int pageIndex) {
		String queryString = "from Station where 1=1 " + conditions + " order by code asc";
		return queryObject(queryString, count, pageIndex);
	}

	/**
	 * 根据条件查询记录条数
	 * 
	 * @param conditions
	 * @return
	 */
	public Long queryCountByConditions(String conditions) {
		String queryString = "select count(*) as count from Station where 1=1 " + conditions + " order by code asc";

		Query queryObject = getSession().createQuery(queryString);
		return (Long) queryObject.uniqueResult();
	}

	/**
	 * 普通条件查询
	 * 
	 * @param conditions
	 * @return
	 */
	public List<Station> queryListByConditions(String conditions) {
		String queryString = "from Station where 1=1 " + conditions + " order by code asc";
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
		String queryString = "delete from Station where 1=1 " + condition;

		Query queryObject = getSession().createQuery(queryString);
		return queryObject.executeUpdate();
	}
}