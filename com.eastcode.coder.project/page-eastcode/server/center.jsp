<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>无标题文档</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow: hidden;
}
-->
</style>
</head>

<body>
<table width="100%" height="100%" border="0" cellpadding="0"
	cellspacing="0" style="table-layout: fixed">
	<tr>
		<td background="<s:url value='/server/images/main_40.gif' />" style="width: 3px;">&nbsp;</td>
		<td width="177" style="border-right: solid 1px #9ad452;">
			<iframe name="leftFrame" height="100%" width="177" frameborder="0" src="<s:url value="/server/left.jsp" />"> 
				浏览器不支持嵌入式框架，或被配置为不显示嵌入式框架。 
			</iframe>
		</td>
		<td>
			<iframe name="contentFrame" id="contentFrame" src="<s:url value='/server/welcome.jsp' />" height="100%" width="100%" frameborder="0">
				浏览器不支持嵌入式框架，或被配置为不显示嵌入式框架。
			</iframe>
		</td>
		<td background="<s:url value='/server/images/main_42.gif' />" style="width:3px;">&nbsp;</td>
	</tr>
</table>
</body>
</html>
