<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="<s:url value='/server/css/tab.css' />" type="text/css" />
<title><s:text name="function" /></title>
</head>
<body>
<s:form action="basedataSave">
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td height="30">
				<s:include value="/server/insertTop.jsp"></s:include>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="data_left_border">&nbsp;</td>
					<td>
					<table class="tab_body">
						<tr>
							<td class="td1"><s:text name="datatype.name" />：</td>
							<td class="td2"><s:select name="datatype.id" list="view.datatypeList" listKey="id" listValue="name" /></td>
						</tr>
						<tr>
							<td class="td1"><s:text name="code" />：</td>
							<td class="td2">
								<s:textfield name="code" maxlength="4" /> 
								<s:fielderror cssStyle="color: red;font-weight:bold;">
									<s:param>code</s:param>
								</s:fielderror>
								<s:hidden name="id" />
								<s:hidden name="createTime" />
							</td>
						</tr>
						<tr>
							<td class="td1"><s:text name="name" />：</td>
							<td class="td2">
								<s:textfield name="name" maxlength="20"/> 
								<s:fielderror cssStyle="color: red;font-weight:bold;">
									<s:param>name</s:param>
								</s:fielderror>
							</td>
						</tr>
						<tr>
							<td class="td1"><s:text name="orderCode" />：</td>
							<td class="td2">
								<s:textfield name="orderCode" maxlength="4" />
								<s:fielderror cssStyle="color: red;font-weight:bold;">
									<s:param>orderCode</s:param>
								</s:fielderror>
							</td>
						</tr>
						<tr>
							<td class="td1"><s:text name="status" />：</td>
							<td class="td2">
								<s:select name="status" list="view.statusMap"></s:select>
							</td>
						</tr>
						<tr>
							<td class="td1" colspan="2">
								<s:submit value="%{getText('res.save')}"></s:submit>
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
</s:form>
</body>
</html>
