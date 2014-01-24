package com.tjhx.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.globals.Constants;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/userSecurity")
public class UserSecurityController extends BaseController {

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
//		String loginName = ServletRequestUtils.getStringParameter(request, "loginName");
//		String passWord = ServletRequestUtils.getStringParameter(request, "passWord");
//
//		User user = userManager.findByLoginName(loginName);
//
//		// 校验用户信息
//		if (checkUserInfo(user, loginName, passWord)) {
//			saveUserInfo(session, user);
//
//			if (user.getFirstLoginFlg()) {// 第一次登录,修改默认密码
//				return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/member/initModPwd";
//			} else {
//				return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/member/myspace";
//			}
//		} else {
//			addInfoMsg(model, "ERR_MSG_LOGIN_001");
//			return null;
//		}
		
		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/mainFrame";
	}
}
