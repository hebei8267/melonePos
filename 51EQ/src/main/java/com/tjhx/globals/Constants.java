package com.tjhx.globals;

public class Constants {
	public static final String SESSION_ENCRYPT_KEY_PAIR = "__SESSION_ENCRYPT_KEY_PAIR";
	public static final String SESSION_ENCRYPT_KEY = "__SESSION_ENCRYPT_KEY";

	// ------------------------------------------------------------------
	public static final int EDITABLE_DAY = 3;
	// ------------------------------------------------------------------
	/** 统一的charset定义 */
	public static final String CHARSET_UTF_8 = "UTF-8";

	// ------------------------------------------------------------------
	/** 错误消息前缀 */
	public static final String ERR_MSG_PREFIX = "ERR_MSG";
	/** 提示消息前缀 */
	public static final String TIP_MSG_PREFIX = "TIP_MSG";
	/** 警告消息前缀 */
	public static final String WARN_MSG_PREFIX = "WARN_MSG";

	/** JSP页面请求前缀 */
	public static final String PAGE_REQUEST_PREFIX = "sc";

	/** 提示消息 */
	public static final String SESSION_TIP_MSG_LIST = "__SESSION_TIP_MSG_LIST";
	/** 警告消息 */
	public static final String SESSION_WARN_MSG_LIST = "__SESSION_WARN_MSG_LIST";
	/** 错误消息 */
	public static final String SESSION_ERR_MSG_LIST = "__SESSION_ERR_MSG_LIST";

	/** 用户信息 */
	public static final String SESSION_USER_INFO = "__SESSION_USER_INFO";

	// ------------------------------------------------------------------
	/** 初始化密码-默认值 */
	public static final String DEFAULT_PWD = "123456";
	/** 总部机构编号-百威 */
	public static final String ROOT_ORG_BW_ID = "00D";
	/** 总部机构编号 */
	public static final String ROOT_ORG_ID = "000";
	/** 门店机构编号-02 */
	public static final String ORG_ID_02 = "00002D";
	/** 门店机构编号-13 */
	public static final String ORG_ID_13 = "00013D";
	
	/** 删除标识 */
	public static final String DEL_FLAG = "1";
	/** 空白选择项－显示文字 */
	public static final String BLANK_SELECT_TEXT = "---请选择---";
}
