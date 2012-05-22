package com.eastcode.base.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 持久化类接口
 * 
 * @author 王一进
 * 
 */
public interface PersistentObject extends Serializable {
	public BigDecimal getId();

	public void setId(BigDecimal id);
}
