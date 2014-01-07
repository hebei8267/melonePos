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
import org.springside.modules.mapper.JsonMapper;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.info.SalesDayTotalGoods;
import com.tjhx.service.info.SalesDayTotalGoodsManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/salesDayGoodsReport")
public class SalesDayGoodsReportController extends BaseController {
	@Resource
	private SalesDayTotalGoodsManager salesDayTotalGoodsManager;

	@RequestMapping(value = { "list", "" })
	public String salesDayGoodsReportList_Action(Model model) throws ServletRequestBindingException, ParseException {
		String _now = DateUtils.getCurFormatDate("yyyy-MM-dd");
		String optDateStart = DateUtils.getNextDateFormatDate(_now, -7, "yyyy-MM-dd");
		String optDateEnd = DateUtils.getNextDateFormatDate(_now, -1, "yyyy-MM-dd");

		model.addAttribute("optDateShow_start", optDateStart);
		model.addAttribute("optDateShow_end", optDateEnd);
		return "report/salesDayGoodsReport";
	}

	@RequestMapping(value = "search")
	public String salesDayGoodsReportSearch_Action(Model model, HttpServletRequest request)
			throws ServletRequestBindingException, ParseException {
		String optDateStart = ServletRequestUtils.getStringParameter(request, "optDateShow_start");
		String optDateEnd = ServletRequestUtils.getStringParameter(request, "optDateShow_end");
		String barcode = ServletRequestUtils.getStringParameter(request, "barcode");
		model.addAttribute("optDateShow_start", optDateStart);
		model.addAttribute("optDateShow_end", optDateEnd);
		model.addAttribute("barcode", barcode);

		SalesDayTotalGoods param = new SalesDayTotalGoods();
		param.setOptDateStart(DateUtils.transDateFormat(optDateStart, "yyyy-MM-dd", "yyyyMMdd"));
		param.setOptDateEnd(DateUtils.transDateFormat(optDateEnd, "yyyy-MM-dd", "yyyyMMdd"));
		param.setItemSubno(barcode);
		param.setItemBarcode(barcode);

		// 取得各店指定时间区间内的销售信息（按商品）
		List<SalesDayTotalGoods> _sumSaleList = salesDayTotalGoodsManager.getSumSaleInfoList(param);
		model.addAttribute("sumSaleList", _sumSaleList);

		JsonMapper mapper = new JsonMapper();
		model.addAttribute("saleRamtJson", mapper.toJson(_sumSaleList));
		model.addAttribute("showFlg", true);
		return "report/salesDayGoodsReport";
	}
}
