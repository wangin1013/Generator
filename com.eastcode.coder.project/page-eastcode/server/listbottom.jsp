<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15" height="29"><img
			src="<s:url value="/server/images/tab_20.gif" />" width="15"
			height="29" /></td>
		<td background="<s:url value="/server/images/tab_21.gif" />">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="25%" height="29" nowrap="nowrap">
					<span class="STYLE1">
						共<s:property value="view.recordTotal" />条纪录，
						当前第<s:property value="view.pageIndex" />/<s:property value="view.pageCount" />页，
						每页<s:property value="view.perCount" />条纪录
						<s:hidden id="view.pageCount" name="view.pageCount" />
						<s:hidden id="view.deleteCondition" name="view.deleteCondition"/>
						<s:hidden id="view.queryCondition" name="view.queryCondition"/>
						<s:hidden id="view.ids" name="view.ids" value="" />
						<s:hidden id="view.enable" name="view.enable"/>
					</span>
				</td>
				<td width="75%" valign="top" class="STYLE1">
					<div align="right">
						<table width="352" height="20" border="0" cellpadding="0"
							cellspacing="0">
							<tr>
								<td width="62" height="22" valign="middle">
									<div align="right">
										<a href="javascript:void(0);" onclick="return toPageFirstIndex('${param.formId}');">
										<img src="<s:url value="/server/images/first.gif" />" width="37" border="0" height="15" />
										</a>
									</div>
								</td>
								<td width="50" height="22" valign="middle">
									<div align="right">
										<a href="javascript:void(0);" onclick="return toPagePreIndex('${param.formId}');">
											<img src="<s:url value="/server/images/back.gif" />" width="43" height="15" border="0" />
										</a>
									</div>
								</td>
								<td width="54" height="22" valign="middle">
									<div align="right">
										<a href="javascript:void(0);" onclick="return toPageNextIndex('${param.formId}');">
											<img src="<s:url value="/server/images/next.gif" />" width="43" height="15" border="0" />
										</a>
									</div>
								</td>
								<td width="49" height="22" valign="middle">
									<div align="right">
										<a href="javascript:void(0);" onclick="return toPageLastIndex('${param.formId}');">
											<img src="<s:url value="/server/images/last.gif" />" width="37" height="15" border="0" />
										</a>
									</div>
								</td>
								<td width="59" height="22" valign="middle">
									<div align="right">转到第</div>
								</td>
								<td width="25" height="22" valign="middle">
									<span class="STYLE7">
										<s:textfield name="view.pageIndex" id="view.pageIndex" cssStyle="height: 10px; width: 25px; size:5;" cssClass="STYLE1">
										</s:textfield>
									</span>
								</td>
								<td width="23" height="22" valign="middle">页</td>
								<td width="30" height="22" valign="middle">
									<a href="javascript:void(0);" onclick="return toPageInputIndex('${param.formId}');">
										<img src="<s:url value="/server/images/go.gif" />" width="37" height="15" border="0" />
									</a>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
		</td>
		<td width="14">
		<img src="<s:url value="/server/images/tab_22.gif" />" width="14" height="29" /></td>
	</tr>
</table>