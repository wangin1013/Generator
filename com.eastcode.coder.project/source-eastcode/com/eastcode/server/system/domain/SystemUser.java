package com.eastcode.server.system.domain;

/**
 * 该类由WANG YIJIN使用Hibernate Tools 3.2.4.GA 生成
 *
 * 生成时间2010-11-8 15:35:45
 */

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.eastcode.base.domain.PersistentObject;

/**
 * SystemUser generated by hbm2java
 * 
 * @author WANG YIJIN
 * @version V1.0
 */
@Entity
@Table(name = "SYSTEM_USER")
public class SystemUser implements PersistentObject {

	private static final long serialVersionUID = 1L;

	private BigDecimal id;
	private String userId;
	private String name;
	private String password;
	private String password1;
	private String email;
	private String type;
	private BigDecimal ownerId;
	private String status;
	private Date createTime;
	private Date updateTime;

	@Id
	@GeneratedValue(generator = "ud")
	@GenericGenerator(name = "ud", strategy = "native")
	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	@Column(name = "USER_ID")
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Transient
	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "OWNER_ID")
	public BigDecimal getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(BigDecimal ownerId) {
		this.ownerId = ownerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UPDATE_TIME")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
