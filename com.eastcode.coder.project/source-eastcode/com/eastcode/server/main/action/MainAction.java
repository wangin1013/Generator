package com.eastcode.server.main.action;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.eastcode.base.action.BaseAction;
import com.eastcode.server.main.service.MainService;
import com.eastcode.server.main.view.MainView;
import com.eastcode.server.system.domain.SystemMenu;
import com.eastcode.server.system.domain.SystemUser;
import com.eastcode.utils.Constants;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class MainAction extends BaseAction implements ModelDriven<SystemMenu> {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(MainAction.class);

	@Resource
	private MainService mainService;

	private MainView view = new MainView();

	private SystemMenu systemMenu = new SystemMenu();

	public String execute() {
		log.debug("根据权限获取对应用户的功能菜单！");
		SystemUser systemUser = (SystemUser) session.get(Constants.SYS_USER);
		view.setResultList(mainService.querySysteMenu(systemUser));

		request.getSession().setAttribute(Constants.SYS_MENU, view.getResultList());
		request.getSession().setAttribute(Constants.SYS_USER, systemUser);

		view.setJavaVersion(System.getProperty("java.version"));
		view.setUserDir(System.getProperty("user.dir"));
		view.setTempDir(System.getProperty("temp.dir"));

		view.setOsArch(System.getProperty("os.arch"));
		view.setOsName(System.getProperty("os.name"));
		view.setOsVersion(System.getProperty("os.version"));

		request.getSession().setAttribute(Constants.MAIN_VIEW, view);

		return MAIN;
	}

	/**
	 * 清除缓存
	 * 
	 * @return
	 */
	public String removeCache() {
		getDefaultCache().removeAll();
		return execute();
	}

	public SystemMenu getModel() {
		return systemMenu;
	}

	public MainView getView() {
		return view;
	}

	public void setView(MainView view) {
		this.view = view;
	}

}
