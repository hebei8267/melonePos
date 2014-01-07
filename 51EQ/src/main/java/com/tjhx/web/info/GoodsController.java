package com.tjhx.web.info;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.globals.Constants;
import com.tjhx.service.info.GoodsManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/goods")
public class GoodsController extends BaseController {
	@Resource
	private GoodsManager goodsManager;

	/**
	 * 空白商品种类信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String goodsList_Action(Model model, HttpServletRequest request) {
		long goodsCount = goodsManager.getGoodsCount();
		model.addAttribute("goodsCount", goodsCount);
		return "info/goodsList";
	}

	/**
	 * 同步百威商品种类信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "bwDataSynBtn")
	public String bwDataSyn_Action(Model model) {
		// 同步百威商品信息
		goodsManager.bwDataSyn();

		addInfoMsg(model, "TIP_MSG_GOODS_001");

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/goods/list";
	}

}
