package com.eastcode.base.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.eastcode.utils.Constants;
import com.eastcode.utils.EhcacheUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * 该类是所有action类的基类，request请求， 进行action执行前的准备工作。
 * 
 * @author 王一进
 * 
 */
@Controller
@Scope("prototype")
public class BaseAction extends ActionSupport implements Preparable, ServletRequestAware, ServletResponseAware,
		SessionAware, ServletContextAware {

	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory.getLog(BaseAction.class);

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Map<String, Object> session;
	protected ServletContext servletContext;

	protected static final String LIST = "list";
	protected static final String DETAIL = "detail";
	protected static final String MAIN = "main";

	public void prepare() {

	}

	/**
	 * struts2默认发方法
	 * 
	 */
	public String execute() throws Exception {
		return LIST;
	}

	/**
	 * 插入数据页面
	 * 
	 */
	public String insert() throws Exception {
		return INPUT;
	}

	/**
	 * 更新数据页面
	 * 
	 */
	public String update() throws Exception {
		return INPUT;
	}

	/**
	 * 明细数据
	 * 
	 */
	public String detail() throws Exception {
		return DETAIL;
	}

	/**
	 * action执行前的准备工作
	 */
	public void prepareAction() {
		log.debug("BaseAction的prepare方法！");
	}

	/**
	 * 设置request
	 * 
	 * @param request
	 */
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * 设置response
	 * 
	 * @param response
	 */
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	/**
	 * 获取spring的bean
	 * 
	 * @param bean
	 * @return
	 */
	public Object getBean(String bean) {
		ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);

		return context.getBean(bean);
	}

	/**
	 * 获取默认缓存
	 * 
	 * @return
	 */
	public Cache getDefaultCache() {

		Cache cache = EhcacheUtil.getCache(Constants.DEFAULT_EHCACHE);

		return cache;
	}

	/**
	 * 获取缓存对象
	 * 
	 * @return
	 */
	public Object getCacheObject(Object key) {

		Cache cache = EhcacheUtil.getCache(Constants.DEFAULT_EHCACHE);

		if (cache.get(key) != null) {
			return cache.get(key).getValue();
		}
		return null;
	}

	/**
	 * 放置缓存对象
	 * 
	 * @return
	 */
	public void putCacheObject(Element element) {

		Cache cache = EhcacheUtil.getCache(Constants.DEFAULT_EHCACHE);

		cache.put(element);
	}

	/**
	 * 创建缓存对象
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Element createElement(Object key, Object value) {

		return EhcacheUtil.createElement(key, value);
	}
}
