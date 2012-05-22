<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<table border="0" align="right" cellpadding="0" cellspacing="0">
	<tr>
		<td width="60px">
		<table width="87%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="STYLE1">
					<div align="center"><s:checkbox name="checkedAll1" onclick="checkedAll(event);" /></div>
				</td>
				<td class="STYLE1">
				<div align="center"><s:text name="res.allSelect" /></div>
				</td>
			</tr>
		</table>
		</td>
		<td width="60px">
		<table width="90%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="STYLE1">
					<div align="center">
						<img src="<s:url value="/server/images/001.gif" />" width="14" height="14" />
					</div>
				</td>
				<td class="STYLE1">
					<div align="center">
						<a href="javascript:void(0);" onclick="javascript:return insert_click(event);"><s:text name="res.insert" /></a>
					</div>
				</td>
			</tr>
		</table>
		</td>
		<td width="52px">
		<table width="88%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="STYLE1">
					<div align="center">
						<img src="<s:url value="/server/images/083.gif" />" width="14" height="14" />
					</div>
				</td>
				<td class="STYLE1">
					<div align="center"><a href="javascript:void(0);" onclick="javascript:return delete_click('<s:property value="id" />');"><s:text name="res.delete" /></a></div>
				</td>
			</tr>
		</table>
		</td>
		<s:set name="number">${param.number}</s:set>
		
		<%
		int i = 0;
		String number = request.getParameter("listTopNumber");
		if(number!=null && number.length()>0) {
			String[] width = request.getParameter("listTopWidth").split(",");
			String[] imgs = request.getParameter("listTopImg").split(",");
			String[] actions = request.getParameter("listTopAction").split(",");
			String[] names = request.getParameter("listTopName").split(",");
			while( i < Integer.parseInt(number)){ %>
			<td width="<%=width[i]%>px">
			<table width="88%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="STYLE1">
						<div align="center">
							<img src="<%=imgs[i]%>" width="14" height="14" />
						</div>
					</td>
					<td class="STYLE1">
						<div align="center"><a href="<%=actions[i]%>"><%=names[i]%></a></div>
					</td>
				</tr>
			</table>
			</td>
			<%
			i++;
			}
		}
		%>
	</tr>
</table>