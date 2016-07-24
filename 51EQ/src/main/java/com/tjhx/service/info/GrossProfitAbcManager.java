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
			String itemType, BigDecimal abcParam1, BigDecimal abcParam2) throws ParseException {
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

		GrossProfitAbcVo vo = new GrossProfitAbcVo();
		SalesDayTotalGoods total = salesDayTotalGoodsMyBatisDao.getTotalPosAmt(param);
		if (null == total) {
			return vo;
		}

		List<SalesDayTotalGoods> _totalList = salesDayTotalGoodsMyBatisDao.getTotalPosAmtInfo(param);

		BigDecimal total_1 = total.getPosAmt().multiply(abcParam1);
		BigDecimal total_2 = total.getPosAmt().multiply(abcParam1.add(abcParam2));

		List<SalesDayTotalGoods> _list1 = vo.getListA();
		List<SalesDayTotalGoods> _list2 = vo.getListB();
		List<SalesDayTotalGoods> _list3 = vo.getListC();

		vo.setTotal(total.getPosAmt());

		// 时间间隔（单位：天）
		long _spanDay = DateUtils.getDateSpanDay(startDate, endDate) + 1;
		BigDecimal _tmp_total = new BigDecimal("0");
		for (SalesDayTotalGoods _s : _totalList) {
			_s.setSpanDay(_spanDay);

			_tmp_total = _tmp_total.add(_s.getPosAmt());

			if (_tmp_total.compareTo(total_1) == -1) {
				_list1.add(_s);

				vo.setTotalA(vo.getTotalA().add(_s.getPosAmt()));
			} else if (_tmp_total.compareTo(total_2) == -1) {
				_list2.add(_s);

				vo.setTotalB(vo.getTotalB().add(_s.getPosAmt()));
			} else {
				_list3.add(_s);

				vo.setTotalC(vo.getTotalC().add(_s.getPosAmt()));
			}
		}

		return vo;
	}

	public GrossProfitAbcVo getGrossProfitAbcInfo(String optDateShow_start, String optDateShow_end, String orgId, String itemType,
			String itemSubno, String itemName, String abcType, int abcParam1, int abcParam2, int abcParam3) throws ParseException {
		String startDate = DateUtils.transDateFormat(optDateShow_start, "yyyy-MM-dd", "yyyyMMdd");
		String endDate = DateUtils.transDateFormat(optDateShow_end, "yyyy-MM-dd", "yyyyMMdd");

		if ("1".equals(abcType)) {// 合计销售金额
			return getPosAmtInfo(startDate, endDate, orgId, itemSubno, itemName, itemType,
					new BigDecimal(abcParam1).divide(new BigDecimal("100")), new BigDecimal(abcParam2).divide(new BigDecimal("100")));
		} else if ("2".equals(abcType)) {// 合计销售数量
			return getPosNumInfo(startDate, endDate, orgId, itemSubno, itemName, itemType,
					new BigDecimal(abcParam1).divide(new BigDecimal("100")), new BigDecimal(abcParam2).divide(new BigDecimal("100")));
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
			String itemType, BigDecimal abcParam1, BigDecimal abcParam2) throws ParseException {
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

		GrossProfitAbcVo vo = new GrossProfitAbcVo();
		SalesDayTotalGoods total = salesDayTotalGoodsMyBatisDao.getTotalPosQty(param);
		if (null == total) {
			return vo;
		}

		List<SalesDayTotalGoods> _totalList = salesDayTotalGoodsMyBatisDao.getTotalPosQtyInfo(param);

		BigDecimal total_1 = total.getPosQty().multiply(abcParam1);
		BigDecimal total_2 = total.getPosQty().multiply(abcParam1.add(abcParam2));

		List<SalesDayTotalGoods> _list1 = vo.getListA();
		List<SalesDayTotalGoods> _list2 = vo.getListB();
		List<SalesDayTotalGoods> _list3 = vo.getListC();

		vo.setTotal(total.getPosQty());

		// 时间间隔（单位：天）
		long _spanDay = DateUtils.getDateSpanDay(startDate, endDate) + 1;
		BigDecimal _tmp_total = new BigDecimal("0");
		for (SalesDayTotalGoods _s : _totalList) {
			_s.setSpanDay(_spanDay);

			_tmp_total = _tmp_total.add(_s.getPosQty());

			if (_tmp_total.compareTo(total_1) == -1) {
				_list1.add(_s);

				vo.setTotalA(vo.getTotalA().add(_s.getPosQty()));
			} else if (_tmp_total.compareTo(total_2) == -1) {
				_list2.add(_s);

				vo.setTotalB(vo.getTotalB().add(_s.getPosQty()));
			} else {
				_list3.add(_s);

				vo.setTotalC(vo.getTotalC().add(_s.getPosQty()));
			}
		}

		return vo;
	}
}
