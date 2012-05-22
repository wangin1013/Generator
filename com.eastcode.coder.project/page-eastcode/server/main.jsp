<%@page import="com.eastcode.utils.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="icon" href="<s:url value="/server/favicon.ico" />" type="image/x-icon" />
<title>管理平台-<%=Constants.SITE_NAME%></title>
</head>
<frameset rows="61,*,24" cols="*" framespacing="0" frameborder="no" border="0">
	<frame src="<s:url value="/server/top.jsp" />" name="topFrame" scrolling="No"
		noresize="noresize" id="topFrame" />
	<frame src="<s:url value="/server/center.jsp" />" name="mainFrame" id="mainFrame" border="10px"/>
	<frame src="<s:url value="/server/down.jsp" />" name="bottomFrame" scrolling="No"
		noresize="noresize" id="bottomFrame" />
</frameset>
</html>
