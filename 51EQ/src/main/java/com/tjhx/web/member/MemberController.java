package com.tjhx.web.member;

import java.util.List;

import javacryption.aes.AesCtr;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.common.utils.Encrypter;
import com.tjhx.entity.affair.MsgInfo;
import com.tjhx.entity.member.User;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.affair.MsgInfoManager;
import com.tjhx.service.member.UserManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/member")
public class MemberController extends BaseController {
	@Resource
	private UserManager userManager;
	@Resource
	private MsgInfoManager msgInfoManager;

	/**
	 * 用户退出
	 * 
	 * @return
	 */
	@RequestMapping(value = "logout")
	public String userLogout_Action(HttpSession session) {
		session.invalidate();
		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/index";
	}

	/**
	 * 用户登录
	 * 
	 * @param loginName 用户名
	 * @param passWord 密码
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "login")
	public String userLogin_Action(HttpServletRequest request, Model model, HttpSession session)
			throws ServletRequestBindingException {
		String loginName = ServletRequestUtils.getStringParameter(request, "_loginName");
		String passWord = ServletRequestUtils.getStringParameter(request, "_passWord");

		String key = (String) session.getAttribute(Constants.SESSION_ENCRYPT_KEY);

		loginName = AesCtr.decrypt(loginName, key, 256);
		passWord = AesCtr.decrypt(passWord, key, 256);

		User user = userManager.findByLoginName(loginName);

		// 校验用户信息
		if (checkUserInfo(user, loginName, passWord)) {
			saveUserInfo(session, user);

			if (user.getFirstLoginFlg()) {// 第一次登录,修改默认密码
				return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/member/initModPwd";
			} else {
				return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/member/myspace";
			}
		} else {
			addInfoMsg(model, "ERR_MSG_LOGIN_001");
			return null;
		}
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "modPwd")
	public String modPwd_Action(HttpServletRequest request, Model model, HttpSession session)
			throws ServletRequestBindingException {
		Integer userUuid = ServletRequestUtils.getIntParameter(request, "uuid");
		String oldPassWord = ServletRequestUtils.getStringParameter(request, "_oldPassWord");
		String newPassWord = ServletRequestUtils.getStringParameter(request, "_newPassWord");

		String key = (String) session.getAttribute(Constants.SESSION_ENCRYPT_KEY);
		oldPassWord = AesCtr.decrypt(oldPassWord, key, 256);
		newPassWord = AesCtr.decrypt(newPassWord, key, 256);

		try {
			User user = userManager.modUserPwd(userUuid, oldPassWord, newPassWord);

			saveUserInfo(session, user);
		} catch (ServiceException ex) {
			// 添加错误消息
			addInfoMsg(model, ex.getMessage());

			return "member/modPwd";
		}
		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/member/myspace";
	}

	/**
	 * 修改密码初始化
	 * 
	 * @return
	 */
	@RequestMapping(value = "initModPwd")
	public String initModPwd_Action(Model model, HttpSession session) {
		String key = (String) session.getAttribute(Constants.SESSION_ENCRYPT_KEY);

		model.addAttribute("_encrypt_key", key);

		return "member/modPwd";
	}

	/**
	 * 校验用户信息
	 * 
	 * @param user 用户信息
	 * @param loginName 用户名
	 * @param passWord 密码
	 * @return
	 */
	private boolean checkUserInfo(User user, String loginName, String passWord) {
		if (null != user) {
			return user.getLoginName().equals(loginName) && Encrypter.decrypt(user.getPassWord()).equals(passWord)
					&& user.isValid();
		}
		return false;
	}

	/**
	 * 我的空间
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "myspace")
	public String myspace_Action(Model model, HttpSession session) {
		User user = getUserInfo(session);

		// 取得 公告/消息 信息列表（根据用户编号取得### 未读状态+已读状态>=4）
		List<MsgInfo> _msgInfoList = msgInfoManager.getDefaultMsgInfoList(user.getLoginName());
		model.addAttribute("msgInfoList", _msgInfoList);

		return "member/myspace";
	}

}