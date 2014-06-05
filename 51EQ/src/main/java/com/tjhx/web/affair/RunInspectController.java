/**
 * 
 */
package com.tjhx.web.affair;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.web.BaseController;

/**
 * 门店巡查报告(运营)
 */
@Controller
@RequestMapping(value = "/runInspect")
public class RunInspectController extends BaseController {
	/**
	 * 取得门店巡查报告(运营)信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String list_Action(Model model) {

		return "affair/runInspectList";
	}

	@RequestMapping(value = "new")
	public String new_init_Action(Model model) {
		return "affair/runInspectForm";
	}
}
