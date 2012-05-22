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
<s:form action="basedata">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td height="30">
			<s:include value="/server/detailTop.jsp"></s:include>
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
						<td class="td1">
							<s:text name="datatype.name" />：
						</td>
						<td class="td2">
							<s:property value="datatype.name" />
						</td>
					</tr>
					<tr>
						<td class="td1">
							<s:text name="code" />：
						</td>
						<td class="td2">
							<s:property value="code" />
							<s:hidden name="id" />
						</td>
					</tr>
					<tr>
						<td class="td1">
							<s:text name="name" />：
						</td>
						<td class="td2">
							<s:property value="name" />
						</td>
					</tr>
					<tr>
						<td class="td1">
							<s:text name="orderCode" />：
						</td>
						<td class="td2">
							<s:property value="orderCode" />
						</td>
					</tr>
					<tr>
						<td class="td1">
							<s:text name="status" />：
						</td>
						<td class="td2">
							<s:property value="view.statusMap[status]"/>
						</td>
					</tr>
					<tr>
						<td class="td1">
							<s:text name="createTime" />：
						</td>
						<td class="td2">
							<s:property value="createTime" />
						</td>
					</tr>
					<tr>
						<td class="td1">
							<s:text name="updateTime" />：
						</td>
						<td class="td2">
							<s:property value="updateTime" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="2">
							<span class="STYLE4"><s:text name="function_child" /></span>
						</td>
					</tr>
					<tr>
						<td colspan="2" bgcolor="#f3ffe3">
							<table width="99%" border="1" align="center" cellpadding="1" cellspacing="1">
								<tr>
									<td class="listTdTitle"><s:text name="datatype.name" /></td>
									<td class="listTdTitle"><s:text name="code" /></td>
									<td class="listTdTitle"><s:text name="name" /></td>
									<td class="listTdTitle"><s:text name="orderCode" /></td>
									<td class="listTdTitle"><s:text name="status" /></td>
								</tr>
								<s:iterator value="view.otherList" status="basedata">
									<tr>
										<td class="listTd"><s:property value="datatype.name" /></td>
										<td class="listTd"><s:property value="code" /></td>
										<td class="listTd"><s:property value="name" /></td>
										<td class="listTd"><s:property value="orderCode" /></td>
										<td class="listTd"><s:property value="view.statusMap[status]" /></td>
									</tr>
								</s:iterator>
							</table>
						</td>
					</tr>
					<tr>
						<td class="td1" colspan="2">
							<s:submit value="%{getText('res.back')}"></s:submit>
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
