package com.eastcode.utils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

/**
 * 用于Date类型的转化
 * 
 * @author 王一进
 * 
 */
public class DateConverter extends StrutsTypeConverter {

	@Override
	public Object convertFromString(Map context, String[] value, Class toClass) {

		if (toClass.equals(Date.class) && value.length > 0) {
			try {
				if (null != value[0] && value[0].length() > 0) {
					if (value[0].length() > 10) {
						return DateUtil.parseDateTime(value[0]);
					} else {
						return DateUtil.parseDate(value[0]);
					}
				}
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
		}

		return null;
	}

	@Override
	public String convertToString(Map arg0, Object arg1) {
		if (arg1 instanceof Date) {
			Date date = (Date) arg1;
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int hour = calendar.get(Calendar.HOUR);
			if (hour != 0) {
				return DateUtil.getDateTimeStr((Date) arg1);
			} else {
				return DateUtil.getDateStr((Date) arg1);
			}
		}
		return null;
	}

}
