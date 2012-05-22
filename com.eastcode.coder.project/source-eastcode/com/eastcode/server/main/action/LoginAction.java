package com.eastcode.server.main.action;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.eastcode.base.action.BaseAction;
import com.eastcode.server.system.domain.SystemUser;
import com.eastcode.server.system.service.SystemUserService;
import com.eastcode.utils.Constants;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class LoginAction extends BaseAction implements ModelDriven<SystemUser> {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(LoginAction.class);

	@Resource
	private SystemUserService systemUserService;

	private SystemUser user = new SystemUser();

	public String execute() {

		SystemUser systemUser = null;

		if (user.getUserId() == null) {
			systemUser = (SystemUser) session.get(Constants.SYS_USER);
		} else {
			systemUser = systemUserService.findUserByUserIdAndPassword(user.getUserId(), user.getPassword());
		}

		log.debug("系统登录验证！");

		// 登录是否正确
		if (systemUser != null) {
			session.put(Constants.SYS_LOGINED, true);
			session.put(Constants.SYS_USER, systemUser);
			return MAIN;
		} else {
			addFieldError("userId", getText("error.validateFalse"));
			return logout();
		}
	}

	/**
	 * 系统用户退出
	 * 
	 * @return
	 */
	public String logout() {
		session.put(Constants.SYS_LOGINED, false);
		request.getSession().removeAttribute(Constants.SYS_USER);
		return LOGIN;
	}

	public SystemUser getModel() {
		return user;
	}
}
