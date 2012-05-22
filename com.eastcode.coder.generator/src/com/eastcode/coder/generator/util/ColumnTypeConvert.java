package com.eastcode.coder.generator.util;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;

import com.eastcode.coder.generator.domain.Column;

public class ColumnTypeConvert {

	/***
	 * 
	 * 数据库类型与JAVA类型映射关系
	 * 
	 * @param column
	 * @return
	 */
	public static String convert(Column column) {

		String result = "";
		if (column.getDataType().equals("VARCHAR2")) {
			result = "String";
		}

		if (column.getDataType().equals("NUMBER")) {
			result = "BigDecimal";
		}

		if (column.getDataType().equals("DATE")) {
			result = "Date";
		}

		if (column.getDataType().equals("CHAR")) {
			result = "String";
		}

		if (column.getDataType().equals("LONG")) {
			result = "long";
		}

		if (column.getDataType().equals("FLOAT")) {
			result = "float";
		}

		if (column.getDataType().equals("INTEGER")) {
			result = "Integer";
		}

		if (column.getDataType().equals("NCHAR")) {
			result = "String";
		}

		if (column.getDataType().equals("NVARCHAR2")) {
			result = "String";
		}

		if (column.getDataType().equals("BLOB")) {
			result = "String";
		}

		if (column.getDataType().equals("CLOB")) {
			result = "String";
		}

		if (column.getDataType().equals("NCHAR")) {
			result = "String";
		}

		if (column.getDataType().equals("TIMESTAMP")) {
			result = "Date";
		}

		return result;
	}

	/**
	 * 导入包
	 * 
	 * @param columns
	 * @param unit
	 * @throws CoreException
	 */
	public static void addImport(List<Column> columns, ICompilationUnit unit) throws CoreException {

		boolean importDate = false;
		boolean importBigDecimal = false;

		for (int i = 0; columns != null && i < columns.size(); i++) {

			if (!importDate && columns.get(i).getDataType().equals("DATE")) {
				unit.createImport("java.util.Date", null, null);
				importDate = true;
			}

			if (!importDate && columns.get(i).getDataType().equals("TIMESTAMP")) {
				unit.createImport("java.util.Date", null, null);
				importDate = true;
			}

			if (!importBigDecimal && columns.get(i).getDataType().equals("NUMBER")) {
				unit.createImport("java.math.BigDecimal", null, null);
				importBigDecimal = true;
			}
		}

		unit.createImport("javax.persistence.Column", null, null);
		unit.createImport("javax.persistence.Entity", null, null);
		unit.createImport("javax.persistence.GeneratedValue", null, null);
		unit.createImport("javax.persistence.Id", null, null);
		unit.createImport("javax.persistence.Table", null, null);
		unit.createImport("javax.persistence.Transient", null, null);

	}
}
