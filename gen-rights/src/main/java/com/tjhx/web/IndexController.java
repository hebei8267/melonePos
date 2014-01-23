package com.tjhx.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController {
	@RequestMapping(value = "/index")
	public String index() {
		// 用户登录初始化
		return "security/login";
	}
}
