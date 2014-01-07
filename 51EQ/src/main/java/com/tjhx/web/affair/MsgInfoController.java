package com.tjhx.web.affair;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.affair.MsgInfo;
import com.tjhx.entity.member.User;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.Constants;
import com.tjhx.service.affair.MsgInfoManager;
import com.tjhx.service.member.UserManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/msgInfo")
public class MsgInfoController extends BaseController {
	@Resource
	private OrganizationManager orgnManager;
	@Resource
	private UserManager userManager;
	@Resource
	private MsgInfoManager msgInfoManager;

	@RequestMapping(value = { "list", "" })
	public String msgInfoList_Action(Model model, HttpSession session) {
		User user = getUserInfo(session);

		// 收信
		model.addAttribute("msgType", 2);

		List<MsgInfo> _msgInfoList = msgInfoManager.getDefaultMsgInfoList(user.getLoginName());
		model.addAttribute("msgInfoList", _msgInfoList);

		initMsgTypeList(model);

		return "affair/msgInfoList";
	}

	/**
	 * 初始化信息类型下拉列表
	 * 
	 * @param model
	 */
	public static void initMsgTypeList(Model model) {
		Map<String, String> msgTypeList = new LinkedHashMap<String, String>();

		msgTypeList.put("", "所有");
		msgTypeList.put("1", "发信");
		msgTypeList.put("2", "收信");

		model.addAttribute("msgTypeList", msgTypeList);
	}

	/**
	 * 删除 公告/消息 信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delMsgInfo_Action(@RequestParam("uuids") String ids, Model model, HttpSession session) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			msgInfoManager.delMsgInfoByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/msgInfo/list";
	}

	/**
	 * 新增公告/消息初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initMsgInfo_Action(Model model, HttpSession session) {
		User user = getUserInfo(session);
		String orgId = user.getOrganization().getId();

		MsgInfo _msgInfo = new MsgInfo();
		_msgInfo.setOptDateShow(DateUtils.getCurFormatDate("yyyy-MM-dd"));
		_msgInfo.setWeek(DateUtils.getWeekOfDate(DateUtils.getCurrentDate()));
		_msgInfo.setSendUserName(user.getName());

		model.addAttribute("msgInfo", _msgInfo);

		if (Constants.ROOT_ORG_ID.equals(orgId)) {// 总部机构
			List<Organization> _orgList = orgnManager.getAllOrganization();
			model.addAttribute("orgList", _orgList);

			List<User> _userList = userManager.getAllUserByCache();
			model.addAttribute("userList", _userList);

			return "affair/msgInfoForm_Root";
		} else {// 门店机构

			List<User> _userList = userManager.getSelfOrgUserByCache(user.getOrganization().getUuid());
			model.addAttribute("userList", _userList);

			return "affair/msgInfoForm";
		}
	}

	/**
	 * 新增/修改 销售流水信息
	 * 
	 * @param cashRun
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String saveMsgInfo_Action(@ModelAttribute("msgInfo") MsgInfo msgInfo, Model model, HttpSession session)
			throws IllegalAccessException, InvocationTargetException {
		User user = getUserInfo(session);
		String orgId = user.getOrganization().getId();

		if (null == msgInfo.getUuid()) {// 新增操作

			if (!Constants.ROOT_ORG_ID.equals(orgId)) {// 门店机构用户
				msgInfo.setAcceptType("2");
			}

			msgInfoManager.addMsgInfo(msgInfo, user.getLoginName());
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/msgInfo/list";
	}

	/**
	 * @param model
	 * @param request
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "search")
	public String msgInfoSearch_Action(Model model, HttpServletRequest request, HttpSession session)
			throws ServletRequestBindingException {
		String msgType = ServletRequestUtils.getStringParameter(request, "msgType");
		String optDateShow_start = ServletRequestUtils.getStringParameter(request, "optDateShow_start");
		String optDateShow_end = ServletRequestUtils.getStringParameter(request, "optDateShow_end");
		model.addAttribute("msgType", msgType);
		model.addAttribute("optDateShow_start", optDateShow_start);
		model.addAttribute("optDateShow_end", optDateShow_end);

		User user = getUserInfo(session);

		MsgInfo msgInfo = new MsgInfo();
		msgInfo.setSendUserLoginName(user.getLoginName());
		msgInfo.setAcceptUserLoginName(user.getLoginName());

		if (StringUtils.isNotBlank(msgType)) {
			msgInfo.setMsgType(msgType);
		}
		if (StringUtils.isNotBlank(optDateShow_start)) {
			msgInfo.setOptDateStart(DateUtils.transDateFormat(optDateShow_start, "yyyy-MM-dd", "yyyyMMdd"));
		}
		if (StringUtils.isNotBlank(optDateShow_end)) {
			msgInfo.setOptDateEnd(DateUtils.transDateFormat(optDateShow_end, "yyyy-MM-dd", "yyyyMMdd"));
		}

		List<MsgInfo> _msgInfoList = msgInfoManager.getMsgInfoList(msgInfo);
		model.addAttribute("msgInfoList", _msgInfoList);

		initMsgTypeList(model);

		return "affair/msgInfoList";
	}

	@RequestMapping(value = "view/{msgUuid}")
	public String msgInfoView_Action(@PathVariable("msgUuid") int msgUuid, Model model) {
		MsgInfo _msgInfo = msgInfoManager.getMsgInfoByUuid(msgUuid);

		model.addAttribute("msgInfo", _msgInfo);
		return "affair/msgInfoView";
	}

	@RequestMapping(value = "read/{msgUuid}")
	public String msgInfoRead_Action(@PathVariable("msgUuid") int msgUuid, Model model) {
		msgInfoManager.updateMsgInfo_ReadFlg(msgUuid);
		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/msgInfo/list";
	}
}
