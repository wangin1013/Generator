package com.eastcode.server.system.action;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.eastcode.base.action.BaseAction;
import com.eastcode.server.system.domain.SystemMenu;
import com.eastcode.server.system.service.SystemMenuService;
import com.eastcode.server.system.view.SystemMenuView;
import com.eastcode.utils.Constants;
import com.eastcode.utils.DateUtil;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class SystemMenuAction extends BaseAction implements ModelDriven<SystemMenu> {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(SystemMenuAction.class);

	@Resource
	private SystemMenuService systemMenuService;

	private SystemMenu systemMenu = new SystemMenu();
	private SystemMenuView view = new SystemMenuView();

	public String execute() throws Exception {
		query();
		return LIST;
	}

	/**
	 * 保存数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {

		// 创建或修改时间
		if (systemMenu.getId() != null) {
			systemMenu.setUpdateTime(DateUtil.getToday());
		} else {
			systemMenu.setCreateTime(DateUtil.getToday());
		}
		systemMenuService.save(systemMenu);
		return DETAIL;
	}

	/**
	 * 查询数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String query() throws Exception {
		String conditions = view.getQueryCondition();

		String isLeaf = request.getParameter("isLeaf");
		if (null != isLeaf && isLeaf.length() > 0) {
			conditions += " and leaf='" + isLeaf + "'";
		}
		log.debug(conditions);

		// 设置列表页面分页相关设置
		Long recordTotal = systemMenuService.queryCountByConditions(conditions);

		view.setTurnPage(recordTotal);
		List<SystemMenu> resultList = systemMenuService.queryListByConditions(conditions, view.getPerCount(),
				Integer.parseInt(view.getPageIndex()));

		view.setResult(resultList);
		return LIST;
	}

	/**
	 * 删除数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		systemMenuService.deleteByCondition(view.getDeleteCondition());
		return execute();
	}

	/**
	 * 禁用数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String disable() throws Exception {

		systemMenu = systemMenuService.findById(new BigDecimal(view.getIds()));
		systemMenu.setStatus(view.getEnable());
		systemMenu.setUpdateTime(DateUtil.getToday());
		systemMenuService.save(systemMenu);
		return execute();
	}

	/**
	 * 准备数据
	 */
	public void prepare() {
		view.setBasedataList(systemMenuService.getBasedataList(Constants.SYSTEM_MENU_TYPE_CODE));
		view.setBasedataMap(systemMenuService.getBasedataMap(Constants.SYSTEM_MENU_TYPE_CODE));
	}

	/**
	 * 更新数据
	 */
	public void prepareUpdate() {
		systemMenu = systemMenuService.findById(new BigDecimal(view.getIds()));
		SystemMenu parent = systemMenu;
		if (parent.getParentId() != null) {
			parent = systemMenuService.findById(parent.getParentId());
			view.setParentName(parent.getName());
		}
	}

	/**
	 * 查看数据
	 */
	public void prepareDetail() {
		systemMenu = systemMenuService.findById(new BigDecimal(view.getIds()));
		SystemMenu parent = systemMenu;
		while (parent.getParentId() != null) {
			parent = systemMenuService.findById(parent.getParentId());
			if (null == view.getParentName() || view.getParentName().length() <= 0) {
				view.setParentName(parent.getName());
			} else {
				view.setParentName(parent.getName() + "->" + view.getParentName());
			}
		}
	}

	public SystemMenuView getView() {
		return view;
	}

	public void setView(SystemMenuView view) {
		this.view = view;
	}

	public SystemMenu getModel() {
		return systemMenu;
	}
}
