package com.tjhx.service.accounts;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.cache.memcached.SpyMemcachedClient;
import org.springside.modules.utils.SpringContextHolder;

import com.tjhx.dao.accounts.MonthSaleTargetJpaDao;
import com.tjhx.dao.accounts.MonthSaleTargetMyBatisDao;
import com.tjhx.entity.accounts.MonthSaleTarget;
import com.tjhx.entity.info.SalesMonthTotal_Show;
import com.tjhx.globals.MemcachedObjectType;
import com.tjhx.globals.SysConfig;

@Service
@Transactional(readOnly = true)
public class MonthSaleTargetManager {
	private static Logger logger = LoggerFactory.getLogger(MonthSaleTargetManager.class);

	@Resource
	private MonthSaleTargetJpaDao monthSaleTargetJpaDao;
	@Resource
	private MonthSaleTargetMyBatisDao monthSaleTargetMyBatisDao;
	@Resource
	private SpyMemcachedClient spyMemcachedClient;
	private final static String XML_CONFIG_MONTH_SALE_TARGET = "/excel/Month_Sale_Target_Template.xls";

	/**
	 * 取得月销售目标信息(根据机构编号/年份)
	 * 
	 * @return 月销售目标信息列表
	 */
	@SuppressWarnings("unchecked")
	public List<MonthSaleTarget> getByOrgIdAndOptDateY(String orgId, String optDateY) {
		return (List<MonthSaleTarget>) monthSaleTargetJpaDao.findByOrgIdAndOptDateY(orgId, optDateY, new Sort(new Sort.Order(
				Sort.Direction.ASC, "optDateM")));
	}

	/**
	 * 取得指定门店/年份/月份 销售目标
	 * 
	 * @param orgId
	 * @param optDateY
	 * @param optDateM
	 * @return
	 */
	public BigDecimal getMonthSaleTargetAmt(String orgId, String optDateY, String optDateM) {
		MonthSaleTarget _monthSaleTarget = getMonthSaleTarget(orgId, optDateY, optDateM);

		if (null == _monthSaleTarget) {
			return new BigDecimal("0");
		} else {
			return _monthSaleTarget.getSaleTargetAmt();
		}
	}

	/**
	 * 取得指定门店/年份/月份 销售目标
	 * 
	 * @param orgId
	 * @param optDateY
	 * @param optDateM
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public MonthSaleTarget getMonthSaleTarget(String orgId, String optDateY, String optDateM) {
		Map<String, MonthSaleTarget> _monthSaleTargetMap = spyMemcachedClient.get(MemcachedObjectType.MONTH_SALE_TARGET_MAP
				.getObjKey());

		if (null == _monthSaleTargetMap) {
			// 从数据库中取出销售目标信息(List格式)
			List<MonthSaleTarget> _monthSaleTargetList = (List<MonthSaleTarget>) monthSaleTargetJpaDao.findAll(new Sort(
					new Sort.Order(Sort.Direction.ASC, "orgId")));

			_monthSaleTargetMap = formatMonthSaleTargetList(_monthSaleTargetList);
			// 将销售目标信息Map保存到memcached
			spyMemcachedClient.set(MemcachedObjectType.MONTH_SALE_TARGET_MAP.getObjKey(),
					MemcachedObjectType.MONTH_SALE_TARGET_MAP.getExpiredTime(), _monthSaleTargetMap);

			logger.debug("销售目标信息不在 memcached中,从数据库中取出并放入memcached");
		} else {
			logger.debug("从memcached中取出销售目标信息");
		}
		return _monthSaleTargetMap.get(orgId + optDateY + optDateM);

	}

	/**
	 * 将销售目标信息List转换成Map格式
	 * 
	 * @param _monthSaleTargetList
	 * @return
	 */
	private Map<String, MonthSaleTarget> formatMonthSaleTargetList(List<MonthSaleTarget> _monthSaleTargetList) {
		Map<String, MonthSaleTarget> _monthSaleTargetMap = new HashMap<String, MonthSaleTarget>();
		for (MonthSaleTarget monthSaleTarget : _monthSaleTargetList) {
			_monthSaleTargetMap.put(monthSaleTarget.getOrgId() + monthSaleTarget.getOptDateY() + monthSaleTarget.getOptDateM(),
					monthSaleTarget);
		}
		return _monthSaleTargetMap;
	}

	/**
	 * 添加新月销售目标信息
	 * 
	 * @param monthSaleTarget 月销售目标信息
	 */
	@Transactional(readOnly = false)
	public void saveMonthSaleTarget(String orgId, String optDateY, List<MonthSaleTarget> newObjList) {
		if (StringUtils.isNotBlank(orgId)) {// 删除单机构指定年份销售目标
			MonthSaleTarget param = new MonthSaleTarget();
			param.setOrgId(orgId);
			param.setOptDateY(optDateY);

			monthSaleTargetMyBatisDao.delMonthSaleTargetInfo(param);
		} else {// 删除所有机构指定年份销售目标
			monthSaleTargetMyBatisDao.delMonthSaleTargetInfo_All(optDateY);
		}

		for (MonthSaleTarget monthSaleTarget : newObjList) {
			monthSaleTargetJpaDao.save(monthSaleTarget);
		}

		spyMemcachedClient.delete(MemcachedObjectType.MONTH_SALE_TARGET_MAP.getObjKey());
	}

	/**
	 * @param orgNameList
	 * @param _list
	 * @param totalList
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException
	 * @throws ParsePropertyException
	 */
	public String createDownLoadFile(String optDateY, List<String> orgNameList, List<List<SalesMonthTotal_Show>> dataList,
			List<SalesMonthTotal_Show> totalList) throws ParsePropertyException, InvalidFormatException, IOException {
		// ---------------------------文件生成---------------------------
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("optDateY", optDateY);
		map.put("orgNameList", orgNameList);
		map.put("totalList", totalList);
		map.put("dataList", dataList);

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		XLSTransformer transformer = new XLSTransformer();

		String tmpFileName = UUID.randomUUID().toString() + ".xls";
		String tmpFilePath = sysConfig.getReportTmpPath() + tmpFileName;
		transformer.transformXLS(sysConfig.getExcelTemplatePath() + XML_CONFIG_MONTH_SALE_TARGET, map, tmpFilePath);

		return tmpFileName;
	}
}