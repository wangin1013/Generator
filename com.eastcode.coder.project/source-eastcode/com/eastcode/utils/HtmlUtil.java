package com.eastcode.utils;

import org.htmlparser.Parser;
import org.htmlparser.visitors.TextExtractingVisitor;

public class HtmlUtil {

	/**
	 * 获取是否radio
	 * 
	 * @return
	 */
	public static String getYesNoRadioButton() {
		StringBuffer sb = new StringBuffer();
		sb.append("#");
		sb.append("{");
		sb.append("'");
		sb.append(Constants.ZERO);
		sb.append("'");
		sb.append(":");
		sb.append("'");
		sb.append(Constants.NO);
		sb.append("'");
		sb.append(",");
		sb.append("'");
		sb.append(Constants.ONE);
		sb.append("'");
		sb.append(":");
		sb.append("'");
		sb.append(Constants.YES);
		sb.append("'");
		sb.append("}");

		return sb.toString();
	}

	public static String getTextFromHtml(String html) throws Exception {
		// 抽取大文本内容
		Parser parser = new Parser();
		parser.setInputHTML(html);
		TextExtractingVisitor introduct = new TextExtractingVisitor();
		parser.visitAllNodesWith(introduct);

		String result = introduct.getExtractedText();
		if (result != null) {
			result = result.trim();
		} else {
			result = "";
		}

		return result;
	}

	/**
	 * 产生空格
	 * 
	 * @param number
	 * @return
	 */
	public static String createSpace(int number) {
		
		StringBuffer sb = new StringBuffer();
		for(int i = 0;i<number;i++) {
			sb.append(" ");
		}
		
		return sb.toString(); 
	}
}
