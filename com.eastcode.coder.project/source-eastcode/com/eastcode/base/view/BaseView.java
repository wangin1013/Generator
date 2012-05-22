package com.eastcode.base.view;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.eastcode.server.system.domain.Basedata;
import com.eastcode.utils.Constants;

public class BaseView {
	// 查询条件
	private String queryCondition = "";
	// 删除条件
	private String deleteCondition = "";
	// 所选记录ID
	private String ids;
	// 数据结果集
	private Object result;
	// 当前页
	private String pageIndex = "1";
	// 当前页记录数
	private int recordNum = 0;
	// 页面数
	private int pageCount = 1;
	// 每页记录数
	private int perCount = 20;
	// 总记录数
	private Long recordTotal = new Long(0);

	// 特定字段
	private String enable = Constants.ENABLE;

	private List<Basedata> basedataList;

	private Map<String, String> basedataMap;

	// 状态map表
	private Map<String, String> statusMap;

	// YesNo表
	private Map<String, String> yesNoMap;

	private String errorMessage ="";

	private String timestamp;

	/**
	 * 列表翻页配置
	 * 
	 * @param queryCondition
	 */
	public void setTurnPage(Long recordTotal) {
		setRecordTotal(recordTotal);
		double pageCount = Math.ceil(new Double(recordTotal).doubleValue() / new Double(getPerCount()));
		setPageCount((int) pageCount);

		if (pageCount == 0) {
			setPageIndex("1");
		} else if (Integer.parseInt(getPageIndex()) > pageCount) {
			setPageIndex(String.valueOf((int) pageCount));
		}
	}

	public String getQueryCondition() {
		return queryCondition;
	}

	public void setQueryCondition(String queryCondition) {
		this.queryCondition = queryCondition;
	}

	public String getDeleteCondition() {
		return deleteCondition;
	}

	public void setDeleteCondition(String deleteCondition) {
		this.deleteCondition = deleteCondition;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * 获取当前页的记录数
	 * 
	 * @return
	 */
	public int getRecordNum() {
		if (recordTotal < perCount) {
			recordNum = recordTotal.intValue();
		} else {
			recordNum = perCount;
		}

		return recordNum;
	}

	public void setRecordNum(int recordNum) {
		this.recordNum = recordNum;
	}

	public int getPerCount() {
		return perCount;
	}

	public void setPerCount(int perCount) {
		this.perCount = perCount;
	}

	public Long getRecordTotal() {
		return recordTotal;
	}

	public void setRecordTotal(Long recordTotal) {
		this.recordTotal = recordTotal;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public List<Basedata> getBasedataList() {
		return basedataList;
	}

	public void setBasedataList(List<Basedata> basedataList) {
		this.basedataList = basedataList;
	}

	public Map<String, String> getBasedataMap() {
		return basedataMap;
	}

	public void setBasedataMap(Map<String, String> basedataMap) {
		this.basedataMap = basedataMap;
	}

	public Map<String, String> getStatusMap() {
		statusMap = new TreeMap<String, String>();
		statusMap.put(Constants.ENABLE, Constants.ENABLE_TEXT);
		statusMap.put(Constants.DISABLE, Constants.DISABLE_TEXT);
		return statusMap;
	}

	public void setStatusMap(Map<String, String> statusMap) {
		this.statusMap = statusMap;
	}

	public Map<String, String> getYesNoMap() {
		yesNoMap = new TreeMap<String, String>();
		yesNoMap.put(Constants.ONE_STRING, Constants.YES);
		yesNoMap.put(Constants.ZERO_STRING, Constants.NO);
		return yesNoMap;
	}

	public void setYesNoMap(Map<String, String> yesNoMap) {
		this.yesNoMap = yesNoMap;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
