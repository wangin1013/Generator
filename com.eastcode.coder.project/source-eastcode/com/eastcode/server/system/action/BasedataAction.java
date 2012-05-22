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
import com.eastcode.server.system.service.BasedataService;
import com.eastcode.server.system.view.BasedataView;
import com.eastcode.utils.Constants;
import com.eastcode.utils.DateUtil;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class BasedataAction extends BaseAction implements ModelDriven<Basedata> {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(BasedataAction.class);

	@Resource
	private BasedataService basedataService;

	private Basedata basedata = new Basedata();
	private Datatype datatype = new Datatype();
	private BasedataView view = new BasedataView();

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
		if (basedata.getId() != null) {
			basedata.setUpdateTime(DateUtil.getToday());
		} else {
			basedata.setCreateTime(DateUtil.getToday());
		}
		basedataService.save(basedata);

		// 查询级联对象
		Datatype datatype = basedataService.findDatatypeById(basedata.getDatatype().getId());
		basedata.setDatatype(datatype);

		// 关联数据查询
		queryBasedata(basedata);

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
		Long recordTotal = basedataService.queryCountByConditions(view.getQueryCondition());

		view.setTurnPage(recordTotal);
		List<Basedata> resultList = basedataService.queryListByConditions(view.getQueryCondition(), view.getPerCount(),
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
		basedataService.deleteByCondition(view.getDeleteCondition());
		return execute();
	}

	/**
	 * 禁用数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String disable() throws Exception {

		basedata = basedataService.findById(new BigDecimal(view.getIds()));
		basedata.setStatus(view.getEnable());
		basedata.setUpdateTime(DateUtil.getToday());
		basedataService.save(basedata);

		return execute();
	}

	/**
	 * 插入数据前，检索字典类型
	 */
	public void queryDatatype() {
		String conditions = " and status='" + Constants.ENABLE + "'";
		List<Datatype> datatypeList = basedataService.queryDatatypeListByConditions(conditions);
		view.setDatatypeList(datatypeList);
	}

	/**
	 * 更新数据
	 */
	public void prepareUpdate() {
		basedata = basedataService.findById(new BigDecimal(view.getIds()));
	}

	public void prepare() {
		queryDatatype();
	}

	/**
	 * 查看数据
	 */
	public void prepareDetail() {
		basedata = basedataService.findById(new BigDecimal(view.getIds()));
		queryBasedata(basedata);
	}

	public void queryBasedata(Basedata basedata) {
		StringBuffer sb = new StringBuffer();
		sb.append(" and baseData.datatype.id='");
		sb.append(basedata.getDatatype().getId());
		sb.append("'");
		sb.append(" and baseData.id!='");
		sb.append(basedata.getId());
		sb.append("'");
		view.setOtherList(basedataService.queryListByConditions(sb.toString()));
	}

	public Datatype getDatatype() {
		return datatype;
	}

	public void setDatatype(Datatype datatype) {
		this.datatype = datatype;
	}

	public BasedataView getView() {
		return view;
	}

	public void setView(BasedataView view) {
		this.view = view;
	}

	public Basedata getModel() {
		return basedata;
	}
}
