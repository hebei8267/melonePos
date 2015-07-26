package com.tjhx.service.info;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference;
import com.google.common.collect.MapDifference.ValueDifference;
import com.google.common.collect.Maps;
import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.info.SalesWeekTotalGoods1JpaDao;
import com.tjhx.dao.info.SalesWeekTotalGoods2JpaDao;
import com.tjhx.dao.info.SalesWeekTotalGoods3JpaDao;
import com.tjhx.dao.info.SalesWeekTotalGoods4JpaDao;
import com.tjhx.dao.info.StoreDetailMyBatisDao;
import com.tjhx.dao.info.WeekSalesTotalGoodsJpaDao;
import com.tjhx.dao.info.WeekSalesTotalGoodsMyBatisDao;
import com.tjhx.entity.info.SalesWeekTotalGoods_1;
import com.tjhx.entity.info.SalesWeekTotalGoods_2;
import com.tjhx.entity.info.SalesWeekTotalGoods_3;
import com.tjhx.entity.info.SalesWeekTotalGoods_4;
import com.tjhx.entity.info.StoreDetail;
import com.tjhx.entity.info.WeekSalesTotalGoods;
import com.tjhx.entity.struct.Organization;
import com.tjhx.service.struct.OrganizationManager;

@Service
@Transactional(readOnly = true)
public class SalesWeekTotalGoodsManager2 {
	@Resource
	private OrganizationManager organizationManager;
	@Resource
	private SalesWeekTotalGoods1JpaDao salesWeekTotalGoods1JpaDao;
	@Resource
	private SalesWeekTotalGoods2JpaDao salesWeekTotalGoods2JpaDao;
	@Resource
	private SalesWeekTotalGoods3JpaDao salesWeekTotalGoods3JpaDao;
	@Resource
	private SalesWeekTotalGoods4JpaDao salesWeekTotalGoods4JpaDao;
	@Resource
	private StoreDetailMyBatisDao storeDetailMyBatisDao;
	@Resource
	private WeekSalesTotalGoodsJpaDao weekSalesTotalGoodsJpaDao;
	@Resource
	private WeekSalesTotalGoodsMyBatisDao weekSalesTotalGoodsMyBatisDao;

	@Transactional(readOnly = false)
	public void saveWeekSalesTotalGoodsInfo() {
		weekSalesTotalGoodsMyBatisDao.delWeekSalesTotalGoods();

		Map<String, List<WeekSalesTotalGoods>> map = getWeekSalesInfo();

		for (Entry<String, List<WeekSalesTotalGoods>> entry : map.entrySet()) {
			for (WeekSalesTotalGoods salesInfo : entry.getValue()) {
				weekSalesTotalGoodsJpaDao.save(salesInfo);
			}
		}
	}

	/**
	 * 取得周销售信息列表（各门店）
	 * 
	 * @return
	 */
	private Map<String, List<WeekSalesTotalGoods>> getWeekSalesInfo() {
		Map<String, List<WeekSalesTotalGoods>> map = new HashMap<String, List<WeekSalesTotalGoods>>();

		// 取得门店机构（不包含总部机构）
		List<Organization> orgList = organizationManager.getSubOrganization();
		for (Organization org : orgList) {

			map.put(org.getId(), getWeekSalesTotalGoodsList(org.getId()));
		}

		return map;
	}

	/**
	 * 取得各门店机构销售信息列表
	 * 
	 * @param orgId 门店机构编号
	 * @return
	 */
	private List<WeekSalesTotalGoods> getWeekSalesTotalGoodsList(String orgId) {
		Map<String, BigDecimal> salesGoodsMap1 = Maps.newHashMap();
		getOrgWeekSalesGoodsTotal1(orgId, salesGoodsMap1);

		Map<String, BigDecimal> salesGoodsMap2 = Maps.newHashMap();
		getOrgWeekSalesGoodsTotal2(orgId, salesGoodsMap2);

		// 归并Map(周销售信息)
		Map<String, BigDecimal> monthMap1 = mergeWeekSalesTotalGoodsMap(salesGoodsMap1, salesGoodsMap2);

		Map<String, BigDecimal> salesGoodsMap3 = Maps.newHashMap();
		getOrgWeekSalesGoodsTotal3(orgId, salesGoodsMap3);

		Map<String, BigDecimal> salesGoodsMap4 = Maps.newHashMap();
		getOrgWeekSalesGoodsTotal4(orgId, salesGoodsMap4);

		// 归并Map(周销售信息)
		Map<String, BigDecimal> monthMap2 = mergeWeekSalesTotalGoodsMap(salesGoodsMap3, salesGoodsMap4);

		// 归并Map(半月销售信息)
		Map<String, BigDecimal> _monthMap = mergeMonthSalesTotalGoodsMap(monthMap1, monthMap2);

		// 取得指定机构头一天的库存信息
		Map<String, String> param = Maps.newHashMap();
		param.put("orgId", orgId);
		param.put("optDate", DateUtils.getNextDateFormatDate(-1, "yyyyMMdd"));

		List<StoreDetail> sdList = storeDetailMyBatisDao.getStoreListGroupBySubno(param);

		Map<String, WeekSalesTotalGoods> wsg = Maps.newHashMap();
		// 以库存为基础计算
		for (StoreDetail sd : sdList) {
			String itemSubno = sd.getItemSubno();
			BigDecimal posQty = _monthMap.get(itemSubno);
			if (null == posQty) {
				continue;
			}
			WeekSalesTotalGoods dto = new WeekSalesTotalGoods();

			// 机构编号
			dto.setOrgId(orgId);
			// 短条码
			dto.setItemSubno(itemSubno);
			// 销售数量
			dto.setPosQty(posQty);
			// 库存数量
			dto.setStockQty(sd.getStockQty());

			wsg.put(itemSubno, dto);
		}

		// 以销售为基础计算
		for (Entry<String, BigDecimal> sgEntry : _monthMap.entrySet()) {
			String itemSubno = sgEntry.getKey();

			WeekSalesTotalGoods tmpDto = wsg.get(itemSubno);

			if (null == tmpDto) {
				WeekSalesTotalGoods dto = new WeekSalesTotalGoods();

				// 机构编号
				dto.setOrgId(orgId);
				// 短条码
				dto.setItemSubno(itemSubno);
				// 销售数量
				dto.setPosQty(sgEntry.getValue());
				// 库存数量
				dto.setStockQty(new BigDecimal("0"));

				wsg.put(itemSubno, dto);
			}

		}

		List<WeekSalesTotalGoods> dtoList = Lists.newArrayList();
		for (Entry<String, WeekSalesTotalGoods> sgEntry : wsg.entrySet()) {
			dtoList.add(sgEntry.getValue());
		}
		return dtoList;
	}

	/**
	 * 归并Map(半月销售信息)
	 * 
	 * @param mapA
	 * @param mapB
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private Map<String, BigDecimal> mergeMonthSalesTotalGoodsMap(Map<String, BigDecimal> mapA,
			Map<String, BigDecimal> mapB) {
		Map<String, BigDecimal> _map = new HashMap<String, BigDecimal>();

		MapDifference<String, BigDecimal> differenceMap = Maps.difference(mapA, mapB);

		Map<String, BigDecimal> entriesOnlyOnLeft = differenceMap.entriesOnlyOnLeft();
		_map.putAll(entriesOnlyOnLeft);

		Map<String, BigDecimal> entriesOnlyOnRight = differenceMap.entriesOnlyOnRight();
		_map.putAll(entriesOnlyOnRight);

		Map<String, ValueDifference<BigDecimal>> entriesDiffering = differenceMap.entriesDiffering();
		Set<Entry<String, ValueDifference<BigDecimal>>> params = entriesDiffering.entrySet();
		for (Map.Entry entry : params) {
			String key = (String) entry.getKey();

			int r = mapA.get(key).compareTo(mapB.get(key));
			if (r == 1 || r == 0) { // 大于,等于
				_map.put(key, mapA.get(key));
			} else {// 小于
				_map.put(key, mapB.get(key));
			}

		}

		return _map;
	}

	/**
	 * 归并Map(周销售信息)
	 * 
	 * @param mapA
	 * @param mapB
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private Map<String, BigDecimal> mergeWeekSalesTotalGoodsMap(Map<String, BigDecimal> mapA,
			Map<String, BigDecimal> mapB) {

		Map<String, BigDecimal> _map = new HashMap<String, BigDecimal>();

		MapDifference<String, BigDecimal> differenceMap = Maps.difference(mapA, mapB);

		Map<String, BigDecimal> entriesOnlyOnLeft = differenceMap.entriesOnlyOnLeft();
		_map.putAll(entriesOnlyOnLeft);

		Map<String, BigDecimal> entriesOnlyOnRight = differenceMap.entriesOnlyOnRight();
		_map.putAll(entriesOnlyOnRight);

		Map<String, ValueDifference<BigDecimal>> entriesDiffering = differenceMap.entriesDiffering();
		Set<Entry<String, ValueDifference<BigDecimal>>> params = entriesDiffering.entrySet();
		for (Map.Entry entry : params) {
			String key = (String) entry.getKey();
			_map.put(key, mapA.get(key).add(mapB.get(key)));

		}

		return _map;
	}

	/**
	 * 取得各门店机构销售信息列表(近一周)
	 * 
	 * @param orgId
	 * @param salesGoodsMap
	 */
	private void getOrgWeekSalesGoodsTotal1(String orgId, Map<String, BigDecimal> salesGoodsMap) {
		List<SalesWeekTotalGoods_1> list = salesWeekTotalGoods1JpaDao.findByOrgId(orgId);

		for (SalesWeekTotalGoods_1 g1 : list) {
			BigDecimal posQty = salesGoodsMap.get(g1.getItemSubno());
			if (null == posQty) {
				salesGoodsMap.put(g1.getItemSubno(), g1.getPosQty());
			} else {
				salesGoodsMap.put(g1.getItemSubno(), posQty.add(g1.getPosQty()));
			}
		}

	}

	/**
	 * 取得各门店机构销售信息列表(近二周)
	 * 
	 * @param orgId
	 * @param salesGoodsMap
	 */
	private void getOrgWeekSalesGoodsTotal2(String orgId, Map<String, BigDecimal> salesGoodsMap) {
		List<SalesWeekTotalGoods_2> list = salesWeekTotalGoods2JpaDao.findByOrgId(orgId);

		for (SalesWeekTotalGoods_2 g2 : list) {
			BigDecimal posQty = salesGoodsMap.get(g2.getItemSubno());
			if (null == posQty) {
				salesGoodsMap.put(g2.getItemSubno(), g2.getPosQty());
			} else {
				salesGoodsMap.put(g2.getItemSubno(), posQty.add(g2.getPosQty()));
			}
		}

	}

	/**
	 * 取得各门店机构销售信息列表(近三周)
	 * 
	 * @param orgId
	 * @param salesGoodsMap
	 */
	private void getOrgWeekSalesGoodsTotal3(String orgId, Map<String, BigDecimal> salesGoodsMap) {
		List<SalesWeekTotalGoods_3> list = salesWeekTotalGoods3JpaDao.findByOrgId(orgId);

		for (SalesWeekTotalGoods_3 g3 : list) {
			BigDecimal posQty = salesGoodsMap.get(g3.getItemSubno());
			if (null == posQty) {
				salesGoodsMap.put(g3.getItemSubno(), g3.getPosQty());
			} else {
				salesGoodsMap.put(g3.getItemSubno(), posQty.add(g3.getPosQty()));
			}
		}

	}

	/**
	 * 取得各门店机构销售信息列表(近四周)
	 * 
	 * @param orgId
	 * @param salesGoodsMap
	 */
	private void getOrgWeekSalesGoodsTotal4(String orgId, Map<String, BigDecimal> salesGoodsMap) {
		List<SalesWeekTotalGoods_4> list = salesWeekTotalGoods4JpaDao.findByOrgId(orgId);

		for (SalesWeekTotalGoods_4 g4 : list) {
			BigDecimal posQty = salesGoodsMap.get(g4.getItemSubno());
			if (null == posQty) {
				salesGoodsMap.put(g4.getItemSubno(), g4.getPosQty());
			} else {
				salesGoodsMap.put(g4.getItemSubno(), posQty.add(g4.getPosQty()));
			}
		}

	}
}
