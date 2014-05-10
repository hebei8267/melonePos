/**
 * 
 */
package com.tjhx.web.order;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjhx.entity.order.Coupon;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.order.CouponManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;

/**
 * 代金卷
 */
@Controller
@RequestMapping(value = "/coupon")
public class CouponController extends BaseController {
	@Resource
	private CouponManager couponManager;
	@Resource
	private OrganizationManager orgnManager;

	/**
	 * 取得代金卷信息列表
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = { "list", "" })
	public String couponList_Action(Model model, HttpSession session) throws ParseException {
		List<Coupon> _list = couponManager.getAllCouponList();
		model.addAttribute("couponList", _list);
		return "order/couponList";
	}

	/**
	 * 新增代金卷初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initCoupon_Action(Model model) {

		Coupon coupon = new Coupon();
		model.addAttribute("coupon", coupon);

		List<Organization> _orgList = orgnManager.getSubOrganization();
		model.addAttribute("allOrgList", _orgList);

		return "order/couponForm";
	}

	/**
	 * 删除代金卷信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delCoupon_Action(@RequestParam("uuids") String ids, Model model) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			couponManager.delCouponByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/coupon/list";
	}

	/**
	 * 编辑代金卷信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{couponNo}")
	public String editUser_Action(@PathVariable("couponNo") String couponNo, Model model) {

		List<Coupon> _cList = couponManager.findByCouponNo(couponNo);
		if (null == _cList || _cList.size() == 0) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/coupon/list";
		} else {
			model.addAttribute("coupon", _cList.get(0));

			// 初始化备选/已选机构列表
			init_allOrgList_appOrgList(_cList, model);

			return "order/couponForm";
		}
	}

	/**
	 * 初始化备选/已选机构列表
	 * 
	 * @param _cList
	 * @param model
	 */
	private void init_allOrgList_appOrgList(List<Coupon> couponList, Model model) {
		List<Organization> _appOrgList = new ArrayList<Organization>();
		for (Coupon coupon : couponList) {
			Organization _org = orgnManager.getOrganizationByOrgIdInCache(coupon.getOrgId());
			if (null != _org) {
				_appOrgList.add(_org);
			}
		}
		model.addAttribute("appOrgList", _appOrgList);

		List<Organization> _orgList = orgnManager.getSubOrganization();
		_orgList.removeAll(_appOrgList);
		model.addAttribute("allOrgList", _orgList);
	}

	/**
	 * 新增/修改 User信息
	 * 
	 * @param user
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String saveUser_Action(@ModelAttribute("coupon") Coupon coupon, Model model) throws IllegalAccessException,
			InvocationTargetException {

		if (null == coupon.getUuid()) {
			// 新增操作
			try {
				couponManager.addNewCoupon(coupon);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				List<Coupon> _cList = couponManager.findByCouponNo(coupon.getCouponNo());
				// 初始化备选/已选机构列表
				init_allOrgList_appOrgList(_cList, model);

				return "order/couponForm";
			}
		} else {// 修改操作
			try {
				couponManager.updateCoupon(coupon);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				List<Coupon> _cList = couponManager.findByCouponNo(coupon.getCouponNo());
				// 初始化备选/已选机构列表
				init_allOrgList_appOrgList(_cList, model);

				return "order/couponForm";
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/coupon/list";
	}
}
