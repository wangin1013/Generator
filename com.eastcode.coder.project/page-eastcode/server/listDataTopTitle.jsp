<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15px" style="background-image:url(<s:url value="/server/images/tab_03.gif" />);" height="30px">
		</td>
		<td style="background-image:url(<s:url value="/server/images/tab_05.gif" />);">
			<img src="<s:url value="/server/images/311.gif" />" width="16px" height="16px" /> 
			<span class="STYLE4"><s:text name="function" /></span>
		</td>
		<td style="background-image:url(<s:url value="/server/images/tab_05.gif" />);">
			<s:include value="/server/listTopButton.jsp">
			</s:include>
		</td>
    	<td width="14px" style="background-image:url(<s:url value="/server/images/tab_07.gif" />);"></td>
	</tr>
</table>