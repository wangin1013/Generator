package com.eastcode.server.system.action;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.eastcode.server.main.action.FileUploadAction;
import com.eastcode.server.system.domain.SystemInfo;
import com.eastcode.server.system.service.SystemInfoService;
import com.eastcode.server.system.view.SystemInfoView;
import com.eastcode.utils.Constants;
import com.eastcode.utils.DateUtil;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class SystemInfoAction extends FileUploadAction implements ModelDriven<SystemInfo> {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(SystemInfoAction.class);

	@Resource
	private SystemInfoService systemInfoService;

	private SystemInfo systemInfo = new SystemInfo();
	private SystemInfoView view = new SystemInfoView();

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

		// 处理图片
		try {
			String path = upload(Constants.UPLOAD_PATH);
			if (path != null && path.length() > 0) {
				systemInfo.setFilePath(Constants.UPLOAD_PATH + "/" + getTargetFileName());
				log.debug(path);
			}
		} catch (Exception ex) {
			throw new Exception(getText("fileUploadFailed"));
		}

		// 创建或修改时间
		if (systemInfo.getId() != null) {
			systemInfo.setUpdateTime(DateUtil.getToday());
		} else {
			systemInfo.setCreateTime(DateUtil.getToday());
		}
		systemInfoService.save(systemInfo);
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
		Long recordTotal = systemInfoService.queryCountByConditions(view.getQueryCondition());

		view.setTurnPage(recordTotal);
		List<SystemInfo> resultList = systemInfoService.queryListByConditions(view.getQueryCondition(),
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
		systemInfoService.deleteByCondition(view.getDeleteCondition());
		return execute();
	}

	/**
	 * 禁用数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String disable() throws Exception {

		systemInfo = systemInfoService.findById(new BigDecimal(view.getIds()));
		systemInfo.setStatus(view.getEnable());
		systemInfo.setUpdateTime(DateUtil.getToday());
		systemInfoService.save(systemInfo);
		return execute();
	}

	/**
	 * 准备数据
	 */
	public void prepare() {
		// 系统信息类型
		view.setBasedataList(systemInfoService.getBasedataList(Constants.SYSTEM_INFO_CODE));
		view.setBasedataMap(systemInfoService.getBasedataMap(Constants.SYSTEM_INFO_CODE));

	}

	/**
	 * 更新数据
	 */
	public void prepareUpdate() {
		systemInfo = systemInfoService.findById(new BigDecimal(view.getIds()));
	}

	/**
	 * 查看数据
	 */
	public void prepareDetail() {
		systemInfo = systemInfoService.findById(new BigDecimal(view.getIds()));
	}

	public SystemInfoView getView() {
		return view;
	}

	public void setView(SystemInfoView view) {
		this.view = view;
	}

	public SystemInfo getModel() {
		return systemInfo;
	}
}
