package com.eastcode.server.system.dao;

import com.eastcode.base.dao.BaseDao;
import com.eastcode.server.system.domain.SystemParam;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * SystemParam的私有Dao，继承自BaseDao
 * 
 * @see com.eastcode.server.system.domain.SystemParam
 * 
 * @author 王一进
 */
@Repository
public class SystemParamDao extends BaseDao {

	public SystemParam load(java.lang.Integer id) {
		Object instance = getHibernateTemplate().load(SystemParam.class, id);
		return (SystemParam) instance;
	}

	public SystemParam load(java.lang.String id) {
		Object instance = getHibernateTemplate().load(SystemParam.class, id);
		return (SystemParam) instance;
	}

	public SystemParam load(BigDecimal id) {
		Object instance = getHibernateTemplate().load(SystemParam.class, id);
		return (SystemParam) instance;
	}

	public SystemParam findById(Integer id) {
		SystemParam instance = (SystemParam) getHibernateTemplate().get(SystemParam.class.getName(), id);
		return instance;
	}

	public SystemParam findById(String id) {
		SystemParam instance = (SystemParam) getHibernateTemplate()
				.get(SystemParam.class.getName(), new BigDecimal(id));
		return instance;
	}

	public SystemParam findById(BigDecimal id) {
		SystemParam instance = (SystemParam) getHibernateTemplate().get(SystemParam.class.getName(), id);
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
	public List<SystemParam> queryListByConditions(String conditions, int count, int pageIndex) {
		String queryString = "from SystemParam where 1=1 " + conditions + " order by code asc";
		return queryObject(queryString, count, pageIndex);
	}

	/**
	 * 根据条件查询记录条数
	 * 
	 * @param conditions
	 * @return
	 */
	public Long queryCountByConditions(String conditions) {
		String queryString = "select count(*) as count from SystemParam where 1=1 " + conditions + " order by code asc";

		Query queryObject = getSession().createQuery(queryString);
		return (Long) queryObject.uniqueResult();
	}

	/**
	 * 普通条件查询
	 * 
	 * @param conditions
	 * @return
	 */
	public List<SystemParam> queryListByConditions(String conditions) {
		String queryString = "from SystemParam where 1=1 " + conditions + " order by code asc";
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
		String queryString = "delete from SystemParam systemParam where 1=1 " + condition;

		Query queryObject = getSession().createQuery(queryString);
		return queryObject.executeUpdate();
	}
}