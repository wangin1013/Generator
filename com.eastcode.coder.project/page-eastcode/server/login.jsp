<%@page import="com.eastcode.utils.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>后台管理</title>
<script type="text/javascript" src="<s:url value="/js/jquery-1.4.3.min.js" />"></script>
<script type="text/javascript" src="<s:url value="/js/hiAlerts/jquery.hiAlerts-min.js" />"></script>
<script type="text/javascript" src="<s:url value="/js/hiAlerts/jquery.ui.daraggable.js" />"></script>
<link rel="stylesheet" type="text/css" href="<s:url value="/js/hiAlerts/jquery.hiAlerts.css" />"></link>
<link rel="icon" href="<s:url value="/server/favicon.ico" />" type="image/x-icon" />
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
$(document).ready(function(){
	$.alerts.dialogClass = "over_3";
	var loginError = '<s:property value="fieldErrors.userId" />';
	var result = true;
	if(loginError!='') {
		hiOverAlert(loginError,5000);
		result = false;
	}
	
	var passwordError = '<s:property value="fieldErrors.password" />';
	if(result&&passwordError!='') {
		hiOverAlert(passwordError,5000);
	}
	$.alerts.dialogClass = null;
	
	$("#userId").focus(function(){ 
		  $("#userId").val($("#userId").val());
	});
	$("#userId").focus();
});
document.onkeydown = function(e){
   if(!e) e = window.event;//火狐中是 window.event
   if((e.keyCode || e.which) == 13){
	   document.getElementById('loginForm').submit();
   }
};
function restToTop() {
	if(window.name=="contentFrame"){
		window.top.location.reload();
	}
}
</script>
</head>
<body onload="restToTop()">
<s:form action="loginAction" method="post" name="loginForm" id="loginForm">
<table width="100%"  border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<td bgcolor="#e5f6cf">&nbsp;</td>
	</tr>
	<tr>
		<td height="608" style="background-image:url(<s:url value="/server/images/login_03.gif" />);">
		<table  width="862" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="266" style="background-image:url(<s:url value="/server/images/login_04.gif" />);">
				<div
					style="color: green; font-weight: bold; font-size: 19px; text-align: center;"><%=Constants.SITE_NAME%></div>
				</td>
			</tr>
			<tr>
				<td height="95">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="424" height="95" align="right" style="background-image:url(<s:url value="/server/images/login_06.gif" />);">
						</td>
						<td width="183" style="background-image:url(<s:url value="/server/images/login_07.gif" />);">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="21%" height="30">
										<div align="center"><span class="STYLE3">用户</span></div>
									</td>
									<td width="79%" height="30">
										<s:textfield tabindex="1" name="userId" id="userId" cssStyle="height: 18px; width: 130px; border: solid 1px #cadcb2; font-size: 12px; color: #81b432;" />
									</td>
								</tr>
								<tr>
									<td height="30">
										<div align="center"><span class="STYLE3">密码</span></div>
									</td>
									<td height="30">
										<s:password  tabindex="2" name="password" cssStyle="height: 18px; width: 130px; border: solid 1px #cadcb2; font-size: 12px; color: #81b432;" />
									</td>
								</tr>
								<tr>
									<td height="30">&nbsp;</td>
									<td height="30">
										<img src="<s:url value="/server/images/dl.gif" />" width="81" height="22" border="0" usemap="#Map" />
									</td>
								</tr>
							</table>
						</td>
						<td width="255" style="background-image:url(<s:url value="/server/images/login_08.gif" />);">&nbsp;</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td height="247" valign="top" style="background-image:url(<s:url value="/server/images/login_09.gif" />);">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="22%" height="30">&nbsp;</td>
						<td width="56%">&nbsp;</td>
						<td width="22%">&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td height="30">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="44%" height="20">&nbsp;</td>
								<td width="56%" class="STYLE4">版本 2010V1.0</td>
							</tr>
						</table>
						</td>
						<td>&nbsp;</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td bgcolor="#a2d962">&nbsp;</td>
	</tr>
</table>
</s:form>
<map name="Map">
	<area shape="rect" coords="3,3,36,19" href="javascript:document.getElementById('loginForm').submit();" />
	<area shape="rect" coords="40,3,78,18" href="javascript:void(0);" />
</map>
</body>
</html>
