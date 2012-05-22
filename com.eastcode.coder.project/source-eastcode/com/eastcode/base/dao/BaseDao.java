package com.eastcode.base.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.eastcode.base.domain.PersistentObject;

@Repository
public class BaseDao extends FrameDao {
	private static final Log log = LogFactory.getLog(BaseDao.class);

	/**
	 * 通用分页列表数据查询
	 * 
	 * @param queryString
	 * @param count
	 * @param pageIndex
	 * @return
	 */
	protected List queryObject(String queryString, int count, int pageIndex) {
		Query queryObject = getSession().createQuery(queryString);
		queryObject.setFirstResult(count * (pageIndex - 1));
		queryObject.setMaxResults(count);
		return queryObject.list();
	}

	public void merge(PersistentObject object) {
		log.debug("merging 对象:" + object.getClass().getName());
		try {
			getHibernateTemplate().merge(object);

			log.debug("merging 成功！");
		} catch (RuntimeException re) {
			log.error("merging 失败！", re);
			throw re;
		}
	}

	public void refresh(PersistentObject object) {

		log.debug("refresh 对象:" + object.getClass().getName());
		try {
			getHibernateTemplate().refresh(object);
			log.debug("refresh 成功！");
		} catch (RuntimeException re) {
			log.error("refresh 失败！", re);
			throw re;
		}
	}

	public void delete(PersistentObject object) {
		log.debug("deleting 对象:" + object.getClass().getName());
		try {
			getHibernateTemplate().delete(object);
			log.debug("delete 成功！");
		} catch (RuntimeException re) {
			log.error("delete 失败！", re);
			throw re;
		}
	}

	public void save(PersistentObject object) {
		log.debug("save 对象:" + object.getClass().getName());
		try {
			getHibernateTemplate().save(object);
			log.debug("save 成功！");
		} catch (RuntimeException re) {
			log.error("save 失败！", re);
			throw re;
		}
	}

	public void saveOrUpdate(PersistentObject object) {
		log.debug("save 对象:" + object.getClass().getName());
		try {
			getHibernateTemplate().saveOrUpdate(object);
			log.debug("save 成功！");
		} catch (RuntimeException re) {
			log.error("save 失败！", re);
			throw re;
		}
	}

	public void update(PersistentObject object) {
		log.debug("update 对象:" + object.getClass().getName());
		try {
			getHibernateTemplate().update(object);
			log.debug("update 成功！");
		} catch (RuntimeException re) {
			log.error("update 失败！", re);
			throw re;
		}
	}
}
