package com.eastcode.utils;

/**
 * 用于Integer类型的转化
 * 
 * @author 王一进
 * 
 */
public class IntegerUtil {

	/**
	 * 判断字符串是否为整数
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNumber(String value) {
		for (int i = 0; value != null && i < value.length(); i++) {
			char e = value.charAt(i);

			if (!Character.isDigit(e)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 获取字符串后面的数字
	 * 
	 * @param value
	 * @return
	 */
	public static int parseInt(String value) {
		int digitIndext = 0;
		for (int i = value.length() - 1; i >= 0; i--) {
			if (!Character.isDigit(value.charAt(i))) {
				System.out.println(value.charAt(i));
				digitIndext = i + 1;
				break;
			}
		}
		String result = value.substring(digitIndext);

		if (result != null && result.length() > 0) {
			return Integer.parseInt(result);
		}
		return 0;
	}

	/**
	 * 获取字符串前面的字符串
	 * 
	 * @param value
	 * @return
	 */
	public static String parseString(String value) {
		int digitIndext = 0;
		for (int i = value.length() - 1; i >= 0; i--) {
			if (!Character.isDigit(value.charAt(i))) {
				System.out.println(value.charAt(i));
				digitIndext = i;
				break;
			}
		}
		return value.substring(0, digitIndext);

	}
}
