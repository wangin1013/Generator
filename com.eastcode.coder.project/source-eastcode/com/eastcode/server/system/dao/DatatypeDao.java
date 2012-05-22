package com.eastcode.server.system.dao;

import com.eastcode.base.dao.BaseDao;
import com.eastcode.server.system.domain.Basedata;
import com.eastcode.server.system.domain.Datatype;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Datatype的私有Dao，继承自BaseDao
 * 
 * @see com.eastcode.server.system.domain.Datatype
 * 
 * @author 王一进
 */
@Repository
public class DatatypeDao extends BaseDao {

	public Datatype load(java.lang.Integer id) {
		Object instance = getHibernateTemplate().load(Datatype.class, id);
		return (Datatype) instance;
	}

	public Datatype load(java.lang.String id) {
		Object instance = getHibernateTemplate().load(Datatype.class, id);
		return (Datatype) instance;
	}

	public Datatype load(BigDecimal id) {
		Object instance = getHibernateTemplate().load(Datatype.class, id);
		return (Datatype) instance;
	}

	public Datatype findById(Integer id) {
		Datatype instance = (Datatype) getHibernateTemplate().get(Datatype.class.getName(), id);
		return instance;
	}

	public Datatype findById(String id) {
		Datatype instance = (Datatype) getHibernateTemplate().get(Datatype.class.getName(), new BigDecimal(id));
		return instance;
	}

	public Datatype findById(BigDecimal id) {
		Datatype instance = (Datatype) getHibernateTemplate().get(Datatype.class.getName(), id);
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
	public List<Datatype> queryListByConditions(String conditions, int count, int pageIndex) {
		String queryString = "from Datatype where 1=1 " + conditions + " order by code asc";
		return queryObject(queryString, count, pageIndex);
	}

	/**
	 * 根据条件查询记录条数
	 * 
	 * @param conditions
	 * @return
	 */
	public Long queryCountByConditions(String conditions) {
		String queryString = "select count(*) as count from Datatype where 1=1 " + conditions + " order by code asc";

		Query queryObject = getSession().createQuery(queryString);
		return (Long) queryObject.uniqueResult();
	}

	/**
	 * 普通条件查询
	 * 
	 * @param conditions
	 * @return
	 */
	public List<Datatype> queryListByConditions(String conditions) {
		String queryString = "from Datatype where 1=1 " + conditions + " order by code asc";
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
		String queryString = "delete from Datatype datatype where 1=1 " + condition;

		Query queryObject = getSession().createQuery(queryString);
		return queryObject.executeUpdate();
	}

	/**
	 * 根据字典类型查询所关联的字典数据的个数
	 * 
	 * @param conditions
	 * @return
	 */
	public int findBasedataByCondition(String condition) {
		String queryString = "select count(*) from Basedata where 1=1 " + condition;
		return getJdbcTemplate().queryForInt(queryString);
	}

	/**
	 * 根据字典类型查询所关联的字典数据
	 * 
	 * @param conditions
	 * @return
	 */
	public List<Basedata> findBasedataListByCondition(String condition) {
		String queryString = "from Basedata where 1=1 " + condition;
		Query queryObject = getSession().createQuery(queryString);
		return queryObject.list();
	}
}