package com.eastcode.server.system.action;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.eastcode.base.action.BaseAction;
import com.eastcode.server.system.domain.Basedata;
import com.eastcode.server.system.domain.Datatype;
import com.eastcode.server.system.service.DatatypeService;
import com.eastcode.server.system.view.DatatypeView;
import com.eastcode.utils.DateUtil;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class DatatypeAction extends BaseAction implements ModelDriven<Datatype> {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(DatatypeAction.class);

	@Resource
	private DatatypeService datatypeService;

	private Datatype datatype = new Datatype();
	private DatatypeView view = new DatatypeView();

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
		if (datatype.getId() != null) {
			datatype.setUpdateTime(DateUtil.getToday());
		} else {
			datatype.setCreateTime(DateUtil.getToday());
		}
		datatypeService.save(datatype);
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
		Long recordTotal = datatypeService.queryCountByConditions(view.getQueryCondition());

		view.setTurnPage(recordTotal);
		List<Datatype> resultList = datatypeService.queryListByConditions(view.getQueryCondition(), view.getPerCount(),
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
		int count = datatypeService.deleteByCondition(view.getDeleteCondition(), view.getIds());
		if (count == 0) {
			this.addFieldError(ERROR, "存在已经使用的类型，无法删除！");
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

		datatype = datatypeService.findById(new BigDecimal(view.getIds()));
		datatype.setStatus(view.getEnable());
		datatype.setUpdateTime(DateUtil.getToday());
		datatypeService.save(datatype);
		return execute();
	}

	/**
	 * 更新数据
	 */
	public void prepareUpdate() {
		datatype = datatypeService.findById(new BigDecimal(view.getIds()));
	}

	/**
	 * 查看数据
	 */
	public void prepareDetail() {
		datatype = datatypeService.findById(new BigDecimal(view.getIds()));
		List<Basedata> basedataList = datatypeService.queryBasedataListByType(datatype.getId());
		view.setBasedataList(basedataList);
	}

	public DatatypeView getView() {
		return view;
	}

	public void setView(DatatypeView view) {
		this.view = view;
	}

	public Datatype getModel() {
		return datatype;
	}
}
