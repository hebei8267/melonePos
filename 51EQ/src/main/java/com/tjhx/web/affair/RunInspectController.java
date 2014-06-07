/**
 * 
 */
package com.tjhx.web.affair;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;
import com.tjhx.web.report.ReportUtils;

/**
 * 门店巡查报告(运营)
 */
@Controller
@RequestMapping(value = "/runInspect")
public class RunInspectController extends BaseController {
	@Resource
	private OrganizationManager orgManager;

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

		ReportUtils.initOrgList_Null_NoNRoot(orgManager, model);

		return "affair/runInspectForm";
	}
}
