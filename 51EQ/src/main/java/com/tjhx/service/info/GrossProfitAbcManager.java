package com.tjhx.service.info;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.info.SalesDayTotalGoodsMyBatisDao;
import com.tjhx.entity.info.SalesDayTotalGoods;
import com.tjhx.vo.GrossProfitAbcVo;

@Service
@Transactional(readOnly = true)
public class GrossProfitAbcManager {
	@Resource
	private SalesDayTotalGoodsMyBatisDao salesDayTotalGoodsMyBatisDao;

	/**
	 * 合计销售金额
	 * 
	 * @throws ParseException
	 */
	private GrossProfitAbcVo getPosAmtInfo(String startDate, String endDate, String orgId, String itemSubno, String itemName,
			String itemType, BigDecimal abcParam1, BigDecimal abcParam2, String supplierBwIdArray) throws ParseException {
		Map<String, String> param = Maps.newHashMap();
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		if (StringUtils.isNotBlank(orgId)) {
			param.put("orgId", orgId);
		}
		if (StringUtils.isNotBlank(itemSubno)) {
			param.put("itemSubno", itemSubno);
		}
		if (StringUtils.isNotBlank(itemName)) {
			param.put("itemName", "%" + itemName + "%");
		}
		if (StringUtils.isNotBlank(itemType)) {
			param.put("itemType", itemType);
		}
		if (StringUtils.isNotBlank(supplierBwIdArray)) {
			param.put("supplier", supplierBwIdArray);
		}

		GrossProfitAbcVo vo = new GrossProfitAbcVo();
		SalesDayTotalGoods totalAmt = salesDayTotalGoodsMyBatisDao.getTotalPosAmt(param);
		if (null == totalAmt) {
			return vo;
		}

		vo.setTotalAmt(totalAmt.getPosAmt());
		SalesDayTotalGoods totalQty = salesDayTotalGoodsMyBatisDao.getTotalPosQty(param);
		vo.setTotalQty(totalQty.getPosQty());

		List<SalesDayTotalGoods> _totalList = salesDayTotalGoodsMyBatisDao.getTotalPosAmtInfo(param);

		BigDecimal total_1 = totalAmt.getPosAmt().multiply(abcParam1);
		BigDecimal total_2 = totalAmt.getPosAmt().multiply(abcParam1.add(abcParam2));

		List<SalesDayTotalGoods> _list1 = vo.getListA();
		List<SalesDayTotalGoods> _list2 = vo.getListB();
		List<SalesDayTotalGoods> _list3 = vo.getListC();

		// 时间间隔（单位：天）
		long _spanDay = DateUtils.getDateSpanDay(startDate, endDate) + 1;
		BigDecimal _tmp_total = new BigDecimal("0");
		for (SalesDayTotalGoods _s : _totalList) {
			_s.setSpanDay(_spanDay);

			_tmp_total = _tmp_total.add(_s.getPosAmt());

			if (_tmp_total.compareTo(total_1) == -1) {
				_list1.add(_s);

				vo.setTotalAAmt(vo.getTotalAAmt().add(_s.getPosAmt()));
				vo.setTotalAQty(vo.getTotalAQty().add(_s.getPosQty()));
				vo.setTotalAStockAmt(vo.getTotalAStockAmt().add(_s.getStockAmt()));
			} else if (_tmp_total.compareTo(total_2) == -1) {
				_list2.add(_s);

				vo.setTotalBAmt(vo.getTotalBAmt().add(_s.getPosAmt()));
				vo.setTotalBQty(vo.getTotalBQty().add(_s.getPosQty()));
				vo.setTotalBStockAmt(vo.getTotalBStockAmt().add(_s.getStockAmt()));
			} else {
				_list3.add(_s);

				vo.setTotalCAmt(vo.getTotalCAmt().add(_s.getPosAmt()));
				vo.setTotalCQty(vo.getTotalCQty().add(_s.getPosQty()));
				vo.setTotalCStockAmt(vo.getTotalCStockAmt().add(_s.getStockAmt()));
			}
		}

		return vo;
	}

	public GrossProfitAbcVo getGrossProfitAbcInfo(String optDateShow_start, String optDateShow_end, String orgId, String itemType,
			String itemSubno, String itemName, String abcType, int abcParam1, int abcParam2, int abcParam3, String supplierBwIdArray)
			throws ParseException {
		String startDate = DateUtils.transDateFormat(optDateShow_start, "yyyy-MM-dd", "yyyyMMdd");
		String endDate = DateUtils.transDateFormat(optDateShow_end, "yyyy-MM-dd", "yyyyMMdd");

		if ("1".equals(abcType)) {// 合计销售金额
			return getPosAmtInfo(startDate, endDate, orgId, itemSubno, itemName, itemType,
					new BigDecimal(abcParam1).divide(new BigDecimal("100")), new BigDecimal(abcParam2).divide(new BigDecimal("100")),
					supplierBwIdArray);
		} else if ("2".equals(abcType)) {// 合计销售数量
			return getPosNumInfo(startDate, endDate, orgId, itemSubno, itemName, itemType,
					new BigDecimal(abcParam1).divide(new BigDecimal("100")), new BigDecimal(abcParam2).divide(new BigDecimal("100")),
					supplierBwIdArray);
		} else {// 合计销售毛利

		}
		return null;
	}

	/**
	 * 合计销售数量
	 * 
	 * @throws ParseException
	 */
	private GrossProfitAbcVo getPosNumInfo(String startDate, String endDate, String orgId, String itemSubno, String itemName,
			String itemType, BigDecimal abcParam1, BigDecimal abcParam2, String supplierBwIdArray) throws ParseException {
		Map<String, String> param = Maps.newHashMap();
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		if (StringUtils.isNotBlank(orgId)) {
			param.put("orgId", orgId);
		}
		if (StringUtils.isNotBlank(itemSubno)) {
			param.put("itemSubno", itemSubno);
		}
		if (StringUtils.isNotBlank(itemName)) {
			param.put("itemName", "%" + itemName + "%");
		}
		if (StringUtils.isNotBlank(itemType)) {
			param.put("itemType", itemType);
		}
		if (StringUtils.isNotBlank(supplierBwIdArray)) {
			param.put("supplier", supplierBwIdArray);
		}

		GrossProfitAbcVo vo = new GrossProfitAbcVo();
		SalesDayTotalGoods totalQty = salesDayTotalGoodsMyBatisDao.getTotalPosQty(param);
		if (null == totalQty) {
			return vo;
		}
		vo.setTotalQty(totalQty.getPosQty());
		SalesDayTotalGoods totalAmt = salesDayTotalGoodsMyBatisDao.getTotalPosAmt(param);
		vo.setTotalAmt(totalAmt.getPosAmt());

		List<SalesDayTotalGoods> _totalList = salesDayTotalGoodsMyBatisDao.getTotalPosQtyInfo(param);

		BigDecimal total_1 = totalQty.getPosQty().multiply(abcParam1);
		BigDecimal total_2 = totalQty.getPosQty().multiply(abcParam1.add(abcParam2));

		List<SalesDayTotalGoods> _list1 = vo.getListA();
		List<SalesDayTotalGoods> _list2 = vo.getListB();
		List<SalesDayTotalGoods> _list3 = vo.getListC();

		// 时间间隔（单位：天）
		long _spanDay = DateUtils.getDateSpanDay(startDate, endDate) + 1;
		BigDecimal _tmp_total = new BigDecimal("0");
		for (SalesDayTotalGoods _s : _totalList) {
			_s.setSpanDay(_spanDay);

			_tmp_total = _tmp_total.add(_s.getPosQty());

			if (_tmp_total.compareTo(total_1) == -1) {
				_list1.add(_s);

				vo.setTotalAAmt(vo.getTotalAAmt().add(_s.getPosAmt()));
				vo.setTotalAQty(vo.getTotalAQty().add(_s.getPosQty()));
				vo.setTotalAStockAmt(vo.getTotalAStockAmt().add(_s.getStockAmt()));
			} else if (_tmp_total.compareTo(total_2) == -1) {
				_list2.add(_s);

				vo.setTotalBAmt(vo.getTotalBAmt().add(_s.getPosAmt()));
				vo.setTotalBQty(vo.getTotalBQty().add(_s.getPosQty()));
				vo.setTotalBStockAmt(vo.getTotalBStockAmt().add(_s.getStockAmt()));

			} else {
				_list3.add(_s);

				vo.setTotalCAmt(vo.getTotalCAmt().add(_s.getPosAmt()));
				vo.setTotalCQty(vo.getTotalCQty().add(_s.getPosQty()));
				vo.setTotalCStockAmt(vo.getTotalCStockAmt().add(_s.getStockAmt()));
			}
		}

		return vo;
	}

	/**
	 * 取得销售门店信息（根据条码、销售时间段）
	 * 
	 * @param itemSubno
	 * @param optDateShowStart
	 * @param optDateShowEnd
	 * @return
	 */
	public List<SalesDayTotalGoods> getSaleOrgInfo(String itemSubno, String optDateShowStart, String optDateShowEnd) {
		String startDate = DateUtils.transDateFormat(optDateShowStart, "yyyy-MM-dd", "yyyyMMdd");
		String endDate = DateUtils.transDateFormat(optDateShowEnd, "yyyy-MM-dd", "yyyyMMdd");

		Map<String, String> param = Maps.newHashMap();
		param.put("itemSubno", itemSubno);
		param.put("startDate", startDate);
		param.put("endDate", endDate);

		return salesDayTotalGoodsMyBatisDao.getSaleOrgInfo(param);
	}

	/**
	 * 取得库存门店信息（根据条码）
	 * 
	 * @param itemSubno
	 * @return
	 */
	public List<SalesDayTotalGoods> getStoreOrgInfo(String itemSubno) {
		return salesDayTotalGoodsMyBatisDao.getStoreOrgInfo(itemSubno);
	}
}
