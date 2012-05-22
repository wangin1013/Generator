package com.eastcode.server.system.dao;

import com.eastcode.base.dao.BaseDao;
import com.eastcode.server.system.domain.Basedata;
import com.eastcode.utils.Constants;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Basedata的私有Dao，继承自BaseDao
 * 
 * @see com.eastcode.server.system.domain.Basedata
 * 
 * @author 王一进
 */
@Repository
public class BasedataDao extends BaseDao {

	public Basedata load(java.lang.Integer id) {
		Object instance = getHibernateTemplate().load(Basedata.class, id);
		return (Basedata) instance;
	}

	public Basedata load(java.lang.String id) {
		Object instance = getHibernateTemplate().load(Basedata.class, id);
		return (Basedata) instance;
	}

	public Basedata load(BigDecimal id) {
		Object instance = getHibernateTemplate().load(Basedata.class, id);
		return (Basedata) instance;
	}

	public Basedata findById(Integer id) {
		Basedata instance = (Basedata) getHibernateTemplate().get(Basedata.class.getName(), id);
		return instance;
	}

	public Basedata findById(String id) {
		Basedata instance = (Basedata) getHibernateTemplate().get(Basedata.class.getName(), new BigDecimal(id));
		return instance;
	}

	public Basedata findById(BigDecimal id) {
		Basedata instance = (Basedata) getHibernateTemplate().get(Basedata.class.getName(), id);
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
	public List<Basedata> queryListByConditions(String conditions, int count, int pageIndex) {
		String queryString = "from Basedata baseData left join baseData.datatype dataType where 1=1 and baseData.datatype=dataType and dataType.status='"
				+ Constants.ENABLE + "'" + conditions + " order by dataType.code asc,baseData.orderCode asc ";
		Query queryObject = getSession().createQuery(queryString);
		queryObject.setFirstResult(count * (pageIndex - 1));
		queryObject.setMaxResults(count);
		List<Object[]> sqlList = queryObject.list();
		List<Basedata> resultList = new ArrayList<Basedata>();

		Iterator<Object[]> iterator = sqlList.iterator();
		while (iterator.hasNext()) {
			Object[] o = iterator.next();
			Basedata basedata = (Basedata) o[0];
			resultList.add(basedata);
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
		String queryString = "select count(*) as count from Basedata baseData left join baseData.datatype dataType where 1=1 and baseData.datatype=dataType and dataType.status='"
				+ Constants.ENABLE + "'" + conditions + " order by dataType.code asc,baseData.orderCode asc ";
		Query queryObject = getSession().createQuery(queryString);
		return (Long) queryObject.uniqueResult();
	}

	/**
	 * 普通条件查询
	 * 
	 * @param conditions
	 * @return
	 */
	public List<Basedata> queryListByConditions(String conditions) {
		String queryString = "from Basedata baseData left join baseData.datatype dataType where 1=1 and baseData.datatype=dataType and dataType.status='"
				+ Constants.ENABLE + "'" + conditions + " order by dataType.code asc,baseData.orderCode asc ";
		Query queryObject = getSession().createQuery(queryString);

		List sqlList = queryObject.list();
		List<Basedata> resultList = new ArrayList<Basedata>();

		Iterator iterator = sqlList.iterator();
		while (iterator.hasNext()) {
			Object[] o = (Object[]) iterator.next();
			Basedata basedata = (Basedata) o[0];
			resultList.add(basedata);
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
		String queryString = "delete from Basedata baseData where 1=1 " + condition;

		Query queryObject = getSession().createQuery(queryString);
		return queryObject.executeUpdate();
	}
}