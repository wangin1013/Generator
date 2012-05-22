<%@page import="com.eastcode.utils.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<s:include value="/server/header.jsp"></s:include>
<script type="text/javascript">
//插入数据
function insert_click(event) {
	return insert_click_base(event,"<s:url action="systemParamInsert" />");
}
//更新数据
function update_click(event) {
	return update_click_base(event,"<s:url action="systemParamUpdate" />");
}
function update_data(id) {
	return update_data_base(id,"<s:url action="systemParamUpdate" />");
}
//查看数据
function detail_data(id) {
	return detail_data_base(id,"<s:url action="systemParamDetail" />");
}
//删除数据
function delete_click(event){
	return delete_click_base(event,"<s:url action="systemParamDelete" />");
}
function delete_data(id){
	return delete_data_base(id,"<s:url action="systemParamDelete" />");
}
//禁用，启用数据
function disable_data(id,enable){
	return disable_data_base(id,enable,"<s:url action="systemParamDisable" />");
}
//搜索
function search_click(event){
	var queryCondition = "";
	var search_name = document.getElementById("view.searchName").value.trim();
	var search_value = document.getElementById("view.searchValue").value.trim();
	var search_code = document.getElementById("view.searchCode").value.trim();
	var search_status = document.getElementById("view.searchStatus").value.trim();
	
	if(search_name!="") {
		queryCondition = " and name like '%" + search_name + "%'";
	}
	if(search_value!="") {
		queryCondition = " and value like '%" + search_value + "%'";
	}
	if(search_code) {
		queryCondition+=" and code like '%" + search_code + "%'";
	}
	
	if(search_status!="") {
		queryCondition+=" and status ='"+search_status+"'";
	}
	return search_click_base(event,"<s:url action="systemParam" />",queryCondition);
}
$(document).keydown(function(e){
	   if(!e) e = window.event;//火狐中是 window.event
	   if((e.keyCode || e.which) == 13){
		   search_click(e);
	   }
});
$(document).ready(function(){
	$("#view\\.searchCode").focus(function(){ 
		  $("#view\\.searchCode").val($("#view\\.searchCode").val());
	});
	$("#view\\.searchCode").focus();
});
</script>
</head>
<body>
<s:form id="insertForm" action="systemParam"></s:form>
<s:form id="listForm" action="systemParam">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td height="30">
			<s:include value="/server/listSearchTitle.jsp"></s:include>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="9" style="background-image:url(<s:url value="/server/images/tab_12.gif" />);">&nbsp;</td>
				<td>
					<table>
						<tr>
							<td align="right" width="80px">
								<s:text name="searchCode"></s:text>：
							</td>
							<td>
								<s:textfield id="view.searchCode" name="view.searchCode"></s:textfield>
							</td>
							<td align="right" width="80px">
								<s:text name="searchName"></s:text>：
							</td>
							<td>
								<s:textfield id="view.searchName" name="view.searchName"></s:textfield>
							</td>
						</tr>
						<tr>
							<td align="right" width="80px">
								<s:text name="searchValue"></s:text>：
							</td>
							<td>
								<s:textfield id="view.searchValue" name="view.searchValue"></s:textfield>
							</td>
							<td align="right">
								<s:text name="searchStatus" />：
							</td>
							<td>
								<s:select id="view.searchStatus" name="view.searchStatus" emptyOption="true" list="view.statusMap" ></s:select>
							</td>
							<td align="right">
								<a href="javascript:void(0);" onclick="search_click();">
								<s:text name="res.search" />
								<img src="<s:url value="/server/images/a1.gif" />" width="14" height="14" border="0" /></a>
							</td>
						</tr>
					</table>
				</td>
				<td width="9"  style="background-image:url(<s:url value="/server/images/tab_16.gif" />);"></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="9" style="background-image:url(<s:url value="/server/images/tab_20.gif" />);"></td>
				<td style="background-image:url(<s:url value="/server/images/tab_21.gif" />);">&nbsp;</td>
				<td width="14"><img src="<s:url value="/server/images/tab_22.gif" />" width="14"/></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td height="30px">
			<s:include value="/server/listDataTopTitle.jsp"></s:include>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="data_left_border">&nbsp;</td>
				<td bgcolor="#f3ffe3">
				<table width="99%" border="0" align="center" cellpadding="0"
					cellspacing="1" bgcolor="#c0de98" onmouseover="changeto(event)"
					onmouseout="changeback(event)">
					<tr>
						<td class="listTdTitle"><s:text name="res.select" /></td>
						<td class="listTdTitle"><s:text name="code" /></td>
						<td class="listTdTitle"><s:text name="name" /></td>
						<td class="listTdTitle"><s:text name="value" /></td>
						<td class="listTdTitle"><s:text name="status" /></td>
						<td class="listTdTitle"><s:text name="res.detailEdit" /></td>
						<td class="listTdTitle"><s:text name="res.deteletEnable" /></td>
					</tr>
					<s:iterator value="view.result" status="result">
						<tr>
							<td class="listTd"><s:checkbox name="checked" /> <s:hidden name="id_list" value="%{id}" /></td>
							<td class="listTd"><s:property value="code" /></td>
							<td class="listTd"><s:property value="name" /></td>
							<td class="listTd"><s:property value="value" /></td>
							<td class="listTd"><s:property value="view.statusMap[status]" /></td>
							<td class="listTd"><s:include value="/server/listEditButton.jsp"></s:include></td>
							<td class="listTd"><s:include value="/server/listDeleteButton.jsp"></s:include></td>
						</tr>
					</s:iterator>
				</table>
				</td>
				<td width="9" style="background-image:url(<s:url value="/server/images/tab_16.gif" />);">&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td height="29">
			<s:include value="/server/listbottom.jsp">
				<s:param name="formId">listForm</s:param>
			</s:include>
		</td>
	</tr>
</table>
</s:form>
</body>
</html>
