package com.tjhx.service.info;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tjhx.dao.info.SalesDayTotalItemMyBatisDao;
import com.tjhx.entity.info.ItemType;
import com.tjhx.entity.info.SalesDayTotalItem;
import com.tjhx.entity.struct.Organization;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.vo.SalesContrastVo;

@Service
@Transactional(readOnly = true)
public class SalesContrastByItemManager {
	@Resource
	private SalesDayTotalItemMyBatisDao salesDayTotalItemMyBatisDao;
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private ItemTypeManager itemTypeManager;

	/**
	 * 拷贝对比1的显示值
	 * 
	 * @param list1
	 * @return
	 */
	private void copyList1Value(List<SalesContrastVo> voList, String optDate1Start, String optDate1End, String orgId,
			String itemNoArray) {

		Map<String, String> param = Maps.newHashMap();
		param.put("optDateStart", optDate1Start);
		param.put("optDateEnd", optDate1End);
		param.put("orgId", orgId);
		param.put("itemType", itemNoArray);
		List<SalesDayTotalItem> list1 = salesDayTotalItemMyBatisDao.getContrastList(param);

		for (SalesContrastVo vo : voList) {
			int _index = list1.indexOf(vo);
			if (-1 != _index) {
				SalesDayTotalItem value = list1.get(_index);
				// 实销数量1
				vo.setSaleRqty1(value.getSaleRqty());
				// 实销金额1
				vo.setSaleRamt1(value.getSaleRamt());
				// 实销均价1
				vo.setSalePrice1(value.getSaleRamt().divide(value.getSaleRqty(), 2, BigDecimal.ROUND_UP));
			}
		}

		// // 实销数量2
		// vo.setSaleRqty2(saleRqty2);
		// // 实销金额2
		// vo.setSaleRamt2(saleRamt2);
		// // 实销均价2
		// vo.setSalePrice2(salePrice2);
		// // 销售额增长/下降率
		// vo.setSalesContrast(salesContrast);

	}

	/**
	 * @param optDate1Start
	 * @param optDate1End
	 * @param optDate2Start
	 * @param optDate2End
	 * @param itemNoArray
	 * @param orgId
	 * @return
	 */
	public List<List<SalesContrastVo>> search(String optDate1Start, String optDate1End, String optDate2Start, String optDate2End,
			String itemNoArray, String orgId) {

		List<SalesContrastVo> voList = initBlankVoList(itemNoArray);
		copyList1Value(voList, optDate1Start, optDate1End, orgId, itemNoArray);
		copyList2Value(voList, optDate2Start, optDate2End, orgId, itemNoArray);
		return formatVoList(voList);
	}

	/**
	 * @param voList
	 * @param optDate1Start
	 * @param optDate1End
	 * @param orgId
	 * @param itemNoArray
	 */
	private void copyList2Value(List<SalesContrastVo> voList, String optDate2Start, String optDate2End, String orgId,
			String itemNoArray) {
		Map<String, String> param = Maps.newHashMap();
		param.put("optDateStart", optDate2Start);
		param.put("optDateEnd", optDate2End);
		param.put("orgId", orgId);
		param.put("itemType", itemNoArray);
		List<SalesDayTotalItem> list1 = salesDayTotalItemMyBatisDao.getContrastList(param);

		for (SalesContrastVo vo : voList) {
			int _index = list1.indexOf(vo);
			if (-1 != _index) {
				SalesDayTotalItem value = list1.get(_index);
				// 实销数量2
				vo.setSaleRqty2(value.getSaleRqty());
				// 实销金额2
				vo.setSaleRamt2(value.getSaleRamt());
				// 实销均价2
				vo.setSalePrice2(value.getSaleRamt().divide(value.getSaleRqty(), 2, BigDecimal.ROUND_UP));

				// 销售额增长/下降率
				if (vo.getSaleRamt1().compareTo(BigDecimal.ZERO) == 1) {
					BigDecimal tmp = vo.getSaleRamt2().subtract(vo.getSaleRamt1());
					tmp = tmp.divide(vo.getSaleRamt1(), 2, BigDecimal.ROUND_UP);
					tmp = tmp.multiply(new BigDecimal("100"));
					vo.setSalesContrast(tmp);
				}

			}
		}

	}

	/**
	 * 初始化空白销售信息对比VO对象列表
	 * 
	 * @param itemNoArray
	 * @return
	 */
	private List<SalesContrastVo> initBlankVoList(String itemNoArray) {
		List<String> itemNoList = Lists.newArrayList(itemNoArray.split(","));

		List<SalesContrastVo> voList = Lists.newArrayList();

		List<Organization> orgList = orgManager.getSubOrganization();
		for (Organization org : orgList) {
			for (String itemNo : itemNoList) {
				SalesContrastVo vo = new SalesContrastVo();
				// 机构编号
				vo.setOrgId(org.getId());
				// 机构名称
				vo.setOrgName(org.getName());
				// 类型编号
				vo.setItemClsNo(itemNo);
				// 类型名称
				ItemType itemType = itemTypeManager.getByItemNo(itemNo);
				if (null != itemType) {
					vo.setItemName(itemType.getItemShortName());
				}

				voList.add(vo);
			}
		}
		return voList;
	}

	/**
	 * 校验是否有略过的机构空行
	 * 
	 * @param tmpList
	 * @return
	 */
	private List<List<SalesContrastVo>> formatVoList(List<SalesContrastVo> voList) {

		List<List<SalesContrastVo>> _list = Lists.newArrayList();
		List<SalesContrastVo> _subList = null;
		String tmpOrgId = null;
		for (SalesContrastVo vo : voList) {
			if (null == tmpOrgId || !tmpOrgId.equals(vo.getOrgId())) {
				tmpOrgId = vo.getOrgId();
				_subList = Lists.newArrayList();

				_list.add(_subList);
			}
			_subList.add(vo);
		}

		return _list;
	}
}
