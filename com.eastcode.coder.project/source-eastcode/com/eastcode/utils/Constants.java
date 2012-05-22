package com.eastcode.utils;

import java.util.Map;
import java.util.TreeMap;

public class Constants {

	public static String SITE_NAME= "";
	public static String COPYRIGHT_BOTTOM = "";
	public static final String CLIENT = "/client";
	public static final String DISABLE = "00";
	public static final String ENABLE = "01";
	public static final String ENABLE_02 = "02";
	public static final String ENABLE_03 = "03";

	public static final String COMMA = ",";

	public static final String STAR = "#";

	public static int ZERO_NUM = 0;
	public static int ONE_NUM = 1;
	public static int TWO_NUM = 2;
	public static int THREE_NUM = 3;

	public static String UTF_8 = "UTF-8";

	public static String ISO_8859_1 = "ISO-8859-1";

	public static String GB2312 = "GB2312";

	public static Integer ZERO_INTEGER = new Integer(ZERO_NUM);
	public static Integer ONE_INTEGER = new Integer(ONE_NUM);
	public static Integer TWO_INTEGER = new Integer(TWO_NUM);
	public static Integer THREE_INTEGER = new Integer(THREE_NUM);

	public static final char ONE = '1';
	public static final char ZERO = '0';

	public static final String ZERO_STRING = "0";
	public static final String ONE_STRING = "1";
	public static final String TWO_STRING = "2";
	public static final String THREE_STRING = "3";

	public static final String YES = "是";
	public static final String NO = "否";

	public static final String DISABLE_TEXT = "禁用";

	public static final String ENABLE_TEXT = "启用";

	public static final String MAKE_NOT_TEXT = "未处理";

	public static final String MAKE_GOING_TEXT = "处理中";

	public static final String MAKE_DONE_TEXT = "完成";

	public static final String SEND_BACK_TEXT = "交付客户";

	public static final String DEVICE_FORWARD = "device_forward";

	public static final String REQUEST_HEADER = "request_header";

	public static final String DEVICE_LIST = "device_list";

	/** PC端默认标识编号 **/
	public static final String PC_DEFAULT_CODE = "PC0000000001";

	/** 系统默认跳转标识 **/
	public static final String DEFAULT_FORWARD = "defaultForward";

	/** 订单支付状态 **/
	public static final String ORDER_PAYED_CODE = "5101";

	/** 未支付 **/
	public static final String ORDER_PAYED_NO = "0001";

	/** 已支付 **/
	public static final String ORDER_PAYED_YES = "0002";

	public static final String PAYED_TEXT = "已支付";

	public static final String NOTPAY_TEXT = "未支付";

	/** 客户编码 **/
	public static final String CUSTOMTYPE_CUSTOMER_CODE = "0001";

	/** 供应商编码 **/
	public static final String CUSTOMTYPE_SUPPLIER_CODE = "0002";

	/** 系统登录标识 **/
	public static final String SYS_LOGINED = "sysLogined";

	/** 系统用户 **/
	public static final String SYS_USER = "sysUser";

	public static final String SYS_MENU = "sysMenu";

	public static final String MAIN_VIEW = "mainView";

	/** 系统用户分类 **/
	public static final String SYSTEM_USER_TYPE_CODE = "1101";

	/** 功能菜单类型 **/
	public static final String SYSTEM_MENU_TYPE_CODE = "1102";

	/** 平台账号 **/
	public static final String SYSTEM_USER_TYPE_P = "0001";
	/** 供应商账号 **/
	public static final String SYSTEM_USER_TYPE_S = "0002";
	/** 客户账号 **/
	public static final String SYSTEM_USER_TYPE_C = "0003";

	/** 供应商等级 **/
	public static final String SUPPLIER_STEP_CODE = "2101";

	/** 供应商接口类型 **/
	public static final String SUPPLIER_INTERFACE_CODE = "2102";

	/** 供应商接口类型:HTTP **/
	public static final String SUPPLIER_INTERFACE_HTTP = "0001";

	/** 供应商接口类型:WEBSERVICE **/
	public static final String SUPPLIER_INTERFACE_WEBSERVICE = "0002";

	/** 供应商接口类型:RSS **/
	public static final String SUPPLIER_INTERFACE_RSS = "0003";

	/** 商品类别 **/
	public static final String PRODUCT_TYPE_CODE = "2201";

	/** 资源类别：图书 **/
	public static final String PRODUCT_TYPE_B = "0001";

	/** 资源类别：报纸 **/
	public static final String PRODUCT_TYPE_P = "0002";

	/** 资源类别：资讯新闻 **/
	public static final String PRODUCT_TYPE_N = "0003";

	/** 普通资源 **/
	public static final String RESOURCES_COMMON_TYPE = "0001";

	/** 特殊商品类别 **/
	public static final String PRODUCT_SPECIAL_CODE = "2202";

	/** 推荐图书 **/
	public static final String PRODUCT_SPECIAL_REPORT = "0001";

	/** 最新图书 **/
	public static final String PRODUCT_SPECIAL_NEW = "0002";

	/** 推荐商品:上周推荐 **/
	public static final String PRODUCT_SPECIAL_REPORT_LAST = "0003";

	/** 推荐资讯 **/
	public static final String PRODUCT_SPECIAL_REPORT_NEWS = "3001";

	/** 设备类型编码 **/
	public static final String DEVICE_TYPE_CODE = "3101";

	/** 软件版本编码 **/
	public static final String SOFT_VERSION_CODE = "3102";

	/** 性别 **/
	public static final String SEX_CODE = "3201";

	/** 婚姻状况 **/
	public static final String MARRIAGE_CODE = "3202";

	/** 是否加密 **/
	public static final String ENCRPT_CODE = "3203";

	/** 是否加密：是 **/
	public static final String ENCRPT_CODE_YES = "0001";

	/** 是否加密：否 **/
	public static final String ENCRPT_CODE_NO = "0002";

	/** 定制分类 **/
	public static final String CUSTOM_TYPE_CODE = "4101";

	/** 定制分类 :客户 **/
	public static final String CUSTOM_TYPE_C = "0001";

	/** 定制分类 :供应商 **/
	public static final String CUSTOM_TYPE_S = "0002";

	/** 模板格式 **/
	public static final String TEMPLATE_TYPE_CODE = "4102";

	/** 模板格式 **/
	public static final String RESPONSE_TEMPLATE_PATH = "/client/template/reader/responseResult.xsl";

	/** 搜索分类 :书名，作者，关键字 **/
	public static final String SEARCH_KEY_NANM = "1";
	public static final String SEARCH_KEY_AUTHOR = "2";
	public static final String SEARCH_KEY_CONTENT = "3";

	// 用于不受权限控制的访问
	public static final String EXT = "Ext";

	// 成功字符串标识
	public static final String SUCCESS_SAVE_MESSAGE = "SUCCESS_SAVE_MESSAGE";

	/** 注册协议 **/
	public static final String REGIST_PROTOCOL = "1001";
	/** 版权声明 **/
	public static final String COPYRIGHT_DECLARE = "1002";
	/** 网站关键字 **/
	public static final String WEB_SITE_KEYWORDS = "1003";
	/** 网站描述 **/
	public static final String WEB_SITE_DESCRIPTION = "1004";
	/** 网站名称 **/
	public static final String WEB_SITE_TITLE = "1005";

	/** 服务周期 **/
	public static final String SERVICE_CYCLE_CODE = "6101";
	/** 每天 **/
	public static final String SERVICE_CYCLE_DAY = "0001";
	/** 每周 **/
	public static final String SERVICE_CYCLE_WEEK = "0002";
	/** 每月 **/
	public static final String SERVICE_CYCLE_MONTH = "0003";

	/** 订阅调用等级 ：每级顺延半小时(+-)随机15分钟 **/
	public static final String SUBSCRIBE_STEP_1 = "0001";
	public static final String SUBSCRIBE_STEP_2 = "0002";
	public static final String SUBSCRIBE_STEP_3 = "0003";

	/** 封面上传路径 **/
	public static final String COVER_PATH = "client/images/cover/";

	/** 广告上传路径 **/
	public static final String AD_PATH = "client/images/ad/";

	/** ckEditor上传路径 **/
	public static final String CKEDITOR_PATH = "client/images/ckeditor/";

	/** ckEditor上传路径 **/
	public static final String AVATAR_PATH = "client/images/avatar/";
	
	/** 普通上传路径 **/
	public static final String UPLOAD_PATH = "upload";

	/** 普通:报纸上传路径 **/
	public static final String UPLOAD_NEWSPAPER_PATH = "upload/newspaper";

	/** 普通下载路径 **/
	public static String DOWNLOAD_PATH = "download";

	/** 工程目录 **/
	public static String PROJECT_PATH = "";

	public static final String DEFAULT_EHCACHE = "defaultEhcache";

	/** 对接平台唯一标示 **/
	public static final String SUPPLIER_CODE_UNISTRONG = "0001";

	/** 服务接口编码 **/
	public static final String INTERFACES_CODE_USERS = "1000";
	public static final String INTERFACES_CODE_SALES = "1001";
	public static final String INTERFACES_CODE_RESOURCES = "1002";

	/** HTTP请求中的排序参数 **/
	public static final String SORT_BY_PARAM = "sortBy";
	public static final String ASC_DESC_PARAM = "ascDesc";

	/** 系统信息 **/
	public static final String SYSTEM_INFO_CODE = "1103";

	/** 广告位类型 **/
	public static final String SYSTEM_ADVERTISEMENT_CODE = "1104";

	/** 系统信息 软件下载 编码 **/
	public static final String SYSTEM_INFO_SOFTWARE_CODE = "1004";

	/** 系统信息 服务与支持 编码 **/
	public static final String SYSTEM_INFO_SUPPORT_CODE = "1003";

	/** 系统管理员 **/
	public static final String SYSTEM_ADMIN_CODE = "admin";

	/** 超市公告 **/
	public static final String SYSTEM_INFO_NOTE_CODE = "1005";
	/** 顶部公告 **/
	public static final String SYSTEM_INFO_NOTE_TILE_CODE = "1006";

	/** 购物车 **/
	public static final String SESSION_SHOP_CARD_CODE = "shopCard";

	public static final Map<String, String> getWeekMap() {
		Map<String, String> weekMap = new TreeMap<String, String>();
		weekMap.put("1", "周一");
		weekMap.put("2", "周二");
		weekMap.put("3", "周三");
		weekMap.put("4", "周四");
		weekMap.put("5", "周五");
		weekMap.put("6", "周六");
		weekMap.put("7", "周日");
		return weekMap;
	}
}
