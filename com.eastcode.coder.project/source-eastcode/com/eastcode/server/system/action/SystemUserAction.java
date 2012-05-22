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
import com.eastcode.server.system.service.StationService;
import com.eastcode.server.system.service.SystemUserService;
import com.eastcode.server.system.service.SystemUserStationService;
import com.eastcode.server.system.view.SystemUserView;
import com.eastcode.utils.Constants;
import com.eastcode.utils.DateUtil;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class SystemUserAction extends BaseAction implements ModelDriven<SystemUser> {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(SystemUserAction.class);

	@Resource
	private SystemUserService systemUserService;
	@Resource
	private SystemUserStationService systemUserStationService;

	@Resource
	private StationService stationService;

	private SystemUser systemUser = new SystemUser();
	private String ownerName = "";
	private SystemUserView view = new SystemUserView();

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
		if (systemUser.getId() != null) {
			systemUser.setUpdateTime(DateUtil.getToday());
		} else {
			systemUser.setCreateTime(DateUtil.getToday());
		}
		systemUserService.save(systemUser);

		SystemUser loginSystemUser = (SystemUser) request.getSession().getAttribute(Constants.SYS_USER);

		if (systemUser.getId().intValue() == loginSystemUser.getId().intValue()) {
			request.getSession().setAttribute(Constants.SYS_USER, systemUser);
		}

		request.setAttribute(Constants.SUCCESS_SAVE_MESSAGE, getText("success.message"));
		return DETAIL;
	}

	/**
	 * 后台用户修改自身信息
	 * 
	 */
	public String updateSelf() throws Exception {
		return SUCCESS;
	}

	/**
	 * 后台用户修改自身信息
	 * 
	 */
	public String prepareUpdateSelf() throws Exception {
		systemUser = (SystemUser) request.getSession().getAttribute(Constants.SYS_USER);
		return SUCCESS;
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
		Long recordTotal = systemUserService.queryCountByConditions(view.getQueryCondition());

		view.setTurnPage(recordTotal);
		List<SystemUser> resultList = systemUserService.queryListByConditions(view.getQueryCondition(),
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
		try {
			systemUserService.deleteByCondition(view.getDeleteCondition());
		} catch (Exception sqlEx) {
			throw new Exception(sqlEx.getMessage() + " <br /> " + getText("error.delete"));
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

		systemUser = systemUserService.findById(new BigDecimal(view.getIds()));
		systemUser.setStatus(view.getEnable());
		systemUser.setUpdateTime(DateUtil.getToday());
		systemUserService.save(systemUser);
		return execute();
	}

	/**
	 * 数据准备
	 */
	public void prepare() {
		view.setBasedataList(systemUserService.getBasedataList(Constants.SYSTEM_USER_TYPE_CODE));
		view.setBasedataMap(systemUserService.getBasedataMap(Constants.SYSTEM_USER_TYPE_CODE));

		String id = request.getParameter("id");
		if (null != id && id.length() > 0) {
			systemUser = systemUserService.findById(id);
		}

		if (systemUser != null && systemUser.getId() != null) {
			List<Station> stationList = systemUserStationService.querySystemUserListBySystemUser(systemUser);
			view.setStationList(stationList);

			String conditions = " and status='" + Constants.ENABLE + "'";
			List<Station> stationAllList = stationService.queryListByConditions(conditions);
			for (int i = 0; stationList != null && i < stationList.size(); i++) {
				Station station = stationList.get(i);
				for (int j = 0; stationAllList != null && j < stationAllList.size(); j++) {
					Station stationInner = stationAllList.get(j);
					if (station.getId().equals(stationInner.getId())) {
						stationAllList.remove(j);
						j--;
					}
				}
			}
			view.setStationAllList(stationAllList);
		}
	}

	/**
	 * 更新数据
	 */
	public void prepareUpdate() {
		systemUser = systemUserService.findById(new BigDecimal(view.getIds()));
	}

	/**
	 * 保存分配的岗位
	 * 
	 * @throws Exception
	 */
	public String saveStations() throws Exception {
		systemUser = systemUserService.findById(systemUser.getId());
		String station = request.getParameter("stations");

		String[] stations = station.split(",");

		systemUserService.saveSystemUsersStations(systemUser, stations);

		prepare();
		return DETAIL;
	}

	/**
	 * 查看数据
	 */
	public void prepareDetail() {
		systemUser = systemUserService.findById(new BigDecimal(view.getIds()));

	}

	public SystemUserView getView() {
		return view;
	}

	public void setView(SystemUserView view) {
		this.view = view;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public SystemUser getModel() {
		return systemUser;
	}
}
