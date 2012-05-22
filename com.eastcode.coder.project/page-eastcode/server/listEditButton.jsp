<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div align="center">
	<a href="javascript:void(0);" onclick="javascript:return update_data('<s:property value="id" />');">
		<img title="<s:text name="res.edit"/>" alt="<s:text name="res.edit"/>" src="<s:url value="/server/images/037.gif" />" border="0" />
	</a>|
	<a href="javascript:void(0);" onclick="javascript:return detail_data('<s:property value="id" />');">
		<img title="<s:text name="res.detail"/>" alt="<s:text name="res.detail"/>" src="<s:url value="/server/images/a1.gif" />" border="0" />
	</a>
</div>