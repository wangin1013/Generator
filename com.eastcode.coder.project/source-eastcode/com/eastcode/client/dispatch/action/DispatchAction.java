package com.eastcode.client.dispatch.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.eastcode.client.base.action.ClientBaseAction;
import com.eastcode.utils.Constants;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class DispatchAction extends ClientBaseAction {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(DispatchAction.class);

	public String execute() throws Exception {
		log.debug("分发模块！");

		setNamespace(Constants.CLIENT);
		// 设定action名称用于跳转到该action
		setActionName(ActionContext.getContext().getName());

		return SUCCESS;
	}
}
