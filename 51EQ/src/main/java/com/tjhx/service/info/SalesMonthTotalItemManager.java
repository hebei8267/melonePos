package com.tjhx.service.info;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.SpringContextHolder;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.info.SalesDayTotalItemJpaDao;
import com.tjhx.dao.info.SalesDayTotalItemMyBatisDao;
import com.tjhx.dao.info.SalesMonthTotalItemJpaDao;
import com.tjhx.dao.info.SalesMonthTotalItemMyBatisDao;
import com.tjhx.entity.info.SalesDayTotalItem;
import com.tjhx.entity.info.SalesMonthTotalItem;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.struct.OrganizationManager;

@Service
@Transactional(readOnly = true)
public class SalesMonthTotalItemManager {
	@Resource
	private SalesDayTotalItemJpaDao salesDayTotalItemJpaDao;
	@Resource
	private SalesDayTotalItemMyBatisDao salesDayTotalItemMyBatisDao;
	@Resource
	private SalesDayTotalItemManager salesDayTotalItemManager;
	@Resource
	private SalesMonthTotalItemJpaDao salesMonthTotalItemJpaDao;
	@Resource
	private SalesMonthTotalItemMyBatisDao salesMonthTotalItemMyBatisDao;
	@Resource
	private OrganizationManager orgManager;

	/**
	 * 门店月销售计算
	 * 
	 * @throws ParseException
	 */
	@Transactional(readOnly = false)
	public void calOrgSalesMonthTotal() throws ParseException {
		// 取得门店日销售计算-重计算天数
		List<String> optDateList = salesDayTotalItemManager.calOptDate();
		// 取得门店月销售计算-重计算月数
		List<String> _optYMList = calOptYearMonth(optDateList);
		List<Organization> _orgList = orgManager.getSubOrganization();

		for (String _optYM : _optYMList) {
			// 删除指定年月的所有门店销售信息
			salesMonthTotalItemMyBatisDao.delSalesMonthTotalInfo(_optYM);

			for (Organization org : _orgList) {
				SalesDayTotalItem _param = new SalesDayTotalItem();

				String optY = DateUtils.transDateFormat(_optYM, "yyyyMM", "yyyy");
				String optM = DateUtils.transDateFormat(_optYM, "yyyyMM", "MM");

				_param.setOrgId(org.getId());
				_param.setOptDateY(optY);
				_param.setOptDateM(optM);

				// 取得合计实销数量（指定年/月/机构）
				List<SalesDayTotalItem> _monthSaleTotalList = salesDayTotalItemManager
						.getSumSalesMonthTotalList(_param);

				for (SalesDayTotalItem monthSaleTotal : _monthSaleTotalList) {
					SalesMonthTotalItem _salesMonthTotal = new SalesMonthTotalItem();

					// 机构编号
					_salesMonthTotal.setOrgId(org.getId());
					// 机构资金编号
					_salesMonthTotal.setBranchNo(org.getBwBranchNo());
					// 日期-年月
					_salesMonthTotal.setOptDateYM(optY + optM);
					// 日期-年
					_salesMonthTotal.setOptDateY(optY);
					// 类别编号
					_salesMonthTotal.setItemClsNo(monthSaleTotal.getItemClsNo());
					// 销售数量
					_salesMonthTotal.setSaleQty(monthSaleTotal.getSaleQty());
					// 销售金额
					_salesMonthTotal.setSaleAmt(monthSaleTotal.getSaleAmt());
					// 退货数量
					_salesMonthTotal.setRetQty(monthSaleTotal.getRetQty());
					// 退货金额
					_salesMonthTotal.setRetAmt(monthSaleTotal.getRetAmt());
					// 赠送数量
					_salesMonthTotal.setGiveQty(monthSaleTotal.getGiveQty());
					// 实销数量
					_salesMonthTotal.setSaleRqty(monthSaleTotal.getSaleRqty());
					// 实销金额
					_salesMonthTotal.setSaleRamt(monthSaleTotal.getSaleRamt());
					// 实销价格
					if (monthSaleTotal.getSaleRqty().compareTo(BigDecimal.ZERO) == 0) {
						_salesMonthTotal.setSalePrice(new BigDecimal(0));
					} else {
						_salesMonthTotal.setSalePrice(monthSaleTotal.getSaleRamt().divide(monthSaleTotal.getSaleRqty(),
								2, BigDecimal.ROUND_UP));
					}

					salesMonthTotalItemJpaDao.save(_salesMonthTotal);
				}
			}
		}
	}

	/**
	 * 取得门店月销售计算-重计算月数
	 * 
	 * @param optDateList
	 * @return
	 */
	private List<String> calOptYearMonth(List<String> optDateList) {
		List<String> _optYMList = new ArrayList<String>();

		for (String _optDate : optDateList) {
			String _optMonth = DateUtils.transDateFormat(_optDate, "yyyyMMdd", "yyyyMM");
			if (!_optYMList.contains(_optMonth)) {
				_optYMList.add(_optMonth);
			}
		}

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		int _month = sysConfig.getSalesMonthTotalItemMonths();
		if (_optYMList.size() > _month) {// 从计算月份
			_optYMList = _optYMList.subList(0, _month);
		}

		return _optYMList;
	}

}
