<%@page import="com.eastcode.utils.SystemMenuTreeUtil"%>
<%@page import="com.eastcode.server.system.domain.SystemMenu"%>
<%@page import="com.eastcode.utils.Constants"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>菜单功能列表</title>
<link rel="stylesheet" href="<s:url value='/css/zTreeStyle/zTreeStyle.css' />" type="text/css" />
<link rel="stylesheet" href="<s:url value='/css/zTreeStyle/zTreeIcons.css' />" type="text/css" />
  
<script type="text/javascript" src="<s:url value='/js/jquery-1.4.3.min.js' />"></script>
<script type="text/javascript" src="<s:url value='/js/jquery-ztree-2.1.min.js' />"></script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE2 {color: #43860c; font-size: 12px; }

a:link {font-size:12px; text-decoration:none; color:#43860c;}
a:visited {font-size:12px; text-decoration:none; color:#43860c;}
a:hover{font-size:12px; text-decoration:none; color:#FF0000;}
-->
</style>
<script type="text/javascript">
<!--
// 功能列表
	var zTree;
	var demoIframe;

	var setting = {
		showLine: true,
		root:{ 
			isRoot:true,
			nodes:[]
		}
	};
	zNodes =[
		{ name:"电纸书服务平台", iconSkin:"sim1", target:"contentFrame", open:true,
			nodes: [
				
				{ name:"用户管理", iconSkin:"sim3", "url":"<s:url value="/admin/client/users.action" />", "target":"contentFrame"},
				{ name:"绑定设备", iconSkin:"sim3", "url":"<s:url value="/admin/client/usersDevice.action" />", "target":"contentFrame"},
				{ name:"设备类型", iconSkin:"sim5", "url":"<s:url value="/admin/client/deviceType.action" />", "target":"contentFrame"},
				{ name:"设备", iconSkin:"sim4", "url":"<s:url value="/admin/client/device.action" />", "target":"contentFrame"},
				{ name:"商品管理", iconSkin:"sim10", open:true,
					nodes:[
						{ name:"供应商管理", iconSkin:"sim1", "url":"<s:url value="/admin/content/supplier.action" />", "target":"contentFrame"},
						{ name:"商品分类管理", iconSkin:"sim2", "url":"<s:url value="/admin/content/productType.action" />", "target":"contentFrame"},
						{ name:"商品管理", iconSkin:"sim8", "url":"<s:url value="/admin/content/product.action" />", "target":"contentFrame"},
						{ name:"特殊商品", iconSkin:"sim7", "url":"<s:url value="/admin/content/productSpecial.action" />", "target":"contentFrame"},
						{ name:"图书管理", iconSkin:"sim11", "url":"<s:url value="/admin/content/book.action" />", "target":"contentFrame"},
						{ name:"新闻资讯", iconSkin:"sim3", "url":"<s:url value="/admin/content/news.action" />", "target":"contentFrame"},
						{ name:"商品资源划分", iconSkin:"sim4", "url":"<s:url value="/admin/content/productResource.action" />", "target":"contentFrame"},
						{ name:"供应商服务", iconSkin:"sim3", "url":"<s:url value="/admin/content/supplierInterface.action" />","target":"contentFrame"}
					]},
				{ name:"定制管理", iconSkin:"sim10", open:true,
					nodes:[
						{ name:"客户设置", iconSkin:"sim11", "url":"<s:url value="/admin/custom/customer.action" />", "target":"contentFrame"},
						{ name:"用户划分", iconSkin:"sim5", "url":"<s:url value="/admin/custom/deviceCustomer.action" />", "target":"contentFrame"},
						{ name:"定制分类", iconSkin:"sim4", "url":"<s:url value="/admin/custom/customType.action" />", "target":"contentFrame"},
						{ name:"模板管理", iconSkin:"sim3", "url":"<s:url value="/admin/custom/template.action" />","target":"contentFrame"},
						{ name:"模板规则", iconSkin:"sim2", "url":"<s:url value="/admin/custom/templateRule.action" />","target":"contentFrame"},
						{ name:"服务接口", iconSkin:"sim7", "url":"<s:url value="/admin/custom/serviceInterface.action" />","target":"contentFrame"},
						{ name:"设备转向", iconSkin:"sim2", "url":"<s:url value="/admin/custom/deviceForward.action" />","target":"contentFrame"},
						{ name:"资源分类", iconSkin:"sim11", "url":"<s:url value="/admin/custom/resourceType.action" />","target":"contentFrame"},
						{ name:"客户资源权限", iconSkin:"sim1", "url":"<s:url value="/admin/custom/customerResource.action" />","target":"contentFrame"}
					]},
				{name : "系统设置",iconSkin : "sim10",open : true,
					nodes : [
							{name : "数据字典类型",iconSkin : "sim2","url" : "<s:url value="/admin/system/datatype.action" />","target":"contentFrame"},
							{name : "数据字典",iconSkin : "sim5","url" : "<s:url value="/admin/system/basedata.action" />","target":"contentFrame"},
							{name : "系统参数",iconSkin : "sim6","url" : "<s:url value="/admin/system/systemParam.action" />","target":"contentFrame"},
							{name : "系统信息",iconSkin : "sim4","url" : "<s:url value="/admin/system/systemInfo.action" />","target":"contentFrame"},
							{name : "管理员账户",iconSkin : "sim12","url" : "<s:url value="/admin/system/systemUser.action" />","target":"contentFrame"},
							{name : "系统岗位",iconSkin : "sim1","url" : "<s:url value="/admin/system/station.action" />","target":"contentFrame"},
							{name : "功能菜单",iconSkin : "sim8","url" : "<s:url value="/admin/system/systemMenu.action" />","target":"contentFrame"},
							{name : "岗位权限",iconSkin : "sim7","url" : "<s:url value="/admin/system/stationRule.action" />","target":"contentFrame"},
							{name : "账户岗位",iconSkin : "sim6","url" : "<s:url value="/admin/system/systemUserStation.action" />","target":"contentFrame"}
							]
				} ]
		} ];
	
	zNodes =[
		 		{ name:"电纸书服务平台", iconSkin:"sim1", target:"contentFrame", open:true,
		 			nodes :[<%
		 			       	Object obj= session.getAttribute(Constants.SYS_MENU);
		 					List<SystemMenu> resultList = (List<SystemMenu>)obj;
		 					for(int i = 0; resultList!=null&&i<resultList.size();i++) {
		 						SystemMenu systemMenu = resultList.get(i);
		 						if(systemMenu.getParentId()==null) {
		 							out.print(SystemMenuTreeUtil.createMenu(systemMenu));
		 							if(i!=resultList.size()-1) out.print(",");
		 						}
		 					}
		 				%>]
		 		}];
	
	$(document).ready(function() {
		zTree = $("#tree").zTree(setting, zNodes);
	});
//-->
</script>
</head>

<body>
<table width="177" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed">
      <tr>
        <td height="26" style="background-image:url(<s:url value='/server/images/main_21.gif' />);">&nbsp;</td>
      </tr>
      <tr>
        <td  style="line-height:4px; background:url(<s:url value='/server/images/main_38.gif' />)">&nbsp;</td>
      </tr>
      <tr>
        <td>
			<ul id="tree" class="tree" style="overflow:auto;"></ul>
        </td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
