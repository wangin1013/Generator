<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="cache-control" content="no-store" />

<title><s:text name="function" /></title>
<link rel="icon" href="<s:url value="/server/favicon.ico" />" type="image/x-icon" />
<link rel="stylesheet" href="<s:url value='/server/css/tab.css' />" type="text/css" />
<link rel="stylesheet" href="<s:url value='/css/jquery.tabs.css' />" type="text/css" />
<link rel="stylesheet" href="<s:url value='/css/jquery.tabs-ie.css' />" type="text/css" />

<script type="text/javascript" src="<s:url value='/js/jquery-1.4.3.min.js' />"></script>
<script type="text/javascript" src="<s:url value='/js/page.js' />"></script>
<script type="text/javascript" src="<s:url value='/js/jquery.tabs.min.js' />"></script>
<script type="text/javascript" src="<s:url value='/js/jquery.multiselects.min.js' />"></script>
<script type="text/javascript">
// 时间戳
function timestamp(){
	var timestamp = Date.parse(new Date());
	return timestamp;
}
// 插入数据
function insert_click_base(event,url) {
	var result=true;

	document.getElementById("insertForm").action=url;
	document.getElementById("insertForm").submit();
	
	return result;
}
// 更新数据
function update_click_base(event,url) {
	var result=true;
	var ids = document.getElementById("view.ids");
	var checkedNum = getSelections("checked","id_list",ids);
	if(checkedNum>1) {
		alert("<s:text name="single_select_data" />");
		ids.value="";
		result = false;
	} else if(checkedNum==0) {
		alert("<s:text name="select_update_data" />");
		ids.value="";
		result = false;
	}
	if(result) {
		document.getElementById("listForm").action=url;
		document.getElementById("listForm").submit();
	}
	
	return result;
}
function update_data_base(id,url) {
	var result=true;
	var ids = document.getElementById("view.ids");
	ids.value=id;
	document.getElementById("listForm").action=url;
	document.getElementById("listForm").submit();
	
	return true;
}
//批量更新数据
function update_click_base_ext(event,url) {
	var result=true;
	var ids = document.getElementById("view.ids");
	var checkedNum = getSelections("checked","id_list",ids);
	if(checkedNum==0) {
		alert("<s:text name="select_update_data" />");
		ids.value="";
		result = false;
	}

	if(result) {
		document.getElementById("listForm").action=url;
		document.getElementById("listForm").submit();
	}
	
	return result;
}
// 查看数据
function detail_data_base(id,url) {
	var result = true;
	var ids = document.getElementById("view.ids");
	ids.value=id;
	document.getElementById("listForm").action=url;
	document.getElementById("listForm").submit();
	return result;
}
// 删除数据
function delete_click_base(event,url,target){
	var result = true;
	var ids = document.getElementById("view.ids");
	var checkedNum = getSelections("checked","id_list",ids);
	if(checkedNum==0) {
		alert("<s:text name="select_delete_data" />");
		ids.value="";
		result = false;
	} else {
		if(target!=undefined&&target!="") {
			document.getElementById("view.deleteCondition").value = " and "+target+".id in("+ids.value+")";
		} else {
			document.getElementById("view.deleteCondition").value = " and id in("+ids.value+")";
		}
		
		document.getElementById("listForm").action=url;
		document.getElementById("listForm").submit();
		
	}
	
	return result;
}
function delete_data_base(id,url,target){
	var result = true;
	var ids = document.getElementById("view.ids");
	ids.value = id;
	if(target!=undefined&&target!="") {
		document.getElementById("view.deleteCondition").value = " and "+target+".id in("+ids.value+")";
	} else {
		document.getElementById("view.deleteCondition").value = " and id in("+ids.value+")";
	}

	document.getElementById("listForm").action=url;
	document.getElementById("listForm").submit();
	
	return result;
}
//批量禁用数据
function disable_click_base(enable,url){
	var result = true;
	var ids = document.getElementById("view.ids");
	var checkedNum = getSelections("checked","id_list",ids);
	if(checkedNum==0) {
		alert("<s:text name="select_delete_data" />");
		ids.value="";
		result = false;
	} else {
		document.getElementById("view.enable").value=enable;
		document.getElementById("listForm").action=url;
		document.getElementById("listForm").submit();
	}
	
	return result;
}
// 禁用数据
function disable_data_base(id,enable,url){
	var result = true;
	var ids = document.getElementById("view.ids");
	document.getElementById("view.enable").value=enable;
	ids.value = id;
	document.getElementById("listForm").action=url;
	document.getElementById("listForm").submit();
	
	return result;
}
// 搜索数据
function search_click_base(event,url,searchCondition){
	var result = true;
	document.getElementById("view.queryCondition").value=searchCondition;
	document.getElementById("listForm").action=url;
	document.getElementById("listForm").submit();
	
	return result;
}
</script>