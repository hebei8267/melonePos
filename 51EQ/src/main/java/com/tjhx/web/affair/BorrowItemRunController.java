package com.tjhx.web.affair;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.affair.BorrowItem;
import com.tjhx.entity.affair.BorrowItemRun;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.affair.BorrowItemManager;
import com.tjhx.service.affair.BorrowItemRunManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/borrowItemRun")
public class BorrowItemRunController extends BaseController {
	@Resource
	private BorrowItemRunManager borrowItemRunManager;
	@Resource
	private BorrowItemManager borrowItemManager;

	/**
	 * 取得借还物件流水信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list/{itemNo}")
	public String borrowItemRunList_Action(Model model, @PathVariable("itemNo") String itemNo) {
		List<BorrowItemRun> borrowItemRunList = borrowItemRunManager.getBorrowItemRunListByItemNo(itemNo);
		model.addAttribute("borrowItemRunList", borrowItemRunList);

		BorrowItem borrowItem = borrowItemManager.findByItemNo(itemNo);
		model.addAttribute("borrowItem", borrowItem);

		return "affair/borrowItemRunList";
	}

	/**
	 * 删除借还物件流水信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del/{itemNo}")
	public String delBorrowItemRun_Action(@PathVariable("itemNo") String itemNo, @RequestParam("uuids") String ids, Model model,
			HttpServletRequest request) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			borrowItemRunManager.delBorrowItemRunByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/borrowItemRun/list/" + itemNo;
	}

	/**
	 * (借出)物件流水初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "lend/{itemNo}")
	public String initLendBorrowItemRun_Action(@PathVariable("itemNo") String itemNo, Model model, HttpServletRequest request) {
		BorrowItem borrowItem = borrowItemManager.findByItemNo(itemNo);
		if (null != borrowItem) {
			model.addAttribute("borrowItem", borrowItem);
		}

		// TODO 修改点

		BorrowItemRun borrowItemRun = new BorrowItemRun();
		model.addAttribute("borrowItemRun", borrowItemRun);

		return "affair/borrowItemRunLendForm";
	}

	/**
	 * (还入)物件流水初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "return/{itemNo}/{runUuid}")
	public String initReturnBorrowItemRun_Action(@PathVariable("itemNo") String itemNo, @PathVariable("runUuid") Integer runUuid,
			Model model, HttpServletRequest request) {
		BorrowItem borrowItem = borrowItemManager.findByItemNo(itemNo);
		if (null != borrowItem) {
			model.addAttribute("borrowItem", borrowItem);
		}

		// TODO 修改点

		BorrowItemRun borrowItemRun = borrowItemRunManager.getBorrowItemRunByUuid(runUuid);
		model.addAttribute("borrowItemRun", borrowItemRun);

		return "affair/borrowItemRunReturnForm";
	}

	/**
	 * 新增/修改 借还物件流水信息
	 * 
	 * @param borrowItemRun
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String saveBorrowItemRun_Action(@ModelAttribute("borrowItemRun") BorrowItemRun borrowItemRun, Model model,
			HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
		if (null != borrowItemRun && StringUtils.isNotBlank(borrowItemRun.getBorrowDate())) {
			borrowItemRun.setBorrowDate(DateUtils.transDateFormat(borrowItemRun.getBorrowDate(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if (null != borrowItemRun && StringUtils.isNotBlank(borrowItemRun.getExpReturnDate())) {
			borrowItemRun.setExpReturnDate(DateUtils.transDateFormat(borrowItemRun.getExpReturnDate(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if (null != borrowItemRun && StringUtils.isNotBlank(borrowItemRun.getActReturnDate())) {
			borrowItemRun.setActReturnDate(DateUtils.transDateFormat(borrowItemRun.getActReturnDate(), "yyyy-MM-dd", "yyyyMMdd"));
		}

		if (null == borrowItemRun.getUuid()) {// 新增操作
			borrowItemRunManager.addNewBorrowItemRun(borrowItemRun);
		} else {// 修改操作
			try {
				borrowItemRunManager.updateBorrowItemRun(borrowItemRun);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/borrowItemRun/list/" + borrowItemRun.getItemNo();
	}
}