package com.eastcode.server.system.dao;

import com.eastcode.base.dao.BaseDao;
import com.eastcode.server.system.domain.Station;
import com.eastcode.server.system.domain.StationRule;
import com.eastcode.server.system.domain.SystemMenu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * StationRule的私有Dao，继承自BaseDao
 * 
 * @see com.eastcode.server.system.domain.StationRule
 * 
 * @author 王一进
 */
@Repository
public class StationRuleDao extends BaseDao {

	public StationRule load(java.lang.Integer id) {
		Object instance = getHibernateTemplate().load(StationRule.class, id);
		return (StationRule) instance;
	}

	public StationRule load(java.lang.String id) {
		Object instance = getHibernateTemplate().load(StationRule.class, id);
		return (StationRule) instance;
	}

	public StationRule load(BigDecimal id) {
		Object instance = getHibernateTemplate().load(StationRule.class, id);
		return (StationRule) instance;
	}

	public StationRule findById(Integer id) {
		StationRule instance = (StationRule) getHibernateTemplate().get(StationRule.class.getName(), id);
		return instance;
	}

	public StationRule findById(String id) {
		StationRule instance = (StationRule) getHibernateTemplate()
				.get(StationRule.class.getName(), new BigDecimal(id));
		return instance;
	}

	public StationRule findById(BigDecimal id) {
		StationRule instance = (StationRule) getHibernateTemplate().get(StationRule.class.getName(), id);
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
	public List<StationRule> queryListByConditions(String conditions, int count, int pageIndex) {
		String queryString = "from StationRule stationRule left join stationRule.station station left join stationRule.systemMenu systemMenu "
				+ " where 1=1 and station.id=stationRule.station.id and systemMenu.id=stationRule.systemMenu.id "
				+ conditions + " order by stationRule.createTime asc";
		Query queryObject = getSession().createQuery(queryString);
		queryObject.setFirstResult(count * (pageIndex - 1));
		queryObject.setMaxResults(count);
		List<Object[]> sqlList = queryObject.list();
		List<StationRule> resultList = new ArrayList<StationRule>();

		Iterator<Object[]> iterator = sqlList.iterator();
		while (iterator.hasNext()) {
			Object[] o = iterator.next();
			StationRule stationRule = (StationRule) o[0];
			resultList.add(stationRule);
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
		String queryString = "select count(*) as count from StationRule stationRule left join stationRule.station station left join stationRule.systemMenu systemMenu "
				+ " where 1=1 and station.id=stationRule.station.id and systemMenu.id=stationRule.systemMenu.id "
				+ conditions + " order by stationRule.createTime asc";

		Query queryObject = getSession().createQuery(queryString);
		return (Long) queryObject.uniqueResult();
	}

	/**
	 * 普通条件查询
	 * 
	 * @param conditions
	 * @return
	 */
	public List<StationRule> queryListByConditions(String conditions) {
		String queryString = "from StationRule stationRule left join stationRule.station station left join stationRule.systemMenu systemMenu "
				+ " where 1=1 and station.id=stationRule.station.id and systemMenu.id=stationRule.systemMenu.id "
				+ conditions + " order by stationRule.createTime asc";
		Query queryObject = getSession().createQuery(queryString);
		List<Object[]> sqlList = queryObject.list();
		List<StationRule> resultList = new ArrayList<StationRule>();

		Iterator<Object[]> iterator = sqlList.iterator();
		while (iterator.hasNext()) {
			Object[] o = iterator.next();
			StationRule stationRule = (StationRule) o[0];
			resultList.add(stationRule);
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
		String queryString = "delete from StationRule stationRule where 1=1 " + condition;

		Query queryObject = getSession().createQuery(queryString);
		return queryObject.executeUpdate();
	}

	/**
	 * 根据岗位查询其拥有的权限
	 * 
	 * @param station
	 * @return
	 */
	public List<SystemMenu> querySystemMenuListByStation(Station station) {
		String queryString = "from StationRule stationRule left join stationRule.station station left join stationRule.systemMenu systemMenu "
				+ " where 1=1 and station.id=stationRule.station.id and systemMenu.id=stationRule.systemMenu.id and station.id='"
				+ station.getId() + "' order by stationRule.createTime asc";
		Query queryObject = getSession().createQuery(queryString);
		List<Object[]> sqlList = queryObject.list();
		List<SystemMenu> resultList = new ArrayList<SystemMenu>();

		Iterator<Object[]> iterator = sqlList.iterator();
		while (iterator.hasNext()) {
			Object[] o = iterator.next();
			SystemMenu systemMenu = (SystemMenu) o[2];
			resultList.add(systemMenu);
		}

		return resultList;
	}
}