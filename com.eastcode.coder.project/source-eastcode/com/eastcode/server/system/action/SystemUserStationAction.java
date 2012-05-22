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
import com.eastcode.server.system.domain.SystemUser;
import com.eastcode.server.system.domain.SystemUserStation;
import com.eastcode.server.system.service.SystemUserStationService;
import com.eastcode.server.system.view.SystemUserStationView;
import com.eastcode.utils.Constants;
import com.eastcode.utils.DateUtil;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class SystemUserStationAction extends BaseAction implements ModelDriven<SystemUserStation> {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(SystemUserStationAction.class);

	@Resource
	private SystemUserStationService systemUserStationService;

	private SystemUserStation systemUserStation = new SystemUserStation();
	private SystemUser systemUser = new SystemUser();
	private Station station = new Station();

	private SystemUserStationView view = new SystemUserStationView();

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
		if (systemUserStation.getId() != null) {
			systemUserStation.setUpdateTime(DateUtil.getToday());
		} else {
			systemUserStation.setCreateTime(DateUtil.getToday());
		}
		systemUserStationService.save(systemUserStation);
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
		Long recordTotal = systemUserStationService.queryCountByConditions(view.getQueryCondition());

		view.setTurnPage(recordTotal);
		List<SystemUserStation> resultList = systemUserStationService.queryListByConditions(view.getQueryCondition(),
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
		systemUserStationService.deleteByCondition(view.getDeleteCondition());
		return execute();
	}

	/**
	 * 准备数据
	 */
	public void prepare() {
		view.setBasedataList(systemUserStationService.getBasedataList(Constants.SYSTEM_MENU_TYPE_CODE));
		view.setBasedataMap(systemUserStationService.getBasedataMap(Constants.SYSTEM_MENU_TYPE_CODE));
	}

	/**
	 * 更新数据
	 */
	public void prepareUpdate() {
		systemUserStation = systemUserStationService.findById(new BigDecimal(view.getIds()));
	}

	/**
	 * 查看数据
	 */
	public void prepareDetail() {
		systemUserStation = systemUserStationService.findById(new BigDecimal(view.getIds()));
	}

	public SystemUserStationView getView() {
		return view;
	}

	public void setView(SystemUserStationView view) {
		this.view = view;
	}

	public SystemUser getSystemUser() {
		return systemUser;
	}

	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public SystemUserStation getModel() {
		return systemUserStation;
	}
}
