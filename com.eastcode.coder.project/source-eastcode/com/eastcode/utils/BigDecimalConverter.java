package com.eastcode.utils;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

/**
 * 用于BigDecimal类型的转化
 * 
 * @author 王一进
 * 
 */
public class BigDecimalConverter extends StrutsTypeConverter {

	@Override
	public Object convertFromString(Map context, String[] value, Class toClass) {

		if (toClass.equals(BigDecimal.class) && value.length > 0) {
			BigDecimal decimal = new BigDecimal(0);
			if (null != value[0] && value[0].length() > 0) {
				decimal = new BigDecimal(value[0]);
				return decimal;
			}
		}

		return null;
	}

	@Override
	public String convertToString(Map arg0, Object arg1) {

		if (arg1 instanceof BigDecimal) {
			return arg1.toString();
		}
		return null;
	}

}
