package com.eastcode.base.dao;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * 
 * 框架基础DAO，扩展在hibernateDaoSupport，并集成了JdbcTemplate
 * 
 * @author 王一进
 * 
 */
@Repository
public class FrameDao extends HibernateDaoSupport {
	protected JdbcTemplate jdbcTemplate;
	@Resource
	protected DataSource dataSource;

	@Autowired
	public void setSessionFactoryOverride(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	public final void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public final DataSource getDataSource() {
		return dataSource;
	}

	public JdbcTemplate getJdbcTemplate() {
		if (null == this.jdbcTemplate) {
			this.jdbcTemplate = new JdbcTemplate(dataSource);
		}
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jt) {
		this.jdbcTemplate = jt;
	}
}
