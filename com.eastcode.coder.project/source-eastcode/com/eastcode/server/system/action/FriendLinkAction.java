package com.eastcode.server.system.action;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.eastcode.base.action.BaseAction;
import com.eastcode.server.system.domain.FriendLink;
import com.eastcode.server.system.service.FriendLinkService;
import com.eastcode.server.system.view.FriendLinkView;
import com.eastcode.utils.DateUtil;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class FriendLinkAction extends BaseAction implements ModelDriven<FriendLink> {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(FriendLinkAction.class);

	@Resource
	private FriendLinkService friendLinkService;

	private FriendLink friendLink = new FriendLink();
	private FriendLinkView view = new FriendLinkView();

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
		if (friendLink.getId() != null) {
			friendLink.setUpdateTime(DateUtil.getToday());
		} else {
			friendLink.setCreateTime(DateUtil.getToday());
		}
		friendLinkService.save(friendLink);

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
		Long recordTotal = friendLinkService.queryCountByConditions(view.getQueryCondition());

		view.setTurnPage(recordTotal);
		List<FriendLink> resultList = friendLinkService.queryListByConditions(view.getQueryCondition(),
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
		friendLinkService.deleteByCondition(view.getDeleteCondition());
		return execute();
	}

	/**
	 * 禁用数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String disable() throws Exception {

		friendLink = friendLinkService.findById(new BigDecimal(view.getIds()));
		friendLink.setStatus(view.getEnable());
		friendLink.setUpdateTime(DateUtil.getToday());
		friendLinkService.save(friendLink);

		return execute();
	}

	/**
	 * 更新数据
	 */
	public void prepareUpdate() {
		friendLink = friendLinkService.findById(new BigDecimal(view.getIds()));
	}

	/**
	 * 查看数据
	 */
	public void prepareDetail() {
		friendLink = friendLinkService.findById(new BigDecimal(view.getIds()));
	}

	public FriendLinkView getView() {
		return view;
	}

	public void setView(FriendLinkView view) {
		this.view = view;
	}

	public FriendLink getModel() {
		return friendLink;
	}
}
