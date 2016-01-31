package com.tjhx.service.affair;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tjhx.dao.info.StoreDayTotalMyBatisDao;
import com.tjhx.entity.info.StoreDayTotal;
import com.tjhx.entity.struct.Organization;
import com.tjhx.service.struct.OrganizationManager;

@Service
@Transactional(readOnly = true)
public class StoreChartReportManager {
	@Resource
	private StoreDayTotalMyBatisDao storeDayTotalMyBatisDao;
	@Resource
	private OrganizationManager organizationManager;

	/**
	 * 取得最近的库存信息日期
	 * 
	 * @return 最近的库存信息日期
	 */
	public String getMaxOptDate() {
		StoreDayTotal _storeDayTotal = storeDayTotalMyBatisDao.getMaxOptDate();
		if (null != _storeDayTotal) {
			return _storeDayTotal.getOptDate();
		}
		return null;
	}

	/**
	 * 根据日期取得各门店库存信息
	 * 
	 * @param optDate 日期
	 * @return
	 */
	public List<StoreDayTotal> getStoreDayTotalList(String optDate) {
		return storeDayTotalMyBatisDao.getStoreDayTotalList(optDate);
	}

	
	/**
	 * 根据门店库存合计信息
	 * 
	 * @param orgId 门店编号
	 */
	public List<List<StoreDayTotal>> getStoreTotalListGroupByDay(String orgId) {
		List<List<StoreDayTotal>> _reList = Lists.newArrayList();
		if (StringUtils.isBlank(orgId)) {// 全机构
			// 合计
			Map<String, String> _param = Maps.newHashMap();
			_param.put("orgId", null);
			_reList.add(storeDayTotalMyBatisDao.getStoreTotalListGroupByDay(_param));

			// 所有门店机构
			List<Organization> orgList = organizationManager.getSubOrganization();
			for (Organization organization : orgList) {
				Map<String, String> param = Maps.newHashMap();
				param.put("orgId", organization.getId());

				_reList.add(storeDayTotalMyBatisDao.getStoreTotalListGroupByDay(param));
			}

		} else {// 单个机构
			Map<String, String> param = Maps.newHashMap();
			param.put("orgId", orgId);

			_reList.add(storeDayTotalMyBatisDao.getStoreTotalListGroupByDay(param));
		}

		return _reList;
	}
}
