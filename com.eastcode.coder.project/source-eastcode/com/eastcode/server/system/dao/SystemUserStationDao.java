package com.eastcode.server.system.dao;

import com.eastcode.base.dao.BaseDao;
import com.eastcode.server.system.domain.Station;
import com.eastcode.server.system.domain.SystemUser;
import com.eastcode.server.system.domain.SystemUserStation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * SystemUserStation的私有Dao，继承自BaseDao
 * 
 * @see com.eastcode.server.system.domain.SystemUserStation
 * 
 * @author 王一进
 */
@Repository
public class SystemUserStationDao extends BaseDao {

	public SystemUserStation load(java.lang.Integer id) {
		Object instance = getHibernateTemplate().load(SystemUserStation.class, id);
		return (SystemUserStation) instance;
	}

	public SystemUserStation load(java.lang.String id) {
		Object instance = getHibernateTemplate().load(SystemUserStation.class, id);
		return (SystemUserStation) instance;
	}

	public SystemUserStation load(BigDecimal id) {
		Object instance = getHibernateTemplate().load(SystemUserStation.class, id);
		return (SystemUserStation) instance;
	}

	public SystemUserStation findById(Integer id) {
		SystemUserStation instance = (SystemUserStation) getHibernateTemplate().get(SystemUserStation.class.getName(),
				id);
		return instance;
	}

	public SystemUserStation findById(String id) {
		SystemUserStation instance = (SystemUserStation) getHibernateTemplate().get(SystemUserStation.class.getName(),
				new BigDecimal(id));
		return instance;
	}

	public SystemUserStation findById(BigDecimal id) {
		SystemUserStation instance = (SystemUserStation) getHibernateTemplate().get(SystemUserStation.class.getName(),
				id);
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
	public List<SystemUserStation> queryListByConditions(String conditions, int count, int pageIndex) {
		String queryString = "from SystemUserStation systemUserStation left join systemUserStation.station station"
				+ " left join systemUserStation.systemUser systemUser where 1=1 "
				+ " and systemUserStation.station.id = station.id and systemUser.id=systemUserStation.systemUser.id "
				+ conditions + " order by systemUserStation.createTime asc";
		Query queryObject = getSession().createQuery(queryString);
		queryObject.setFirstResult(count * (pageIndex - 1));
		queryObject.setMaxResults(count);
		List<Object[]> sqlList = queryObject.list();
		List<SystemUserStation> resultList = new ArrayList<SystemUserStation>();

		Iterator<Object[]> iterator = sqlList.iterator();
		while (iterator.hasNext()) {
			Object[] o = iterator.next();
			SystemUserStation systemUserStation = (SystemUserStation) o[0];
			resultList.add(systemUserStation);
		}

		return resultList;
	}

	/**
	 * 根据条件查询记录条数
	 * 
	 * @param conditions
	 * @return
	 */
	public Long queryCountByConditions(String conditions) {
		String queryString = "select count(*) as count from SystemUserStation systemUserStation left join systemUserStation.station station"
				+ " left join systemUserStation.systemUser systemUser where 1=1 "
				+ " and systemUserStation.station.id = station.id and systemUser.id=systemUserStation.systemUser.id "
				+ conditions + " order by systemUserStation.createTime asc";

		Query queryObject = getSession().createQuery(queryString);
		return (Long) queryObject.uniqueResult();
	}

	/**
	 * 普通条件查询
	 * 
	 * @param conditions
	 * @return
	 */
	public List<SystemUserStation> queryListByConditions(String conditions) {
		String queryString = "from SystemUserStation systemUserStation left join systemUserStation.station station"
				+ " left join systemUserStation.systemUser systemUser where 1=1 "
				+ " and systemUserStation.station.id = station.id and systemUser.id=systemUserStation.systemUser.id "
				+ conditions + " order by systemUserStation.createTime asc";
		Query queryObject = getSession().createQuery(queryString);
		List<Object[]> sqlList = queryObject.list();
		List<SystemUserStation> resultList = new ArrayList<SystemUserStation>();

		Iterator<Object[]> iterator = sqlList.iterator();
		while (iterator.hasNext()) {
			Object[] o = iterator.next();
			SystemUserStation systemUserStation = (SystemUserStation) o[0];
			resultList.add(systemUserStation);
		}

		return resultList;
	}

	/**
	 * 根据条件删除记录条数
	 * 
	 * @param conditions
	 * @return
	 */
	public int deleteByCondition(String condition) {
		String queryString = "delete from SystemUserStation where 1=1 " + condition;

		Query queryObject = getSession().createQuery(queryString);
		return queryObject.executeUpdate();
	}

	/**
	 * 带翻页的查询
	 * 
	 * @param conditions
	 * @param count
	 * @param pageIndex
	 * @return
	 */
	public List<Station> queryStationListBySystemUser(SystemUser systemUser) {
		String queryString = "from SystemUserStation systemUserStation left join systemUserStation.station station"
				+ " left join systemUserStation.systemUser systemUser where 1=1 "
				+ " and systemUserStation.station.id = station.id and systemUser.id=systemUserStation.systemUser.id "
				+ " and systemUser.id='" + systemUser.getId() + "' order by systemUserStation.createTime asc";
		Query queryObject = getSession().createQuery(queryString);
		List<Object[]> sqlList = queryObject.list();
		List<Station> resultList = new ArrayList<Station>();

		Iterator<Object[]> iterator = sqlList.iterator();
		while (iterator.hasNext()) {
			Object[] o = iterator.next();
			Station station = (Station) o[1];
			resultList.add(station);
		}

		return resultList;
	}

}