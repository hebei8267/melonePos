package com.tjhx.service.affair;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.info.StoreDayTotalMyBatisDao;
import com.tjhx.entity.info.StoreDayTotal;

@Service
@Transactional(readOnly = true)
public class StoreChartReportManager {
	@Resource
	private StoreDayTotalMyBatisDao storeDayTotalMyBatisDao;

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
}
