package com.tjhx.service.info;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.info.SalesDayTotalGoodsMyBatisDao;
import com.tjhx.dao.info.SalesWeekTotalGoods1JpaDao;
import com.tjhx.dao.info.SalesWeekTotalGoods2JpaDao;
import com.tjhx.dao.info.SalesWeekTotalGoods3JpaDao;
import com.tjhx.dao.info.SalesWeekTotalGoods4JpaDao;
import com.tjhx.dao.info.SalesWeekTotalGoodsMyBatisDao;
import com.tjhx.entity.info.SalesDayTotalGoods;
import com.tjhx.entity.info.SalesWeekTotalGoods_1;
import com.tjhx.entity.info.SalesWeekTotalGoods_2;
import com.tjhx.entity.info.SalesWeekTotalGoods_3;
import com.tjhx.entity.info.SalesWeekTotalGoods_4;
import com.tjhx.entity.order.ReqBill;
import com.tjhx.entity.struct.Organization;
import com.tjhx.service.struct.OrganizationManager;

@Service
@Transactional(readOnly = true)
public class SalesWeekTotalGoodsManager {

	@Resource
	private SalesDayTotalGoodsMyBatisDao salesDayTotalGoodsMyBatisDao;
	@Resource
	private SalesWeekTotalGoodsMyBatisDao salesWeekTotalGoodsMyBatisDao;
	@Resource
	private SalesWeekTotalGoods1JpaDao salesWeekTotalGoods1JpaDao;
	@Resource
	private SalesWeekTotalGoods2JpaDao salesWeekTotalGoods2JpaDao;
	@Resource
	private SalesWeekTotalGoods3JpaDao salesWeekTotalGoods3JpaDao;
	@Resource
	private SalesWeekTotalGoods4JpaDao salesWeekTotalGoods4JpaDao;
	@Resource
	private OrganizationManager orgManager;

	/**
	 * 取得重计算区间（近几周）
	 * 
	 * @param week 周数
	 * @return
	 * @throws ParseException
	 */

	private String[] calOptDate(int week) throws ParseException {
		String _now = DateUtils.getCurrentDateShortStr();
		String[] _res = new String[2];
		_res[0] = DateUtils.getNextDateFormatDate(_now, (week) * -7, "yyyyMMdd");
		_res[1] = DateUtils.getNextDateFormatDate(_now, (week - 1) * -7 - 1, "yyyyMMdd");

		return _res;
	}

	public static void main(String[] args) throws ParseException {
		SalesWeekTotalGoodsManager s = new SalesWeekTotalGoodsManager();
		s.calOptDate(1);
		s.calOptDate(2);
		s.calOptDate(3);
		s.calOptDate(4);
	}

	/**
	 * 重计算近1周销售合计情况
	 * 
	 * @throws ParseException
	 */
	public void calSalesWeekTotalGoodsInfo_1() throws ParseException {
		// 清除近1周销售合计情况
		salesWeekTotalGoodsMyBatisDao.delSalesWeekTotalGoodsInfo_1();
		String[] optDate = calOptDate(1);

		SalesDayTotalGoods param = new SalesDayTotalGoods();
		param.setOptDateStart(optDate[0]);
		param.setOptDateEnd(optDate[1]);
		List<SalesDayTotalGoods> _saleList = salesDayTotalGoodsMyBatisDao.getSumSaleInfoList_Week(param);
		for (SalesDayTotalGoods _salesGoods : _saleList) {
			SalesWeekTotalGoods_1 _s = new SalesWeekTotalGoods_1();
			// 机构编号
			_s.setOrgId(_salesGoods.getOrgId());
			// 机构资金编号
			_s.setBranchNo(_salesGoods.getBranchNo());
			// 统计日期
			_s.setOptDate(optDate[1]);
			// 短条码
			_s.setItemSubno(_salesGoods.getItemSubno());
			// 长条码
			_s.setItemBarcode(_salesGoods.getItemBarcode());
			// 销售数量-近1周
			_s.setPosQty(_salesGoods.getPosQty());
			// 销售金额-近1周
			_s.setPosAmt(_salesGoods.getPosAmt());

			salesWeekTotalGoods1JpaDao.save(_s);
		}
	}

	/**
	 * 重计算近2周销售合计情况
	 * 
	 * @throws ParseException
	 */
	public void calSalesWeekTotalGoodsInfo_2() throws ParseException {
		// 清除近2周销售合计情况
		salesWeekTotalGoodsMyBatisDao.delSalesWeekTotalGoodsInfo_2();
		String[] optDate = calOptDate(2);

		SalesDayTotalGoods param = new SalesDayTotalGoods();
		param.setOptDateStart(optDate[0]);
		param.setOptDateEnd(optDate[1]);
		List<SalesDayTotalGoods> _saleList = salesDayTotalGoodsMyBatisDao.getSumSaleInfoList_Week(param);
		for (SalesDayTotalGoods _salesGoods : _saleList) {
			SalesWeekTotalGoods_2 _s = new SalesWeekTotalGoods_2();
			// 机构编号
			_s.setOrgId(_salesGoods.getOrgId());
			// 机构资金编号
			_s.setBranchNo(_salesGoods.getBranchNo());
			// 统计日期
			_s.setOptDate(optDate[1]);
			// 短条码
			_s.setItemSubno(_salesGoods.getItemSubno());
			// 长条码
			_s.setItemBarcode(_salesGoods.getItemBarcode());
			// 销售数量-近2周
			_s.setPosQty(_salesGoods.getPosQty());
			// 销售金额-近2周
			_s.setPosAmt(_salesGoods.getPosAmt());

			salesWeekTotalGoods2JpaDao.save(_s);
		}
	}

	/**
	 * 重计算近3周销售合计情况
	 * 
	 * @throws ParseException
	 */
	public void calSalesWeekTotalGoodsInfo_3() throws ParseException {
		// 清除近3周销售合计情况
		salesWeekTotalGoodsMyBatisDao.delSalesWeekTotalGoodsInfo_3();
		String[] optDate = calOptDate(3);

		SalesDayTotalGoods param = new SalesDayTotalGoods();
		param.setOptDateStart(optDate[0]);
		param.setOptDateEnd(optDate[1]);
		List<SalesDayTotalGoods> _saleList = salesDayTotalGoodsMyBatisDao.getSumSaleInfoList_Week(param);
		for (SalesDayTotalGoods _salesGoods : _saleList) {
			SalesWeekTotalGoods_3 _s = new SalesWeekTotalGoods_3();
			// 机构编号
			_s.setOrgId(_salesGoods.getOrgId());
			// 机构资金编号
			_s.setBranchNo(_salesGoods.getBranchNo());
			// 统计日期
			_s.setOptDate(optDate[1]);
			// 短条码
			_s.setItemSubno(_salesGoods.getItemSubno());
			// 长条码
			_s.setItemBarcode(_salesGoods.getItemBarcode());
			// 销售数量-近3周
			_s.setPosQty(_salesGoods.getPosQty());
			// 销售金额-近3周
			_s.setPosAmt(_salesGoods.getPosAmt());

			salesWeekTotalGoods3JpaDao.save(_s);
		}
	}

	/**
	 * 重计算近4周销售合计情况
	 * 
	 * @throws ParseException
	 */
	public void calSalesWeekTotalGoodsInfo_4() throws ParseException {
		// 清除近4周销售合计情况
		salesWeekTotalGoodsMyBatisDao.delSalesWeekTotalGoodsInfo_4();
		String[] optDate = calOptDate(4);

		SalesDayTotalGoods param = new SalesDayTotalGoods();
		param.setOptDateStart(optDate[0]);
		param.setOptDateEnd(optDate[1]);
		List<SalesDayTotalGoods> _saleList = salesDayTotalGoodsMyBatisDao.getSumSaleInfoList_Week(param);
		for (SalesDayTotalGoods _salesGoods : _saleList) {
			SalesWeekTotalGoods_4 _s = new SalesWeekTotalGoods_4();
			// 机构编号
			_s.setOrgId(_salesGoods.getOrgId());
			// 机构资金编号
			_s.setBranchNo(_salesGoods.getBranchNo());
			// 统计日期
			_s.setOptDate(optDate[1]);
			// 短条码
			_s.setItemSubno(_salesGoods.getItemSubno());
			// 长条码
			_s.setItemBarcode(_salesGoods.getItemBarcode());
			// 销售数量-近4周
			_s.setPosQty(_salesGoods.getPosQty());
			// 销售金额-近4周
			_s.setPosAmt(_salesGoods.getPosAmt());

			salesWeekTotalGoods4JpaDao.save(_s);
		}
	}

	/**
	 * 取得指定门店近四周销售数据及库存情况-按销量排序
	 * 
	 * @param orgId 门店编号
	 * @param barcode 货号/条码
	 * @return
	 */
	public List<ReqBill> getSalesWeekGoodsTotalList_ByOrg_OrderQty(String orgId, String barcode) {
		ReqBill param = new ReqBill();
		param.setOrgId(orgId);
		param.setBarcode(barcode);
		return salesWeekTotalGoodsMyBatisDao.getSalesWeekGoodsTotalList_ByOrg_OrderQty(param);
	}

	/**
	 * 取得指定门店近四周销售数据及库存情况-按销售额排序
	 * 
	 * @param orgId 门店编号
	 * @param barcode 货号/条码
	 * @return
	 */
	public List<ReqBill> getSalesWeekGoodsTotalList_ByOrg_OrderAmt(String orgId, String barcode) {
		ReqBill param = new ReqBill();
		param.setOrgId(orgId);
		param.setBarcode(barcode);
		return salesWeekTotalGoodsMyBatisDao.getSalesWeekGoodsTotalList_ByOrg_OrderAmt(param);
	}

	/**
	 * 计算合计
	 * 
	 * @param totalReqBill
	 * @param barcode
	 * @param orgIdName
	 * @param reqBill
	 */
	private void calTotalReqBill(ReqBill totalReqBill, String barcode, String orgIdName, ReqBill reqBill) {
		totalReqBill.setOrgId(orgIdName);
		// 货号
		totalReqBill.setBarcode(barcode);
		// 名称
		totalReqBill.setProductName(reqBill.getProductName());
		// 销量(件) 近一周
		totalReqBill.setPosQty1(totalReqBill.getPosQty1().add(reqBill.getPosQty1()));
		// 销量(件) 近两周
		totalReqBill.setPosQty2(totalReqBill.getPosQty2().add(reqBill.getPosQty2()));
		// 销量(件) 近三周
		totalReqBill.setPosQty3(totalReqBill.getPosQty3().add(reqBill.getPosQty3()));
		// 销量(件) 近四周
		totalReqBill.setPosQty4(totalReqBill.getPosQty4().add(reqBill.getPosQty4()));
		// 销量(件) 合计
		totalReqBill.setPosQtyTotal(totalReqBill.getPosQtyTotal().add(reqBill.getPosQtyTotal()));
		// 销量(件) 合计
		totalReqBill.setHmPosQty(totalReqBill.getHmPosQty().add(reqBill.getHmPosQty()));

		// 库存量
		totalReqBill.setStockQty(totalReqBill.getStockQty().add(reqBill.getStockQty()));
		// 销售额(元) 近一周
		totalReqBill.setPosAmt1(totalReqBill.getPosAmt1().add(reqBill.getPosAmt1()));
		// 销售额(元) 近二周
		totalReqBill.setPosAmt2(totalReqBill.getPosAmt2().add(reqBill.getPosAmt2()));
		// 销售额(元) 近三周
		totalReqBill.setPosAmt3(totalReqBill.getPosAmt3().add(reqBill.getPosAmt3()));
		// 销售额(元) 近四周
		totalReqBill.setPosAmt4(totalReqBill.getPosAmt4().add(reqBill.getPosAmt4()));
		// 销售额(元)合计
		totalReqBill.setPosAmtTotal(totalReqBill.getPosAmtTotal().add(reqBill.getPosAmtTotal()));

		totalReqBill.setInQty(totalReqBill.getInQty().add(reqBill.getInQty()).subtract(reqBill.getOutQty()));
	}

	/**
	 * 取得指定条码各门店近四周销售数据及库存情况
	 * 
	 * @param barcode 商品条码
	 * @return
	 */
	public List<ReqBill> getSalesWeekGoodsTotalList_ByBarcode(String barcode) {
		Map<String, String> paramMap = Maps.newHashMap();
		paramMap.put("barcode", barcode);
		paramMap.put("optDate", DateUtils.getNextDateFormatDate(-1, "yyyyMMdd"));
		List<ReqBill> list = salesWeekTotalGoodsMyBatisDao.getSalesWeekGoodsTotalList_ByBarcode(paramMap);

		ReqBill totalReqBill = new ReqBill();
		for (ReqBill reqBill : list) {
			// ===================================
			// 进价/售价
			int r = reqBill.getStockQty().compareTo(BigDecimal.ZERO); // 和0，Zero比较
			if (r != 0) {
				reqBill.setItemSaleAmt(reqBill.getItemSaleAmt().divide(reqBill.getStockQty(), 2, BigDecimal.ROUND_UP));
				reqBill.setStockAmt(reqBill.getStockAmt().divide(reqBill.getStockQty(), 2, BigDecimal.ROUND_UP));
			}

			// 调入
			if (reqBill.getHmPosQty().compareTo(reqBill.getStockQty()) > 0) {
				reqBill.setInQty(reqBill.getHmPosQty().subtract(reqBill.getStockQty()));
			}

			// 调出
			if (reqBill.getHmPosQty().multiply(new BigDecimal("2")).compareTo(reqBill.getStockQty()) < 0) {
				reqBill.setOutQty(reqBill.getStockQty().subtract(reqBill.getHmPosQty().multiply(new BigDecimal("2"))));
			}

			// ===================================
			calTotalReqBill(totalReqBill, barcode, "合计", reqBill);
		}
		// 20160807按品牌分类

		List<Organization> orgList = orgManager.getOpenSubOrganization();
		List<ReqBill> _eqList = calSalesWeekGoodsTotalList_EQ(orgList, list);
		List<ReqBill> _infancyList = calSalesWeekGoodsTotalList_Infancy(orgList, list);

		List<ReqBill> _reList = Lists.newArrayList();
		_reList.add(totalReqBill);
		_reList.addAll(_eqList);
		_reList.addAll(_infancyList);
		// 总部
		_reList.add(list.get(list.size() - 1));

		return _reList;
	}

	private List<ReqBill> calSalesWeekGoodsTotalList_EQ(List<Organization> orgList, List<ReqBill> list) {
		List<ReqBill> _eqList = Lists.newArrayList();

		ReqBill totalReqBill = new ReqBill();
		for (ReqBill reqBill : list) {

			for (Organization _org : orgList) {
				if ("EQ+".equals(_org.getBrand()) && _org.getName().equals(reqBill.getOrgId())) {
					_eqList.add(reqBill);
					calTotalReqBill(totalReqBill, reqBill.getBarcode(), "EQ+", reqBill);
				}
			}

		}

		_eqList.add(0, totalReqBill);

		return _eqList;

	}

	private List<ReqBill> calSalesWeekGoodsTotalList_Infancy(List<Organization> orgList, List<ReqBill> list) {
		List<ReqBill> _infancyList = Lists.newArrayList();

		ReqBill totalReqBill = new ReqBill();
		for (ReqBill reqBill : list) {
			for (Organization _org : orgList) {
				if ("Infancy".equals(_org.getBrand()) && _org.getName().equals(reqBill.getOrgId())) {
					_infancyList.add(reqBill);
					calTotalReqBill(totalReqBill, reqBill.getBarcode(), "Infancy", reqBill);
				}
			}

		}

		_infancyList.add(0, totalReqBill);

		return _infancyList;

	}

}
