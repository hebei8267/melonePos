package com.tjhx.web;

public class BaseController {
//	@Resource
//	private FunctionManager functionManager;
//
//	/** 提示消息 */
//	private List<String> tipMsgList;
//	/** 警告消息 */
//	private List<String> warnMsgList;
//	/** 错误消息 */
//	private List<String> errMsgList;
//	/** 表单对象 */
//	private Model _model;
//
//	/**
//	 * 添加提示消息
//	 * 
//	 * @param model 页面模型对象
//	 * @param msg 消息内容
//	 */
//	public void addTipMsgStr(Model model, String msg) {
//		if (null == tipMsgList) {
//			tipMsgList = new ArrayList<String>();
//		}
//		tipMsgList.add(msg);
//		this._model = model;
//	}
//
//	/**
//	 * 添加警告消息
//	 * 
//	 * @param model 页面模型对象
//	 * @param msg 消息内容
//	 */
//	public void addWarnMsgStr(Model model, String msg) {
//		if (null == warnMsgList) {
//			warnMsgList = new ArrayList<String>();
//		}
//		warnMsgList.add(msg);
//		this._model = model;
//	}
//
//	/**
//	 * 添加错误消息
//	 * 
//	 * @param model 页面模型对象
//	 * @param msg 消息内容
//	 */
//	public void addErrMsgStr(Model model, String msg) {
//		if (null == errMsgList) {
//			errMsgList = new ArrayList<String>();
//		}
//		errMsgList.add(msg);
//		this._model = model;
//	}
//
//	/**
//	 * 添加消息
//	 * 
//	 * @param model 页面模型对象
//	 * @param msgId 消息编号
//	 */
//	protected void addInfoMsg(Model model, String msgId) {
//		addInfoMsg(model, msgId, (String[]) null);
//	}
//
//	/**
//	 * 添加消息
//	 * 
//	 * @param model 页面模型对象
//	 * @param msgId 消息编号
//	 * @param msgParam 消息参数
//	 */
//	protected void addInfoMsg(Model model, String msgId, String... msgParam) {
//		if (msgId.startsWith(Constants.TIP_MSG_PREFIX)) {
//			if (null == tipMsgList) {
//				tipMsgList = new ArrayList<String>();
//			}
//			tipMsgList.add(getMsg(msgId, msgParam));
//		} else if (msgId.startsWith(Constants.WARN_MSG_PREFIX)) {
//			if (null == warnMsgList) {
//				warnMsgList = new ArrayList<String>();
//			}
//			warnMsgList.add(getMsg(msgId, msgParam));
//		} else if (msgId.startsWith(Constants.ERR_MSG_PREFIX)) {
//			if (null == errMsgList) {
//				errMsgList = new ArrayList<String>();
//			}
//			errMsgList.add(getMsg(msgId, msgParam));
//		}
//
//		this._model = model;
//	}
//
//	/**
//	 * 取得消息内容
//	 * 
//	 * @param msgId 消息编号
//	 * @param msgParam 消息参数
//	 * @return 消息内容
//	 */
//	private String getMsg(String msgId, String... msgParam) {
//		MessageSource mesResources = SpringContextHolder.getBean("messageSource");
//		return mesResources.getMessage(msgId, msgParam, Locale.CHINESE);
//	}
//
//	/**
//	 * 初始化消息列表
//	 */
//	public void initMsgList() {
//		tipMsgList = null;
//		warnMsgList = null;
//		errMsgList = null;
//	}
//
//	/**
//	 * 将消息保存至表单中
//	 */
//	public void insertMsgListToPageMode() {
//		if (null != _model) {
//			if (null != tipMsgList) {
//				_model.addAttribute(Constants.SESSION_TIP_MSG_LIST, tipMsgList);
//			}
//			if (null != warnMsgList) {
//				_model.addAttribute(Constants.SESSION_WARN_MSG_LIST, warnMsgList);
//			}
//			if (null != errMsgList) {
//				_model.addAttribute(Constants.SESSION_ERR_MSG_LIST, errMsgList);
//			}
//		}
//	}
//
//	protected User getUserInfo(HttpSession session) {
//		return (User) session.getAttribute(Constants.SESSION_USER_INFO);
//	}
//
//	protected void saveUserInfo(HttpSession session, User user) {
//		// 初始化机构信息
//		user.getOrgName();
//		// 初始化角色权限
//		user.getRole().initPermIdList(functionManager.getAllFunction());
//
//		session.setAttribute(Constants.SESSION_USER_INFO, user);
//	}
}
