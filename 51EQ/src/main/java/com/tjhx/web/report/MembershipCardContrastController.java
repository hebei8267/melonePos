/**
 * 
 */
package com.tjhx.web.report;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.entity.bw.MembershipCard;
import com.tjhx.service.info.MembershipCardContrastManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;

/**
 * 会员卡信息对比
 */
@Controller
@RequestMapping(value = "/membershipCardContrast")
public class MembershipCardContrastController extends BaseController {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private MembershipCardContrastManager membershipCardContrastManager;

	@RequestMapping(value = "init")
	public String init_Action(Model model) {
		// 初始化页面下拉菜单控件
		initPageControls(model);

		return "report/membershipCardContrast";
	}

	/**
	 * 初始化页面下拉菜单控件
	 * 
	 * @param model
	 */
	private void initPageControls(Model model) {
		// 初始化机构下拉菜单
		ReportUtils.initOrgList_All_NonRoot(orgManager, model);

	}

	@RequestMapping(value = "search")
	public String search_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException, ParseException {
		String optDate2_start = ServletRequestUtils.getStringParameter(request, "optDate2_start");
		String optDate2_end = ServletRequestUtils.getStringParameter(request, "optDate2_end");
		String optDate1_end = ServletRequestUtils.getStringParameter(request, "optDate1_end");
		String optDate1_start = ServletRequestUtils.getStringParameter(request, "optDate1_start");
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");

		model.addAttribute("optDate2_start", optDate2_start);
		model.addAttribute("optDate2_end", optDate2_end);
		model.addAttribute("optDate1_end", optDate1_end);
		model.addAttribute("optDate1_start", optDate1_start);
		model.addAttribute("orgId", orgId);

		// 初始化页面下拉菜单控件
		initPageControls(model);

		List<MembershipCard> _list = membershipCardContrastManager.getContrastList(optDate1_start, optDate1_end, optDate2_start,
				optDate2_end, orgId);

		model.addAttribute("membershipCardList", _list);

		// 取得金卡余额
		List<MembershipCard> balanceList = membershipCardContrastManager.getMembershipCardBalanceInfo();

		for (MembershipCard membershipCard : balanceList) {
			if ("01".equals(membershipCard.getCardType())) {
				model.addAttribute("infancyBalance", membershipCard.getBalance());
			} else if ("03".equals(membershipCard.getCardType())) {
				model.addAttribute("eqBalance", membershipCard.getBalance());
			}
		}

		return "report/membershipCardContrast";
	}
}
