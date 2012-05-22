<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="<s:url value='/server/css/tab.css' />" type="text/css" />
<title>后台管理 - 错误提示</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow: hidden;
}

.STYLE3 {
	color: #528311;
	font-size: 12px;
}

.STYLE4 {
	color: #42870a;
	font-size: 12px;
}
-->
</style>
<script type="text/javascript">
	alert('不好意思，出错了。\n'+'<s:property value="exception.message" escape="false"/>');
	window.top.history.back();
</script>
</head>

<body>
	<div>
		不好意思，出错了。<br />
		<font style="color:red;font-weight:bold;">
			<s:property value="exception.message" escape="false"/>
		</font>
	</div>
</body>

</html>
