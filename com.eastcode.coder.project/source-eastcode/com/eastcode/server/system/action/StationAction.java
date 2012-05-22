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
import com.eastcode.server.system.domain.SystemMenu;
import com.eastcode.server.system.service.StationRuleService;
import com.eastcode.server.system.service.StationService;
import com.eastcode.server.system.service.SystemMenuService;
import com.eastcode.server.system.view.StationView;
import com.eastcode.utils.Constants;
import com.eastcode.utils.DateUtil;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class StationAction extends BaseAction implements ModelDriven<Station> {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(StationAction.class);

	@Resource
	private StationService stationService;

	@Resource
	private StationRuleService stationRuleService;

	@Resource
	private SystemMenuService systemMenuService;

	private Station station = new Station();
	private String ownerName = "";
	private StationView view = new StationView();

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
		if (station.getId() != null) {
			station.setUpdateTime(DateUtil.getToday());
		} else {
			station.setCreateTime(DateUtil.getToday());
		}
		stationService.save(station);
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
		Long recordTotal = stationService.queryCountByConditions(view.getQueryCondition());

		view.setTurnPage(recordTotal);
		List<Station> resultList = stationService.queryListByConditions(view.getQueryCondition(), view.getPerCount(),
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
		try {
			stationService.deleteByCondition(view.getDeleteCondition());
		} catch (Exception ex) {
			throw new Exception(getText("error.delete"));
		}
		return execute();
	}

	/**
	 * 禁用数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String disable() throws Exception {

		station = stationService.findById(new BigDecimal(view.getIds()));
		station.setUpdateTime(DateUtil.getToday());
		stationService.save(station);
		return execute();
	}

	/**
	 * 准备数据
	 */
	public void prepare() {
		view.setBasedataList(stationService.getBasedataList(Constants.SYSTEM_USER_TYPE_CODE));
		view.setBasedataMap(stationService.getBasedataMap(Constants.SYSTEM_USER_TYPE_CODE));

		String id = request.getParameter("id");
		if (null != id && id.length() > 0) {
			station = stationService.findById(id);
		}
		if (null != station && null != station.getId()) {
			List<SystemMenu> systemMenuList = stationRuleService.querySystemMenuListByStation(station);
			view.setSystemMenuList(systemMenuList);

			String conditions = " and status='" + Constants.ENABLE + "' and leaf='" + Constants.ONE + "'";
			List<SystemMenu> systemMenuAllList = systemMenuService.queryListByConditions(conditions);

			for (int i = 0; systemMenuList != null && i < systemMenuList.size(); i++) {
				SystemMenu systemMenu = systemMenuList.get(i);
				for (int j = 0; systemMenuAllList != null && j < systemMenuAllList.size(); j++) {
					SystemMenu systemMenuInner = systemMenuAllList.get(j);
					if (systemMenu.getId().equals(systemMenuInner.getId())) {
						systemMenuAllList.remove(j);
						j--;
					}
				}
			}
			view.setSystemMenuAllList(systemMenuAllList);
		}
	}

	/**
	 * 岗位权限保存
	 * 
	 * @throws Exception
	 */
	public String saveSystemMenus() throws Exception {
		String systemMenu = request.getParameter("systemMenus");
		String[] systemMenus = systemMenu.split(",");
		stationService.saveSystemMenus(station, systemMenus);
		prepare();
		return DETAIL;
	}

	/**
	 * 更新数据
	 */
	public void prepareUpdate() {
		station = stationService.findById(new BigDecimal(view.getIds()));
	}

	/**
	 * 查看数据
	 */
	public void prepareDetail() {
		station = stationService.findById(new BigDecimal(view.getIds()));
	}

	/**
	 * 校验数据
	 * 
	 */
	public void validateSave() {
		String conditions = " and code='" + station.getCode() + "'";
		long count = stationService.queryCountByConditions(conditions);

		Station station_src = stationService.findById(station.getId());

		// 是否是自身修改
		if (null != station.getId() && null != station_src.getCode() && station_src.getCode().equals(station.getCode())) {
			if (count > 1) {
				addFieldError("code", getText("error.existCode"));
			}
		} else {
			if (count == 1) {
				addFieldError("code", getText("error.existCode"));
			}
		}
	}

	public StationView getView() {
		return view;
	}

	public void setView(StationView view) {
		this.view = view;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public Station getModel() {
		return station;
	}
}
