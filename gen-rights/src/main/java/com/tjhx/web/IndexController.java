package com.tjhx.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.dao.security.MenuJpaDao;
import com.tjhx.entity.security.Menu;
import com.tjhx.service.security.MenuManager;

@Controller
public class IndexController extends BaseController {
	@Resource
	private MenuManager menuManager;

	@RequestMapping(value = "/index")
	public String index() {
		// 用户登录初始化
		return "security/login";
	}

	@RequestMapping(value = "/mainFrame")
	public String mainFrame(Model model) {
		// TODO
		List<Menu> _menuList = menuManager.getMenuTreeInfoByUser();
		model.addAttribute("_menuList", _menuList);
		// 主框架界面
		return "mainFrame";
	}
}
