package com.eastcode.coder.generator.util;

import com.eastcode.coder.generator.domain.Column;

public class StringUtil {

	/**
	 * 判断字符非空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {

		if (str == null || str.trim().length() == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 首字母小写
	 * 
	 * @param str
	 * @return
	 */
	public static String lowerCaptial(String str) {
		return Character.toLowerCase(str.charAt(0)) + str.substring(1);
	}

	/**
	 * 首字母大写
	 * 
	 * @param str
	 * @return
	 */
	public static String upperCaptial(String str) {
		return Character.toUpperCase(str.charAt(0)) + str.substring(1);
	}

	/**
	 * 产生字段名
	 * 
	 * @param columnName
	 * @return
	 */
	public static String createFieldName(String columnName) {
		String[] arr = columnName.toLowerCase().split("_");

		StringBuffer sb = new StringBuffer();
		if (arr != null && arr.length > 0) {
			sb.append(arr[0]);
		}
		for (int i = 1; arr != null && i < arr.length; i++) {
			sb.append(Character.toUpperCase(arr[i].charAt(0)) + arr[i].substring(1));
		}

		return sb.toString();
	}

	/**
	 * 产生字段名
	 * 
	 * @param columnName
	 * @return
	 */
	public static String createMethodSuffix(String columnName) {
		String[] arr = columnName.toLowerCase().split("_");

		StringBuffer sb = new StringBuffer();
		for (int i = 0; arr != null && i < arr.length; i++) {
			sb.append(Character.toUpperCase(arr[i].charAt(0)) + arr[i].substring(1));
		}

		return sb.toString();
	}

	/**
	 * 创建setter方法
	 * 
	 * @param column
	 * @return
	 */
	public static String createSetter(Column column) {
		String filedName = createFieldName(column.getColumnName());
		StringBuffer sb = new StringBuffer();
		sb.append(" public ");
		sb.append(" void set" + createMethodSuffix(column.getColumnName()));
		sb.append("(");
		sb.append(ColumnTypeConvert.convert(column));
		sb.append(" ");
		sb.append(filedName);
		sb.append("){");
		sb.append("this." + filedName + "=" + filedName);
		sb.append(";}");

		return sb.toString();
	}

	/**
	 * 创建getter方法
	 * 
	 * @param column
	 * @return
	 */
	public static String createGetter(Column column) {
		String filedName = createFieldName(column.getColumnName());
		System.out.println(column.getColumnName());
		StringBuffer sb = new StringBuffer();
		if (!filedName.toLowerCase().equals(column.getColumnName().toLowerCase())) {
			sb.append("@Column(name = \"" + column.getColumnName() + "\")");
		}
		sb.append(" public ");
		sb.append(ColumnTypeConvert.convert(column));
		sb.append(" get" + createMethodSuffix(column.getColumnName()));
		sb.append("()");
		sb.append("{");
		sb.append("return " + filedName + ";");
		sb.append("}");

		return sb.toString();
	}
}