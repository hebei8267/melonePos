package com.tjhx.service.info;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.SpringContextHolder;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.info.SalesDayTotalSupJpaDao;
import com.tjhx.dao.info.SalesDayTotalSupMyBatisDao;
import com.tjhx.daobw.DailySaleTotalSupMyBatisDao;
import com.tjhx.entity.bw.DailySaleTotalSup;
import com.tjhx.entity.info.SalesDayTotalSup;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.struct.OrganizationManager;

@Service
@Transactional(readOnly = true)
public class SalesDayTotalSupManager {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private SalesDayTotalSupJpaDao salesDayTotalSupJpaDao;
	@Resource
	private SalesDayTotalSupMyBatisDao salesDayTotalSupMyBatisDao;
	@Resource
	private DailySaleTotalSupMyBatisDao dailySaleTotalSupMyBatisDao;

	/**
	 * 取得百威系统各门店日销售信息(按供应商)
	 * 
	 * @throws ParseException
	 */
	@Transactional(readOnly = false)
	public void getOrgSalesDayTotal() throws ParseException {
		// 取得门店日销售计算-重计算天数
		List<String> optDateList = calOptDate();
		List<Organization> _orgList = orgManager.getSubOrganization();

		for (String optDate : optDateList) {
			// 删除指定日期的所有门店销售信息(按供应商分类)
			salesDayTotalSupMyBatisDao.delSalesDayTotalInfo(optDate);

			for (Organization org : _orgList) {
				DailySaleTotalSup _param = new DailySaleTotalSup();
				_param.setOptDateStart(DateUtils.transDateFormat(optDate, "yyyyMMdd", "yyyy-MM-dd"));
				String _endDate = DateUtils.getNextDateFormatDate(optDate, 1, "yyyyMMdd");
				_param.setOptDateEnd(DateUtils.transDateFormat(_endDate, "yyyyMMdd", "yyyy-MM-dd"));
				_param.setBranchNo(org.getBwBranchNo());
				List<DailySaleTotalSup> _bwList = dailySaleTotalSupMyBatisDao.getDailySaleTotalList(_param);

				for (DailySaleTotalSup _bwSaleSup : _bwList) {
					SalesDayTotalSup _saleSup = new SalesDayTotalSup();
					// 日期
					_saleSup.setOptDate(optDate);
					String optDateY = DateUtils.transDateFormat(optDate, "yyyyMMdd", "yyyy");
					String optDateM = DateUtils.transDateFormat(optDate, "yyyyMMdd", "MM");
					// 日期-年
					_saleSup.setOptDateY(optDateY);
					// 日期-月
					_saleSup.setOptDateM(optDateM);
					// 机构编号
					_saleSup.setOrgId(org.getId());
					// 供应商编号-百威
					_saleSup.setSupplierBwId(_bwSaleSup.getSupcustNo());
					// 机构资金编号
					_saleSup.setBranchNo(org.getBwBranchNo());
					// 前台销售数量
					_saleSup.setPosQty(_bwSaleSup.getPosQty());
					// 前台销售金额
					_saleSup.setPosAmt(_bwSaleSup.getPosAmt());
					// 批发销售数量
					_saleSup.setPfQty(_bwSaleSup.getPfQty());
					// 批发销售金额
					_saleSup.setPfAmt(_bwSaleSup.getPfAmt());
					// 合计销售数量
					_saleSup.setSaleQty(_bwSaleSup.getSaleQty());
					// 合计销售金额
					_saleSup.setSaleAmt(_bwSaleSup.getSaleAmt());
					// 区域编码
					_saleSup.setRegionCode(_bwSaleSup.getRegionNo());

					salesDayTotalSupJpaDao.save(_saleSup);
				}

			}
		}
	}

	/**
	 * 取得门店日(按商品供应商)销售计算-重计算天数
	 * 
	 * @return
	 * @throws ParseException
	 */
	private List<String> calOptDate() throws ParseException {
		List<String> _optDateList = new ArrayList<String>();

		String _now = DateUtils.getCurFormatDate("yyyyMMdd");
		_optDateList.add(_now);

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		for (int i = 1; i < sysConfig.getSalesDayTotalSupDays(); i++) {
			_optDateList.add(DateUtils.getNextDateFormatDate(_now, -i, "yyyyMMdd"));
		}

		return _optDateList;
	}

	/**
	 * 取得合计实销金额（指定时间区间/机构）-按供应商
	 * 
	 * @param salesDayTotalSup
	 * @return
	 */
	public List<SalesDayTotalSup> getSumSaleRamtList(SalesDayTotalSup salesDayTotalSup) {
		return salesDayTotalSupMyBatisDao.getSumSaleRamtList(salesDayTotalSup);
	}

	/**
	 * 取得合计实销数量（指定时间区间/机构）-按供应商
	 * 
	 * @param salesDayTotalSup
	 * @return
	 */
	public List<SalesDayTotalSup> getSumSaleRqtyList(SalesDayTotalSup salesDayTotalSup) {
		return salesDayTotalSupMyBatisDao.getSumSaleRqtyList(salesDayTotalSup);
	}
}
