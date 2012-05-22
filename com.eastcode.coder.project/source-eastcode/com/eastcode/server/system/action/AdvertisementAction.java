package com.eastcode.server.system.action;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.eastcode.server.main.action.FileUploadAction;
import com.eastcode.server.system.domain.Advertisement;
import com.eastcode.server.system.service.AdvertisementService;
import com.eastcode.server.system.view.AdvertisementView;
import com.eastcode.utils.Constants;
import com.eastcode.utils.DateUtil;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class AdvertisementAction extends FileUploadAction implements ModelDriven<Advertisement> {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(AdvertisementAction.class);

	@Resource
	private AdvertisementService advertisementService;

	private Advertisement advertisement = new Advertisement();

	private AdvertisementView view = new AdvertisementView();

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

		String path = upload(Constants.AD_PATH);

		if (path != null && path.length() > 0) {
			advertisement.setPath(Constants.AD_PATH + getTargetFileName());
		}

		// 创建或修改时间
		if (advertisement.getId() != null) {
			advertisement.setUpdateTime(DateUtil.getToday());
		} else {
			advertisement.setCreateTime(DateUtil.getToday());
		}

		advertisementService.save(advertisement);

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
		Long recordTotal = advertisementService.queryCountByConditions(view.getQueryCondition());

		view.setTurnPage(recordTotal);
		List<Advertisement> resultList = advertisementService.queryListByConditions(view.getQueryCondition(),
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
			advertisementService.deleteByCondition(view.getDeleteCondition());
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

		advertisement = advertisementService.findById(new BigDecimal(view.getIds()));
		advertisement.setUpdateTime(DateUtil.getToday());
		advertisementService.save(advertisement);
		return execute();
	}

	@Override
	public void prepare() {
		// 获取广告位
		view.setBasedataMap(advertisementService.getBasedataMap(Constants.SYSTEM_ADVERTISEMENT_CODE));
	}

	/**
	 * 更新数据
	 */
	public void prepareUpdate() {
		advertisement = advertisementService.findById(new BigDecimal(view.getIds()));
	}

	/**
	 * 查看数据
	 */
	public void prepareDetail() {
		advertisement = advertisementService.findById(new BigDecimal(view.getIds()));
	}

	/**
	 * 校验数据
	 * 
	 */
	public void validateSave() {
//		String conditions = " and code='" + advertisement.getCode() + "'";
//		long count = advertisementService.queryCountByConditions(conditions);
//
//		Advertisement advertisement_src = advertisementService.findById(advertisement.getId());
//
//		// 是否是自身修改
//		if (null != advertisement.getId() && null != advertisement_src.getCode()
//				&& advertisement_src.getCode().equals(advertisement.getCode())) {
//			if (count > 1) {
//				addFieldError("code", getText("error.existCode"));
//			}
//		} else {
//			if (count == 1) {
//				addFieldError("code", getText("error.existCode"));
//			}
//		}
	}

	public AdvertisementView getView() {
		return view;
	}

	public void setView(AdvertisementView view) {
		this.view = view;
	}

	public Advertisement getModel() {
		return advertisement;
	}
}
