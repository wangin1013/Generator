<%@page import="com.eastcode.utils.Constants"%>
<%@page import="com.eastcode.server.system.domain.SystemUser"%>
<%@page import="com.eastcode.utils.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>无标题文档</title>
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {
	color: #43860c;
	font-size: 12px;
}
</style>
<script type="text/javascript">
	function home(url) {
		window.top.location = url;
	}
	function back() {
		window.top.history.back();
	}
	function forward() {
		window.top.history.forward();
	}
	function reload() {
		window.top.history.go(0);
	}
	function update() {
		var url = "<s:url namespace="/admin/system" action="systemUserExtUpdate" />";
		window.showModalDialog(url,self,"dialogHeight=400px;dialogWidth=800px;status=no;");
	}
	function logout(url) {
		window.top.location = url;
	}
</script>
</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;">
	<s:hidden value="" name="id"></s:hidden>
  <tr>
    <td height="9" style="line-height:9px; background-image:url(images/main_04.gif)"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="97" height="9" style="background-image: url(<s:url value="/server/images/main_01.gif" />);">&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="47" style="background-image:url(<s:url value="/server/images/main_09.gif" />);"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="38" height="47" style="background-image:url(<s:url value="/server/images/main_06.gif" />);">&nbsp;</td>
        <td width="59"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="29" style="background-image:url(<s:url value="/server/images/main_07.gif" />);">&nbsp;</td>
          </tr>
          <tr>
            <td height="18" style="background-image:url(<s:url value="/server/images/main_14.gif" />);">
	            <table width="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;">
	              <tr>
	                <td  style="width:1px;">&nbsp;</td>
	                <td>
	                <span class="STYLE1">
	                	<%SystemUser user = (SystemUser)session.getAttribute(Constants.SYS_USER);
	                		out.print(user.getName());
	                	%>
	                </span>
	                </td>
	              </tr>
	            </table>
            </td>
          </tr>
        </table></td>
        <td width="155" style="background-image:url(<s:url value="/server/images/main_08.gif" />);">&nbsp;</td>
        <td>
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td height="23" valign="bottom"><img src="<s:url value="/server/images/main_12.gif" />" width="367" height="23" border="0" usemap="#Map" /></td>
	          </tr>
	        </table>
        </td>
        <td width="200" style="background-image:url(<s:url value="/server/images/main_11.gif" />);">
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td width="11%" height="23">&nbsp;</td>
	            <td width="89%" valign="bottom"><span class="STYLE1"><%=DateUtil.getTodayAndWeekC()%> </span></td>
	          </tr>
	        </table>
        </td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="5" style="line-height:5px; background-image:url(<s:url value="/server/images/main_18.gif" />)"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="180" style="background-image:url(<s:url value="/server/images/main_16.gif" />);"  style="line-height:5px;">&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
<map name="Map" id="Map">
<area shape="rect" coords="3,1,49,22" href="javascript:home('<s:url namespace="/" action="admin" />')" />
<area shape="rect" coords="52,2,95,21" href="javascript:back();" />
<area shape="rect" coords="102,2,144,21" href="javascript:forward();" />
<area shape="rect" coords="150,1,197,22" href="javascript:reload();" />
<area shape="rect" coords="210,2,304,20" href="javascript:update();" />
<area shape="rect" coords="314,1,361,23" href="javascript:logout('<s:url namespace="/admin" action="logoutAction" />')" />
</map>
</body>
</html>
