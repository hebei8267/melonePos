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
import com.tjhx.dao.info.SalesDayTotalGoodsJpaDao;
import com.tjhx.dao.info.SalesDayTotalGoodsMyBatisDao;
import com.tjhx.daobw.DailySaleTotalGoodsMyBatisDao;
import com.tjhx.entity.bw.DailySaleTotalGoods;
import com.tjhx.entity.info.SalesDayTotalGoods;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.struct.OrganizationManager;

@Service
@Transactional(readOnly = true)
public class SalesDayTotalGoodsManager {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private SalesDayTotalGoodsMyBatisDao salesDayTotalGoodsMyBatisDao;
	@Resource
	private DailySaleTotalGoodsMyBatisDao dailySaleTotalGoodsMyBatisDao;
	@Resource
	private SalesDayTotalGoodsJpaDao salesDayTotalGoodsJpaDao;

	/**
	 * 取得百威系统各门店日销售信息
	 * 
	 * @throws ParseException
	 */
	@Transactional(readOnly = false)
	public void getOrgSalesDayTotal() throws ParseException {
		// 取得门店日销售计算-重计算天数
		List<String> optDateList = calOptDate();
		List<Organization> _orgList = orgManager.getSubOrganization();

		for (String optDate : optDateList) {
			// 删除指定日期的所有门店销售信息
			salesDayTotalGoodsMyBatisDao.delSalesDayTotalInfo(optDate);

			for (Organization org : _orgList) {
				DailySaleTotalGoods _param = new DailySaleTotalGoods();
				_param.setOptDate(optDate);
				_param.setBranchNo(org.getBwBranchNo());

				List<DailySaleTotalGoods> _dailySaleTotalList = dailySaleTotalGoodsMyBatisDao.getDailySaleTotalList(_param);

				for (DailySaleTotalGoods _dailySale : _dailySaleTotalList) {
					SalesDayTotalGoods _goods = new SalesDayTotalGoods();

					// 机构编号
					_goods.setOrgId(org.getId());
					// 机构资金编号
					_goods.setBranchNo(org.getBwBranchNo());
					// 日期
					_goods.setOptDate(optDate);
					String optDateY = DateUtils.transDateFormat(optDate, "yyyyMMdd", "yyyy");
					String optDateM = DateUtils.transDateFormat(optDate, "yyyyMMdd", "MM");
					// 日期-年
					_goods.setOptDateY(optDateY);
					// 日期-月
					_goods.setOptDateM(optDateM);
					// 短条码
					_goods.setItemSubno(_dailySale.getItemSubno());
					// 长条码
					_goods.setItemBarcode(_dailySale.getItemBarcode());
					// 销售数量
					_goods.setPosQty(_dailySale.getPosQty());
					// 销售金额
					_goods.setPosAmt(_dailySale.getPosAmt());

					salesDayTotalGoodsJpaDao.save(_goods);
				}
			}
		}
	}

	/**
	 * 取得门店日销售计算-重计算天数
	 * 
	 * @return
	 * @throws ParseException
	 */
	List<String> calOptDate() throws ParseException {
		List<String> _optDateList = new ArrayList<String>();

		String _now = DateUtils.getCurrentDateShortStr();
		_optDateList.add(_now);

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		for (int i = 1; i < sysConfig.getSalesDayTotalGoodsDays(); i++) {
			_optDateList.add(DateUtils.getNextDateFormatDate(_now, -i, "yyyyMMdd"));
		}

		return _optDateList;
	}

	/**
	 * 取得各店指定时间区间内的销售信息（按商品）
	 * 
	 * @param salesDayTotalGoods
	 * @return
	 * @throws ParseException
	 */
	public List<SalesDayTotalGoods> getSumSaleInfoList(SalesDayTotalGoods salesDayTotalGoods) throws ParseException {
		// 取得各店指定时间区间内的销售信息（按商品）
		List<SalesDayTotalGoods> _sumSaleList = salesDayTotalGoodsMyBatisDao.getSumSaleInfoList(salesDayTotalGoods);
		Long _span = DateUtils.getDateSpanDay(salesDayTotalGoods.getOptDateStart(), salesDayTotalGoods.getOptDateEnd(),
				"yyyyMMdd");
		if (0 == _span) {
			_span = (long) 1;
		}
		for (SalesDayTotalGoods _salesGoods : _sumSaleList) {
			// 实销价格
			if (_salesGoods.getPosQty().compareTo(BigDecimal.ZERO) == 0) {
				_salesGoods.setAverageDailySales(new BigDecimal(0));
			} else {
				_salesGoods.setAverageDailySales(_salesGoods.getPosQty().divide(new BigDecimal(_span), 2, BigDecimal.ROUND_UP));
			}
		}
		return _sumSaleList;
	}

	/**
	 * 取得指定店指定时间区间内销售信息排名(按类别)--按销量排序
	 * 
	 * @param optDateStart
	 * @param optDateEnd
	 * @param orgId
	 * @param itemNo
	 * @return
	 */
	public List<SalesDayTotalGoods> getSalesItemRankInfoList_OrderQty(String optDateStart, String optDateEnd, String orgId,
			String itemNo) {
		SalesDayTotalGoods param = new SalesDayTotalGoods();
		param.setOptDateStart(optDateStart);
		param.setOptDateEnd(optDateEnd);
		param.setOrgId(orgId);
		param.setItemNo(itemNo);

		return salesDayTotalGoodsMyBatisDao.getSalesItemRankInfoList_OrderQty(param);
	}

	/**
	 * 取得指定店指定时间区间内销售信息排名(按类别)--按销售额排序
	 * 
	 * @param optDateStart
	 * @param optDateEnd
	 * @param orgId
	 * @param itemNo
	 * @return
	 */
	public List<SalesDayTotalGoods> getSalesItemRankInfoList_OrderAmt(String optDateStart, String optDateEnd, String orgId,
			String itemNo) {
		SalesDayTotalGoods param = new SalesDayTotalGoods();
		param.setOptDateStart(optDateStart);
		param.setOptDateEnd(optDateEnd);
		param.setOrgId(orgId);
		param.setItemNo(itemNo);

		return salesDayTotalGoodsMyBatisDao.getSalesItemRankInfoList_OrderAmt(param);
	}
}
