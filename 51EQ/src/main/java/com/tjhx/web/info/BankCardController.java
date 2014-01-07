package com.tjhx.web.info;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjhx.entity.info.Bank;
import com.tjhx.entity.info.BankCard;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.info.BankCardManager;
import com.tjhx.service.info.BankManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/bankCard")
public class BankCardController extends BaseController {
	@Resource
	private BankCardManager bankCardManager;
	@Resource
	private BankManager bankManager;
	@Resource
	private OrganizationManager orgManager;

	/**
	 * 取得银行卡信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String bankCardList_Action(Model model) {
		List<BankCard> bankCardList = bankCardManager.getAllBankCard();

		model.addAttribute("bankCardList", bankCardList);

		return "info/bankCardList";
	}

	private void initBankList(Model model) {
		List<Bank> _bankList = bankManager.getAllBank();

		Map<String, String> bankList = new LinkedHashMap<String, String>();
		bankList.put("", "");
		for (Bank _bank : _bankList) {
			bankList.put(_bank.getBankId(), _bank.getName());
		}

		model.addAttribute("bankList", bankList);
	}

	private void initOrgList(Model model) {
		List<Organization> _orgList = orgManager.getAllOrganization();

		Map<String, String> orgList = new LinkedHashMap<String, String>();
		orgList.put("", "");
		for (Organization _org : _orgList) {
			orgList.put(_org.getId(), _org.getName());
		}

		model.addAttribute("orgList", orgList);
	}

	/**
	 * 编辑银行卡信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}")
	public String editBankCard_Action(@PathVariable("id") Integer id, Model model) {

		BankCard bankCard = bankCardManager.getBankCardByUuid(id);
		if (null == bankCard) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/bankCard/list";
		} else {
			model.addAttribute("bankCard", bankCard);

			initBankList(model);
			initOrgList(model);

			return "info/bankCardForm";
		}

	}

	/**
	 * 删除银行卡信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delBankCard_Action(@RequestParam("uuids") String ids, Model model) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			bankCardManager.delBankCardByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/bankCard/list";
	}

	/**
	 * 新增银行卡初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initBankCard_Action(Model model) {

		BankCard bankCard = new BankCard();
		model.addAttribute("bankCard", bankCard);

		initBankList(model);
		initOrgList(model);

		return "info/bankCardForm";
	}

	/**
	 * 新增/修改 银行卡信息
	 * 
	 * @param bankCard
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String saveBankCard_Action(@ModelAttribute("bankCard") BankCard bankCard, Model model)
			throws IllegalAccessException, InvocationTargetException {

		if (null == bankCard.getUuid()) {// 新增操作
			try {
				bankCardManager.addNewBankCard(bankCard);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				initBankList(model);
				initOrgList(model);

				return "info/bankCardForm";
			}
		} else {// 修改操作
			try {
				bankCardManager.updateBankCard(bankCard);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				initBankList(model);
				initOrgList(model);

				return "info/bankCardForm";
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/bankCard/list";
	}
}