package com.tjhx.service.info;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

		System.out.println(_res[0]);
		System.out.println(_res[1]);
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
	 * 取得指定条码各门店近四周销售数据及库存情况
	 * 
	 * @param barcode 商品条码
	 * @return
	 */
	public List<ReqBill> getSalesWeekGoodsTotalList_ByBarcode(String barcode) {
		return salesWeekTotalGoodsMyBatisDao.getSalesWeekGoodsTotalList_ByBarcode(barcode);
	}

}
