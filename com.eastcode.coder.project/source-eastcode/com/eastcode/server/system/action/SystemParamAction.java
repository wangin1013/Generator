package com.eastcode.server.system.action;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.eastcode.base.action.BaseAction;
import com.eastcode.server.system.domain.SystemParam;
import com.eastcode.server.system.service.SystemParamService;
import com.eastcode.server.system.view.SystemParamView;
import com.eastcode.utils.DateUtil;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class SystemParamAction extends BaseAction implements ModelDriven<SystemParam> {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(SystemParamAction.class);

	@Resource
	private SystemParamService systemParamService;

	private SystemParam systemParam = new SystemParam();
	private SystemParamView view = new SystemParamView();

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
		if (systemParam.getId() != null) {
			systemParam.setUpdateTime(DateUtil.getToday());
		} else {
			systemParam.setCreateTime(DateUtil.getToday());
		}
		systemParamService.save(systemParam);
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
		Long recordTotal = systemParamService.queryCountByConditions(view.getQueryCondition());

		view.setTurnPage(recordTotal);
		List<SystemParam> resultList = systemParamService.queryListByConditions(view.getQueryCondition(),
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
		systemParamService.deleteByCondition(view.getDeleteCondition());
		return execute();
	}

	/**
	 * 禁用数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String disable() throws Exception {

		systemParam = systemParamService.findById(new BigDecimal(view.getIds()));
		systemParam.setStatus(view.getEnable());
		systemParam.setUpdateTime(DateUtil.getToday());
		systemParamService.save(systemParam);
		return execute();
	}

	/**
	 * 更新数据
	 */
	public void prepareUpdate() {
		systemParam = systemParamService.findById(new BigDecimal(view.getIds()));
	}

	/**
	 * 查看数据
	 */
	public void prepareDetail() {
		systemParam = systemParamService.findById(new BigDecimal(view.getIds()));
	}

	public SystemParamView getView() {
		return view;
	}

	public void setView(SystemParamView view) {
		this.view = view;
	}

	public SystemParam getModel() {
		return systemParam;
	}
}
