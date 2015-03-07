package com.tjhx.service.info;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.SpringContextHolder;

import com.google.common.collect.Maps;
import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.info.StoreDayTotalJpaDao;
import com.tjhx.dao.info.StoreDayTotalMyBatisDao;
import com.tjhx.dao.info.StoreDetailJpaDao;
import com.tjhx.dao.info.StoreDetailMyBatisDao;
import com.tjhx.daobw.StoreMyBatisDao;
import com.tjhx.entity.bw.Store;
import com.tjhx.entity.info.StoreDayTotal;
import com.tjhx.entity.info.StoreDetail;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.struct.OrganizationManager;

@Service
@Transactional(readOnly = true)
public class StoreDetailManager {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(StoreDetailManager.class);

	@Resource
	private OrganizationManager orgManager;
	@Resource
	private StoreMyBatisDao storeMyBatisDao;
	@Resource
	private StoreDetailJpaDao storeDetailJpaDao;
	@Resource
	private StoreDetailMyBatisDao storeDetailMyBatisDao;
	@Resource
	private StoreDayTotalJpaDao storeDayTotalJpaDao;
	@Resource
	private StoreDayTotalMyBatisDao storeDayTotalMyBatisDao;

	/**
	 * 取得门店日(按商品供应商)销售计算-重计算天数
	 * 
	 * @return
	 * @throws ParseException
	 */
	private List<String> calOptDate() throws ParseException {
		List<String> _optDateList = new ArrayList<String>();

		String _now = DateUtils.getCurFormatDate("yyyyMMdd");

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		for (int i = 1; i < sysConfig.getSalesDayTotalSupDays(); i++) {
			_optDateList.add(DateUtils.getNextDateFormatDate(_now, -i, "yyyyMMdd"));
		}

		return _optDateList;
	}

	/**
	 * 取得门店库存信息
	 * 
	 * @throws ParseException
	 */
	@Transactional(readOnly = false)
	public void getOrgStoreDetail() throws ParseException {
		List<Organization> _orgList = orgManager.getSubOrganization();

		List<String> optDateList = calOptDate();
		for (String optDate : optDateList) {
			String _optDate = DateUtils.transDateFormat(optDate, "yyyyMMdd", "yyyy-MM-dd");
			String optDateY = DateUtils.transDateFormat(optDate, "yyyyMMdd", "yyyy");
			String optDateM = DateUtils.transDateFormat(optDate, "yyyyMMdd", "MM");

			// 删除指定日期的库存
			storeDetailMyBatisDao.delStoreDetail(optDate);

			for (Organization org : _orgList) {

				Map<String, String> param = Maps.newHashMap();
				param.put("bw_branch_no", org.getBwBranchNo());
				param.put("oper_date", _optDate);
				List<Store> bwStoreList = storeMyBatisDao.getStoreInfoList(param);

				int _index = 0;
				for (Store bwStore : bwStoreList) {
					StoreDetail storeDetail = new StoreDetail();

					// 机构编号
					storeDetail.setOrgId(org.getId());
					// 机构资金-百威
					storeDetail.setBwBranchNo(org.getBwBranchNo());

					storeDetail.setOptDate(optDate);
					// 日期-年
					storeDetail.setOptDateY(optDateY);
					// 日期-月
					storeDetail.setOptDateM(optDateM);
					// 库存标记 0-正库存 1-负库存
					storeDetail.setStoreFlg(bwStore.getStockQty().compareTo(BigDecimal.ZERO) == 1 ? "0" : "1");
					// Index
					storeDetail.setIndex(++_index);
					// 货号
					storeDetail.setItemSubno(bwStore.getItemSubno());
					// 条形码
					storeDetail.setItemBarcode(bwStore.getItemBarcode());
					// 商品名称
					storeDetail.setItemName(bwStore.getItemName());
					// 库存数量
					storeDetail.setStockQty(bwStore.getStockQty());
					// 库存金额
					storeDetail.setStockAmt(bwStore.getStockAmt());
					// 售价金额
					storeDetail.setItemSaleAmt(bwStore.getItemSaleAmt());

					storeDetailJpaDao.save(storeDetail);
				}

			}

		}

	}

	/**
	 * 计算门店库存合计信息
	 * 
	 * @throws ParseException
	 */
	@Transactional(readOnly = false)
	public void calOrgStoreDayTotal() throws ParseException {
		List<Organization> _orgList = orgManager.getSubOrganization();

		List<String> optDateList = calOptDate();
		for (String optDate : optDateList) {

			storeDayTotalMyBatisDao.delStoreDayTotal(optDate);

			for (Organization org : _orgList) {
				// 日期
				StoreDetail _param = new StoreDetail();
				_param.setBwBranchNo(org.getBwBranchNo());
				_param.setOptDate(optDate);
				List<StoreDetail> _dbDayTotal = storeDetailMyBatisDao.getDayTotalList(_param);

				for (StoreDetail storeDetail : _dbDayTotal) {
					StoreDayTotal _storeDayTotal = new StoreDayTotal();

					// 机构编号
					_storeDayTotal.setOrgId(storeDetail.getOrgId());
					// 机构资金-百威
					_storeDayTotal.setBwBranchNo(storeDetail.getBwBranchNo());
					// 日期
					_storeDayTotal.setOptDate(storeDetail.getOptDate());
					// 日期-年
					_storeDayTotal.setOptDateY(storeDetail.getOptDateY());
					// 日期-月
					_storeDayTotal.setOptDateM(storeDetail.getOptDateM());
					// 库存标记 0-正库存 1-负库存
					_storeDayTotal.setStoreFlg(storeDetail.getStoreFlg());
					// 库存数量
					_storeDayTotal.setStockTotalQty(storeDetail.getStockTotalQty());
					// 库存金额
					_storeDayTotal.setStockTotalAmt(storeDetail.getStockTotalAmt());
					// 售价金额
					_storeDayTotal.setItemSaleTotalAmt(storeDetail.getItemSaleTotalAmt());

					storeDayTotalJpaDao.save(_storeDayTotal);
				}
			}

		}

	}

	/**
	 * 清理t_store_detail
	 */
	@Transactional(readOnly = false)
	public void initTable() {
		// 数据量过大,其数据已汇总到t_store_day_total
		// storeDetailMyBatisDao.dropTable();
		// storeDetailMyBatisDao.createTable();

		// 删除库存明细信息,仅保留近90天数据
		String _optDate = DateUtils.getNextDateFormatDate(-90, "yyyyMMdd");
		storeDetailMyBatisDao.delStoreDetail_GreaterThan90Day(_optDate);
	}

	public static void main(String[] args) {
		System.out.println(DateUtils.getNextDateFormatDate(-90, "yyyyMMdd"));
	}
}
