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

import org.hibernate.annotations.GenericGenerator;

import com.eastcode.base.domain.PersistentObject;

/**
 * Basedata generated by hbm2java
 * 
 * @author WANG YIJIN
 * @version V1.0
 */
@Entity
@Table(name = "FRIEND_LINK")
public class FriendLink implements PersistentObject {

	private static final long serialVersionUID = 1L;

	private BigDecimal id;
	private String code;
	private String name;
	private String link;
	private String status;
	private Integer orderCode;
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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "ORDER_CODE")
	public Integer getOrderCode() {
		return this.orderCode;
	}

	public void setOrderCode(Integer orderCode) {
		this.orderCode = orderCode;
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
