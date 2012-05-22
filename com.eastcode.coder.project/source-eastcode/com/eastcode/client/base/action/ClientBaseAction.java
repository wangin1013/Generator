package com.eastcode.client.base.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.eastcode.base.action.BaseAction;
import com.eastcode.client.base.view.ClientBaseView;
import com.eastcode.utils.Constants;
import com.opensymphony.xwork2.ActionContext;

/**
 * 该类是所有action类的基类，用于获取BS对象，request请求， 进行action执行前的准备工作。
 * 
 * @author 王一进
 * 
 */
@Controller
@Scope("prototype")
public class ClientBaseAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private int defaultPort = 80;
	protected String actionName = "";
	protected String namespace = "";
	private String extension;
	private String key;
	private String value;

	/**
	 * 
	 * 设定响应的编码格式
	 * 
	 */
	protected void setEnconding() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding(Constants.UTF_8);
	}


	/**
	 * 从request中分离出域名及端口
	 * 
	 * @param request
	 * @return
	 */
	public String parseServer(HttpServletRequest request) {
		String serverName = "http://" + request.getServerName();
		int serverPort = request.getServerPort();
		if (serverPort != defaultPort) {
			serverName = serverName + ":" + String.valueOf(serverPort);
		}

		// 合并生成
		serverName += request.getContextPath() + "/";

		return serverName;
	}

	/**
	 * 每个方法执行之时，预处理工作
	 * 
	 * @param view
	 * @param clientBaseService
	 * @param listView
	 */
	protected void beforeAction(ClientBaseView view, ClientBaseView listView) {
		beforeAction(view);
		// 设定起始页
		setPageIndex(listView);

		setPageUrl(view);
	}

	/**
	 * 每个方法执行之时，预处理工作
	 * 
	 * @param view
	 * @param clientBaseService
	 * @param listView
	 */
	protected void beforeAction(ClientBaseView view) {
		setEnconding();
		view.setServer(parseServer(request));
	}

	/**
	 * 设置翻页的地址及参数
	 * 
	 * @param view
	 */
	protected void setPageUrl(ClientBaseView view) {
		view.setUrl(request.getRequestURL().toString());
		view.setQueryString(request.getQueryString());
		if (null != view.getQueryString())
			view.setQueryString(view.getQueryString().replaceAll("pageIndex", "a"));
	}

	/**
	 * 设定列表的起始页
	 * 
	 * @param view
	 */
	protected void setPageIndex(ClientBaseView view) {
		// 设定起始页
		String pageIndex = request.getParameter("pageIndex");
		if (null != pageIndex) {
			view.setPageIndex(pageIndex);
		}
	}

	/**
	 * 由服务端将页码下翻一页
	 * 
	 * @param clientBaseView
	 */
	protected void setNextPage(ClientBaseView clientBaseView) {

		String pageIndex = clientBaseView.getPageIndex();
		int index = Integer.parseInt(pageIndex);

		if (index < clientBaseView.getPageCount()) {
			clientBaseView.setPagePreIndex(String.valueOf(index));
			index++;
			clientBaseView.setPageNextIndex(String.valueOf(index));
		} else if (index == clientBaseView.getPageCount() && index > 1) {
			clientBaseView.setPageNextIndex(String.valueOf(index));
			clientBaseView.setPagePreIndex(String.valueOf(--index));
		}
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getExtension() {
		this.extension = "action";
		return this.extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
