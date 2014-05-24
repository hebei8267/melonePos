package com.tjhx.service.accounts;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.SpringContextHolder;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.accounts.CashDailyJpaDao;
import com.tjhx.dao.accounts.CashDailyMyBatisDao;
import com.tjhx.dao.accounts.CashRunJpaDao;
import com.tjhx.dao.accounts.CashRunMyBatisDao;
import com.tjhx.daobw.DailySaleMyBatisDao;
import com.tjhx.entity.accounts.CashDaily;
import com.tjhx.entity.accounts.CashRun;
import com.tjhx.entity.bw.DailySale;
import com.tjhx.entity.receipt.SaleReport;
import com.tjhx.globals.SysConfig;

@Service
@Transactional(readOnly = true)
public class CashDailyManager {
	@Resource
	private CashRunJpaDao cashRunJpaDao;
	@Resource
	private CashRunMyBatisDao cashRunMyBatisDao;
	@Resource
	private CashDailyJpaDao cashDailyJpaDao;
	@Resource
	private CashDailyMyBatisDao cashDailyMyBatisDao;
	@Resource
	private DailySaleMyBatisDao dailySaleMyBatisDao;

	private final static String XML_CONFIG_CASH_DAILY = "/excel/Cash_Daily_Template.xls";
	private final static String XML_CONFIG_CASH_DAILY_2 = "/excel/Cash_Daily_Template_2.xls";
	private final static String XML_CONFIG_CARD_DAILY = "/excel/Card_Daily_Template.xls";

	/**
	 * 取得未日结销售流水日结信息
	 * 
	 * @param orgId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CashDaily> getAllNotCashDailyByOrgId(String orgId) {
		List<CashRun> _list = (List<CashRun>) cashRunJpaDao.getAllNotCashDailyByOrgId(orgId, new Sort(new Sort.Order(
				Sort.Direction.DESC, "optDate"), new Sort.Order(Sort.Direction.ASC, "jobType")));

		List<CashDaily> _noCashDailyList = new ArrayList<CashDaily>();
		String _tmpOptDate = "";
		CashDaily _tmpCashDaily = null;
		for (CashRun cashRun : _list) {
			if (!_tmpOptDate.equals(cashRun.getOptDate())) {// 新合计对象
				_tmpOptDate = cashRun.getOptDate();
				_tmpCashDaily = new CashDaily();
				_noCashDailyList.add(_tmpCashDaily);

				// 日期
				_tmpCashDaily.setOptDate(cashRun.getOptDate());
				_tmpCashDaily.setOptDateShow(cashRun.getOptDateShow());
				// 昨日余额
				_tmpCashDaily.setInitAmt(cashRun.getInitAmt());

			}

			// 现金盈亏（合计）
			_tmpCashDaily.setAdjustAmt(_tmpCashDaily.getAdjustAmt().add(cashRun.getAdjustAmt()));
			// 销售收现（合计）
			_tmpCashDaily.setSaleCashAmt(_tmpCashDaily.getSaleCashAmt().add(cashRun.getSaleCashAmt()));
			// 刷卡金额-单据统计-当日（合计）
			_tmpCashDaily.setCardAmt(_tmpCashDaily.getCardAmt().add(cashRun.getCardAmt()));
			// 存款金额-当日（合计）
			_tmpCashDaily.setDepositAmt(_tmpCashDaily.getDepositAmt().add(cashRun.getDepositAmt()));
			// 留存金额-当日
			_tmpCashDaily.setRetainedAmt(cashRun.getRetainedAmt());
			// 当日销售（合计）
			_tmpCashDaily.setSaleAmt(_tmpCashDaily.getSaleAmt().add(cashRun.getSaleAmt()));
			// 汇报金额
			_tmpCashDaily.setReportAmt(_tmpCashDaily.getReportAmt().add(cashRun.getReportAmt()));
			// 实际现金-当日（合计）
			// _tmpCashDaily.setCashAmt(_tmpCashDaily.getCashAmt().add(cashRun.getCashAmt()));
			// 刷卡金额-电脑统计-当日（合计）
			// _tmpCashDaily.setCardAmtBw(_tmpCashDaily.getCardAmtBw().add(cashRun.getCardAmtBw()));
			// 刷卡笔数-当日（合计）
			// _tmpCashDaily.setCardNum(_tmpCashDaily.getCardNum() +
			// cashRun.getCardNum());

			// 2014-5-11
			// 代金卷面值
			if (null != cashRun.getCouponValue()) {
				_tmpCashDaily.setCouponValue(_tmpCashDaily.getCouponValue().add(cashRun.getCouponValue()));
			}

		}
		return _noCashDailyList;
	}

	/**
	 * 取得销售流水日结信息
	 * 
	 * @param orgId
	 * @param currentDate(yyyyMMdd)
	 * @return
	 * @throws ParseException
	 */
	public List<CashDaily> getAllCashDailyByOrgId_1(String orgId, String currentDate) throws ParseException {
		String optDateY = DateUtils.transDateFormat(currentDate, "yyyyMMdd", "yyyy");

		String optDateM = DateUtils.transDateFormat(currentDate, "yyyyMMdd", "MM");

		return getAllCashDailyByOrgId(orgId, optDateY, optDateM);
	}

	/**
	 * 取得销售流水日结信息
	 * 
	 * @param orgId
	 * @param optDate(yyyy-MM)
	 * @return
	 * @throws ParseException
	 */
	public List<CashDaily> getAllCashDailyByOrgId_2(String orgId, String optDate) throws ParseException {
		String optDateY = DateUtils.transDateFormat(optDate, "yyyy-MM", "yyyy");

		String optDateM = DateUtils.transDateFormat(optDate, "yyyy-MM", "MM");

		return getAllCashDailyByOrgId(orgId, optDateY, optDateM);
	}

	/**
	 * 取得销售流水日结信息
	 * 
	 * @param orgId
	 * @param optDateY
	 * @param optDateM
	 * @return
	 * @throws ParseException
	 */
	private List<CashDaily> getAllCashDailyByOrgId(String orgId, String optDateY, String optDateM) throws ParseException {
		List<CashDaily> _list = (List<CashDaily>) cashDailyJpaDao.findByOrgId_OptDateY_OptDateM(orgId, optDateY, optDateM,
				new Sort(new Sort.Order(Sort.Direction.DESC, "optDate")));

		return _list;
	}

	/**
	 * 销售流水日结
	 * 
	 * @param optDate
	 * @param orgId
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false)
	public void cashDailyConfirm(String optDate, String orgId, String bwBranchNo) {
		List<CashRun> _list = (List<CashRun>) cashRunJpaDao.getNotCashDailyByOrgId_OptDate(orgId, optDate, new Sort(
				new Sort.Order(Sort.Direction.ASC, "jobType")));

		CashDaily _cashDaily = null;
		boolean firstFlg = true;
		for (CashRun cashRun : _list) {
			if (firstFlg) {
				firstFlg = false;
				_cashDaily = new CashDaily();

				// 机构编号
				_cashDaily.setOrgId(orgId);
				// 日期
				_cashDaily.setOptDate(cashRun.getOptDate());
				// 日期-显示
				_cashDaily.setOptDateShow(cashRun.getOptDateShow());
				// 日期-年
				_cashDaily.setOptDateY(cashRun.getOptDateY());
				// 日期-月
				_cashDaily.setOptDateM(cashRun.getOptDateM());
				// 昨日余额
				_cashDaily.setInitAmt(cashRun.getInitAmt());

			}
			// 当日销售（合计）
			_cashDaily.setSaleAmt(_cashDaily.getSaleAmt().add(cashRun.getSaleAmt()));

			// 刷卡金额-单据统计-当日（合计）
			_cashDaily.setCardAmt(_cashDaily.getCardAmt().add(cashRun.getCardAmt()));
			// 刷卡金额-电脑统计-当日（合计）
			_cashDaily.setCardAmtBw(_cashDaily.getCardAmtBw().add(cashRun.getCardAmtBw()));
			// 刷卡笔数-当日（合计）
			_cashDaily.setCardNum(_cashDaily.getCardNum() + cashRun.getCardNum());
			// 存款金额-当日（合计）
			_cashDaily.setDepositAmt(_cashDaily.getDepositAmt().add(cashRun.getDepositAmt()));
			// 留存金额-当日
			_cashDaily.setRetainedAmt(cashRun.getRetainedAmt());
			// 销售现金-当日（合计）
			_cashDaily.setSaleCashAmt(_cashDaily.getSaleCashAmt().add(cashRun.getSaleCashAmt()));
			// 现金盈亏（调节）
			_cashDaily.setAdjustAmt(_cashDaily.getAdjustAmt().add(cashRun.getAdjustAmt()));
			// 汇报金额
			_cashDaily.setReportAmt(_cashDaily.getReportAmt().add(cashRun.getReportAmt()));
			// 修改销售流水标记
			cashRun.setDailyFlg(true);

			cashRunJpaDao.save(cashRun);
			// ############ 20130817-追加 ################
			// 百威系统机构编号
			_cashDaily.setOrgBranchNo(bwBranchNo);
			// ############ 20130817-追加 ################

			// 2014-5-11
			// 代金卷面值
			if (null != cashRun.getCouponValue()) {
				_cashDaily.setCouponValue(_cashDaily.getCouponValue().add(cashRun.getCouponValue()));
				_cashDaily.setCouponCashValue(_cashDaily.getCouponCashValue().add(cashRun.getCouponCashValue()));
			}
		}
		// 生成销售日结信息
		cashDailyJpaDao.save(_cashDaily);
	}

	/**
	 * 日结销售流水明细查看
	 * 
	 * @param optDate
	 * @param orgId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CashRun> cashDailyDetail(String optDate, String orgId) {
		return (List<CashRun>) cashRunJpaDao.findByOrgId_OptDate(orgId, optDate, new Sort(new Sort.Order(Sort.Direction.ASC,
				"jobType")));
	}

	/**
	 * 日结销售流水明细查看
	 * 
	 * @param cashRun
	 * @return
	 */
	public List<CashRun> cashDailyDetail(CashRun cashRun) {
		return cashRunMyBatisDao.getCashRunList(cashRun);
	}

	/**
	 * 取得销售流水日结信息
	 * 
	 * @param cashDaily
	 * @return
	 */
	public List<CashDaily> searchReportList(CashDaily cashDaily) {
		return cashDailyMyBatisDao.getCashDailyList(cashDaily);
	}

	/**
	 * 取得销售流水日结信息
	 * 
	 * @param cashDaily
	 * @return
	 */
	public List<CashRun> searchSaleReportList(CashRun cashRun) {
		return cashRunMyBatisDao.getCashRunList_OptDate_Interval(cashRun);
	}

	/**
	 * 合计计算
	 * 
	 * @param cashDailyList
	 * @return
	 */
	public CashDaily calTotal_CashDaily(List<CashDaily> cashDailyList) {
		CashDaily _cashDaily = new CashDaily();

		int _index = 0;
		for (CashDaily cashDaily : cashDailyList) {
			_index++;
			cashDaily.setIndex(_index);

			// 现金盈亏（调节）
			_cashDaily.setAdjustAmt(_cashDaily.getAdjustAmt().add(cashDaily.getAdjustAmt()));
			// 销售现金-当日（合计）
			_cashDaily.setSaleCashAmt(_cashDaily.getSaleCashAmt().add(cashDaily.getSaleCashAmt()));
			// 刷卡金额(单据)
			_cashDaily.setCardAmt(_cashDaily.getCardAmt().add(cashDaily.getCardAmt()));
			// 刷卡金额(电脑统计)
			_cashDaily.setCardAmtBw(_cashDaily.getCardAmtBw().add(cashDaily.getCardAmtBw()));
			// 刷卡笔数
			_cashDaily.setCardNum(_cashDaily.getCardNum() + cashDaily.getCardNum());
			// 存款金额
			_cashDaily.setDepositAmt(_cashDaily.getDepositAmt().add(cashDaily.getDepositAmt()));
			// 当日销售
			_cashDaily.setSaleAmt(_cashDaily.getSaleAmt().add(cashDaily.getSaleAmt()));
			// 代价卷
			_cashDaily.setCouponValue(_cashDaily.getCouponValue().add(cashDaily.getCouponValue()));
			// 当日销售BW
			_cashDaily.setBwSaleAmt(_cashDaily.getBwSaleAmt().add(cashDaily.getBwSaleAmt()));
			// 汇报金额
			_cashDaily.setReportAmt(_cashDaily.getReportAmt().add(cashDaily.getReportAmt()));
		}
		return _cashDaily;
	}

	/**
	 * 合计计算
	 * 
	 * @param reportList
	 * @return
	 */
	public SaleReport calTotal_SaleReport(List<SaleReport> reportList) {
		SaleReport _saleReport = new SaleReport();
		for (SaleReport saleReport : reportList) {
			_saleReport.setSaleCashAmt(_saleReport.getSaleCashAmt().add(saleReport.getSaleCashAmt()));
			_saleReport.setCashAmt(_saleReport.getCashAmt().add(saleReport.getCashAmt()));
			_saleReport.setAdjustAmt(_saleReport.getAdjustAmt().add(saleReport.getAdjustAmt()));
			_saleReport.setCardAmt(_saleReport.getCardAmt().add(saleReport.getCardAmt()));
			_saleReport.setCardAmtBw(_saleReport.getCardAmtBw().add(saleReport.getCardAmtBw()));
			_saleReport.setDepositAmt(_saleReport.getDepositAmt().add(saleReport.getDepositAmt()));
			_saleReport.setSaleAmt(_saleReport.getSaleAmt().add(saleReport.getSaleAmt()));
			_saleReport.setCardNum(_saleReport.getCardNum() + saleReport.getCardNum());
		}
		return _saleReport;
	}

	/**
	 * 取得销售流水日结信息(全机构)
	 * 
	 * @param currentDate(yyyy-MM-dd)
	 * @return
	 * @throws ParseException
	 */
	public List<CashDaily> getCashDailyListByAllOrg(String dailyDate) throws ParseException {
		CashDaily _cashDaily = new CashDaily();
		_cashDaily.setOptDateShow(dailyDate);
		return cashDailyMyBatisDao.getCashDailyListByAllOrg(_cashDaily);
	}

	private void cardTotalProcess(List<CashRun> _list, List<SaleReport> reportList) throws ParseException {
		String _tmpOrgName = "";
		String _tmpOptDate = "";
		int _index = 0;

		SaleReport _saleReport = null;
		for (CashRun _cashRun : _list) {

			if (!_tmpOrgName.equals(_cashRun.getOrgName()) || !_tmpOptDate.equals(_cashRun.getOptDateShow())) {

				_tmpOrgName = _cashRun.getOrgName();
				_tmpOptDate = _cashRun.getOptDateShow();
				_index++;

				_saleReport = new SaleReport();
				_saleReport.setIndex(_index);
				reportList.add(_saleReport);
			}
			_saleReport.addCashRunInfo_Card(_cashRun);
		}
	}

	private void cashTotalProcess(List<CashRun> _list, List<SaleReport> reportList) throws ParseException {
		int _index = 0;

		for (CashRun _cashRun : _list) {
			_index++;

			SaleReport _saleReport = new SaleReport();
			reportList.add(_saleReport);

			_saleReport.setIndex(_index);
			_saleReport.addCashRunInfo_Cash(_cashRun);
		}
	}

	/**
	 * 创建刷卡流水日结信息文件
	 * 
	 * @param _cashRun
	 * @return
	 * @throws ParsePropertyException
	 * @throws InvalidFormatException
	 * @throws IOException
	 * @throws ParseException
	 */
	public String createCardReportFile(CashRun _cashRun) throws ParsePropertyException, InvalidFormatException, IOException,
			ParseException {
		List<CashRun> _list = searchSaleReportList(_cashRun);
		if (null == _list || _list.size() == 0) {
			return null;
		}

		List<SaleReport> reportList = new ArrayList<SaleReport>();
		cardTotalProcess(_list, reportList);
		SaleReport _totalSaleReport = calTotal_SaleReport(reportList);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportList", reportList);
		map.put("totalReport", _totalSaleReport);

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		XLSTransformer transformer = new XLSTransformer();

		String tmpFileName = UUID.randomUUID().toString() + ".xls";
		String tmpFilePath = sysConfig.getReportTmpPath() + tmpFileName;
		transformer.transformXLS(sysConfig.getExcelTemplatePath() + XML_CONFIG_CARD_DAILY, map, tmpFilePath);

		return tmpFileName;
	}

	/**
	 * 生产销售日结文件-合计
	 * 
	 * @param _cashDaily
	 * @return
	 * @throws ParsePropertyException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public String createCashReportFile(CashDaily _cashDaily) throws ParsePropertyException, InvalidFormatException, IOException {
		List<CashDaily> _cashDailyList = searchReportList(_cashDaily);
		if (null == _cashDailyList || _cashDailyList.size() == 0) {
			return null;
		}
		// 反转列表数据顺序
		Collections.reverse(_cashDailyList);

		CashDaily totalCashDaily = calTotal_CashDaily(_cashDailyList);

		// ---------------------------文件生成---------------------------
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportList", _cashDailyList);
		map.put("totalReport", totalCashDaily);

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		XLSTransformer transformer = new XLSTransformer();

		String tmpFileName = UUID.randomUUID().toString() + ".xls";
		String tmpFilePath = sysConfig.getReportTmpPath() + tmpFileName;
		transformer.transformXLS(sysConfig.getExcelTemplatePath() + XML_CONFIG_CASH_DAILY_2, map, tmpFilePath);

		return tmpFileName;
	}

	/**
	 * 创建销售流水日结信息文件(明细)
	 * 
	 * @param cashDaily
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException
	 * @throws ParsePropertyException
	 * @throws ParseException
	 */
	public String createCashReportFile(CashRun _cashRun) throws ParsePropertyException, InvalidFormatException, IOException,
			ParseException {
		List<CashRun> _list = searchSaleReportList(_cashRun);
		if (null == _list || _list.size() == 0) {
			return null;
		}

		List<SaleReport> reportList = new ArrayList<SaleReport>();
		cashTotalProcess(_list, reportList);
		SaleReport _totalSaleReport = calTotal_SaleReport(reportList);

		// ---------------------------文件生成---------------------------
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportList", reportList);
		map.put("totalReport", _totalSaleReport);

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		XLSTransformer transformer = new XLSTransformer();

		String tmpFileName = UUID.randomUUID().toString() + ".xls";
		String tmpFilePath = sysConfig.getReportTmpPath() + tmpFileName;
		transformer.transformXLS(sysConfig.getExcelTemplatePath() + XML_CONFIG_CASH_DAILY, map, tmpFilePath);

		return tmpFileName;
	}

	/**
	 * 同步百威销售额
	 * 
	 * @throws ParseException
	 */
	@Transactional(readOnly = false)
	public void synBwSaleAmt() throws ParseException {
		// 取得同步百威销售额-重计算天数
		List<String> optDateList = calOptDate();

		for (String optDate : optDateList) {
			List<CashDaily> _cashDailyList = cashDailyJpaDao.findByOptDate(optDate);

			List<DailySale> _bwDailySaleList = dailySaleMyBatisDao.getDailySaleList(optDate);

			for (CashDaily _cashDaily : _cashDailyList) {
				for (DailySale _dailySale : _bwDailySaleList) {

					if (myEquals(_cashDaily, _dailySale)) {

						// 设置百威系统的日销售额度
						_cashDaily.setBwSaleAmt(_dailySale.getBwSaleAmt());

						cashDailyJpaDao.save(_cashDaily);

					}
				}
			}

		}

	}

	private boolean myEquals(CashDaily cashDaily, DailySale dailySale) {

		if (!cashDaily.getOrgBranchNo().equals(dailySale.getOrgBranchNo())) {
			return false;
		}

		if (!cashDaily.getOptDate().equals(dailySale.getOperDate())) {
			return false;
		}

		return true;
	}

	/**
	 * 同步百威销售额-重计算天数
	 * 
	 * @return
	 * @throws ParseException
	 */
	public List<String> calOptDate() throws ParseException {
		List<String> _optDateList = new ArrayList<String>();

		String _now = DateUtils.getCurFormatDate("yyyyMMdd");

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		for (int i = 1; i < sysConfig.getSynBwSaleDays() + 1; i++) {
			_optDateList.add(DateUtils.getNextDateFormatDate(_now, -i, "yyyyMMdd"));
		}
		return _optDateList;
	}

	/**
	 * 取得销售流水日结信息(准备图形化显示数据)--单个机构
	 * 
	 * @param cashDaily
	 * @return
	 */
	public List<CashDaily> searchChartReportListByOrg(CashDaily cashDaily) {
		return cashDailyMyBatisDao.getCashDailyChartList(cashDaily);
	}

	/**
	 * 取得销售流水日结信息(准备图形化显示数据)--所有机构
	 * 
	 * @param cashDaily
	 * @return
	 */
	public List<CashDaily> searchChartReportList(CashDaily cashDaily) {
		return cashDailyMyBatisDao.getAllOrgCashDailyChartList(cashDaily);
	}

	/**
	 * 取得指定机构最终销售流水日结信息
	 * 
	 * @param orgId
	 * @return
	 */
	public CashDaily getLastCashDailyInfoByOrg(String orgId) {
		return cashDailyMyBatisDao.getLastCashDailyInfoByOrg(orgId);
	}

	/**
	 * 反日结
	 * 
	 * @param orgId
	 * @param optDate
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false)
	public void counterDaily(String orgId, String optDate) {
		CashDaily _dParam = new CashDaily();
		_dParam.setOrgId(orgId);
		_dParam.setOptDate(optDate);
		cashDailyMyBatisDao.delCashDailyInfo(_dParam);

		CashRun _rParam = new CashRun();
		_rParam.setOrgId(orgId);
		_rParam.setOptDate(optDate);
		cashRunMyBatisDao.delCashRunInfo(_rParam);

		List<CashRun> _list = (List<CashRun>) cashRunJpaDao.findByOrgId_OptDate(orgId, optDate, null);
		for (CashRun cashRun : _list) {
			cashRun.setDailyFlg(false);

			cashRunJpaDao.save(cashRun);
		}
	}
}
