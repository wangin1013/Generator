<%@page import="com.eastcode.utils.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div align="center">
	<a href="javascript:void(0);" onclick="javascript:return delete_data('<s:property value="id" />');">
		<img title="<s:text name="res.delete"/>" alt="<s:text name="res.delete"/>" src="<s:url value="/server/images/010.gif" />" border="0" />
	</a>
</div>