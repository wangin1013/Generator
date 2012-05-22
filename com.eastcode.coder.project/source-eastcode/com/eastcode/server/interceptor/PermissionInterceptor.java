package com.eastcode.server.interceptor;

import java.lang.reflect.Field;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.eastcode.server.main.action.MainAction;
import com.eastcode.server.main.service.MainService;
import com.eastcode.server.system.domain.SystemMenu;
import com.eastcode.server.system.domain.SystemUser;
import com.eastcode.utils.Constants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


public class PermissionInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Resource
	private MainService mainService;

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {

		// 获取上下文
		ActionContext actionContext = actionInvocation.getInvocationContext();
		// 获取Session
		HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);

		// 获取登录标识
		Boolean logined = (Boolean) request.getSession().getAttribute(Constants.SYS_LOGINED);
		SystemUser systemUser = (SystemUser) request.getSession().getAttribute(Constants.SYS_USER);

		// 是否登录
		if (logined != null && systemUser != null && true == logined) {

			boolean isValidate = validatePermission(actionInvocation, systemUser);

			if (isValidate) {
				return actionInvocation.invoke();
			} else {
				return Action.ERROR;
			}
		} else {
			return Action.LOGIN;
		}
	}

	/**
	 * 验证所请求的URL是否具有权限
	 * 
	 * @param actionInvocation
	 * @param systemUser
	 * @return
	 */
	public boolean validatePermission(ActionInvocation actionInvocation, SystemUser systemUser) {
		List<SystemMenu> systemMenuList = mainService.querySysteMenu(systemUser);

		String action = actionInvocation.getInvocationContext().getName();
		String namespace = (String) actionInvocation.getProxy().getNamespace();
		// 主程序及扩展功能（不受权限限制的）
		if (MainAction.class.equals(actionInvocation.getAction().getClass()) && action.length() > 0
				|| action.contains(Constants.EXT)) {
			return true;
		}

		for (int i = 0; systemMenuList != null && i < systemMenuList.size(); i++) {
			SystemMenu systemMenu = systemMenuList.get(i);
			if (systemMenu.getUrl() != null) {

				PermissionExt permissionExt = new PermissionExt();
				try {
					Field[] fields = permissionExt.getClass().getDeclaredFields();
					for (int j = 0; fields != null && j < fields.length; j++) {
						fields[j].setAccessible(true);
						action = action.replaceAll((String) fields[j].get(fields[j].getName()), "");
					}
				} catch (IllegalAccessException ex) {
					ex.printStackTrace();
				}

				String url = namespace + "/" + action + ".action";
				if (systemMenu.getUrl().contains(url))
					return true;
			}
		}

		return false;
	}
}
