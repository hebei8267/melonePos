package com.tjhx.service.accounts;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.accounts.MonthSaleTargetJpaDao;
import com.tjhx.dao.accounts.MonthSaleTargetMyBatisDao;
import com.tjhx.entity.accounts.MonthSaleTarget;

@Service
@Transactional(readOnly = true)
public class MonthSaleTargetManager {
	@Resource
	private MonthSaleTargetJpaDao monthSaleTargetJpaDao;
	@Resource
	private MonthSaleTargetMyBatisDao monthSaleTargetMyBatisDao;

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

	}
}