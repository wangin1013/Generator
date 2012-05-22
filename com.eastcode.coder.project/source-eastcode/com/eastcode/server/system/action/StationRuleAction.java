package com.eastcode.server.system.action;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.eastcode.base.action.BaseAction;
import com.eastcode.server.system.domain.Station;
import com.eastcode.server.system.domain.StationRule;
import com.eastcode.server.system.domain.SystemMenu;
import com.eastcode.server.system.service.StationRuleService;
import com.eastcode.server.system.view.StationRuleView;
import com.eastcode.utils.Constants;
import com.eastcode.utils.DateUtil;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class StationRuleAction extends BaseAction implements ModelDriven<StationRule> {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(StationRuleAction.class);

	@Resource
	private StationRuleService stationRuleService;

	private StationRule stationRule = new StationRule();
	private SystemMenu systemMenu = new SystemMenu();
	private Station station = new Station();

	private StationRuleView view = new StationRuleView();

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
		if (stationRule.getId() != null) {
			stationRule.setUpdateTime(DateUtil.getToday());
		} else {
			stationRule.setCreateTime(DateUtil.getToday());
		}
		stationRuleService.save(stationRule);
		return DETAIL;
	}

	/**
	 * 查询数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String query() throws Exception {
		log.debug(view.getQueryCondition());

		// 设置列表页面分页相关设置
		Long recordTotal = stationRuleService.queryCountByConditions(view.getQueryCondition());

		view.setTurnPage(recordTotal);
		List<StationRule> resultList = stationRuleService.queryListByConditions(view.getQueryCondition(),
				view.getPerCount(), Integer.parseInt(view.getPageIndex()));

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
		stationRuleService.deleteByCondition(view.getDeleteCondition());
		return execute();
	}

	/**
	 * 准备数据
	 */
	public void prepare() {
		view.setBasedataList(stationRuleService.getBasedataList(Constants.SYSTEM_MENU_TYPE_CODE));
		view.setBasedataMap(stationRuleService.getBasedataMap(Constants.SYSTEM_MENU_TYPE_CODE));
	}

	/**
	 * 更新数据
	 */
	public void prepareUpdate() {
		stationRule = stationRuleService.findById(new BigDecimal(view.getIds()));
	}

	/**
	 * 查看数据
	 */
	public void prepareDetail() {
		stationRule = stationRuleService.findById(new BigDecimal(view.getIds()));
	}

	/**
	 * 校验数据
	 * 
	 */
	public void validateSave() {
		String conditions = " and systemMenu.id='" + stationRule.getSystemMenu().getId() + "' and station.id='"
				+ stationRule.getStation().getId() + "'";
		long count = stationRuleService.queryCountByConditions(conditions);

		StationRule stationRule_src = stationRuleService.findById(stationRule.getId());

		// 是否是自身修改
		if (null != station.getId() && null != stationRule_src && null != stationRule_src.getSystemMenu().getId()) {
			if (count > 1 && stationRule_src.getSystemMenu().getId().equals(stationRule.getSystemMenu().getId())) {
				addFieldError("systemMenu.name", this.getText("error.systemMenuExist"));
			}
		} else {
			if (count == 1) {
				addFieldError("systemMenu.name", this.getText("error.systemMenuExist"));
			}
		}
	}

	public StationRuleView getView() {
		return view;
	}

	public void setView(StationRuleView view) {
		this.view = view;
	}

	public SystemMenu getSystemMenu() {
		return systemMenu;
	}

	public void setSystemMenu(SystemMenu systemMenu) {
		this.systemMenu = systemMenu;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public StationRule getModel() {
		return stationRule;
	}
}
