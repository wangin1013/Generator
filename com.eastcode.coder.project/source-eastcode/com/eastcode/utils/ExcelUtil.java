package com.eastcode.utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtil {

	// 解析文件
	public static List<Map<Integer, Object>> readExcel(String file) throws Exception {

		List<Map<Integer, Object>> resultList = new ArrayList<Map<Integer, Object>>();
		HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
		int sn = wb.getNumberOfSheets();
		for (int i = 0; i < sn; i++) {
			HSSFSheet sheet = wb.getSheetAt(i);
			for (int j = 0; sheet != null && j <= sheet.getLastRowNum(); j++) {
				HSSFRow row = sheet.getRow(j);
				Map<Integer, Object> resultMap = new HashMap<Integer, Object>();
				for (int k = 0; row != null && k < row.getLastCellNum(); k++) {
					HSSFCell cell = row.getCell(k);
					Object object = parseCell(cell);
					resultMap.put(new Integer(k), object);
				}
				resultList.add(resultMap);
			}
		}

		return resultList;
	}

	/**
	 * 处理单元格的内容
	 * 
	 * @param cell
	 * @return
	 */
	public static Object parseCell(HSSFCell cell) {
		Object object = null;
		if (cell == null) {
			return object;
		}
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			object = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			object = cell.getBooleanCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				object = DateUtil.getDateStr(cell.getDateCellValue());
			} else {
				double number = cell.getNumericCellValue();
				if ((number - Math.round(number)) == 0) {
					Integer integer = new Integer((int) number);
					object = integer.toString();
				} else {
					object = cell.getNumericCellValue();
				}

			}
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			object = cell.getCellFormula();
			break;
		default:
			break;
		}

		return object;
	}

	public static void main(String[] args) {
		try {
			List<Map<Integer, Object>> resultList = ExcelUtil.readExcel("D:\\test.xls");

			for (int i = 0; resultList != null && i < resultList.size(); i++) {
				Map<Integer, Object> resultMap = resultList.get(i);
				Set<Integer> keys = resultMap.keySet();
				if (keys != null) {
					Iterator<Integer> iterator = keys.iterator();
					while (iterator.hasNext()) {
						Object key = iterator.next();
						Object value = resultMap.get(key);
						System.out.println(value);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
