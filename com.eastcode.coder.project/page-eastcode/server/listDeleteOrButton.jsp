<%@page import="com.eastcode.utils.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div align="center">
	
	<s:set name="status" value="status"></s:set>
	
	<s:if test='%{#status=="00"}'>
		<a href="javascript:void(0);" onclick="javascript:return delete_data('<s:property value="id" />');">
			<img title="<s:text name="res.delete"/>" alt="<s:text name="res.delete"/>" src="<s:url value="/server/images/010.gif" />" border="0" />
		</a>|
	</s:if>
	<s:if test='%{#status=="00"}'>
		<a href="javascript:void(0);" onclick="javascript:return disable_data('<s:property value="id" />','<%=Constants.ENABLE%>');">
			<img title="<s:text name="res.enable"/>" alt="<s:text name="res.enable"/>" src="<s:url value="/server/images/upper.gif" />" border="0" />
		</a>
	</s:if>
	<s:else>
		<a href="javascript:void(0);" onclick="javascript:return disable_data('<s:property value="id" />','<%=Constants.DISABLE%>');">
			<img title="<s:text name="res.disable"/>" alt="<s:text name="res.disable"/>" src="<s:url value="/server/images/down.gif" />" border="0" />
		</a>
	</s:else>
</div>