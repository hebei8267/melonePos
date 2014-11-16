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

	/**
	 * 拷贝对比1的显示值
	 * 
	 * @param list1
	 * @return
	 */
	private List<SalesContrastVo> copyList_1_Value(List<SalesDayTotalItem> list1) {
		List<SalesContrastVo> tmpList = Lists.newArrayList();

		for (SalesDayTotalItem salesDayTotalItem : list1) {
			SalesContrastVo vo = new SalesContrastVo();

			// 机构名称
			vo.setOrgName(salesDayTotalItem.getOrgName());
			// 机构编号
			vo.setOrgId(salesDayTotalItem.getOrgId());
			// 类型编号
			vo.setItemClsNo(salesDayTotalItem.getItemClsNo());
			// 类型名称
			vo.setItemName(salesDayTotalItem.getItemName());

			// 实销数量1
			vo.setSaleRqty1(salesDayTotalItem.getSaleRqty());
			// 实销金额1
			vo.setSaleRamt1(salesDayTotalItem.getSaleRamt());
			// 实销均价1
			vo.setSalePrice1(salesDayTotalItem.getSaleRamt().divide(salesDayTotalItem.getSaleRqty(), 2, BigDecimal.ROUND_UP));

			// // 实销数量2
			// vo.setSaleRqty2(saleRqty2);
			// // 实销金额2
			// vo.setSaleRamt2(saleRamt2);
			// // 实销均价2
			// vo.setSalePrice2(salePrice2);
			// // 销售额增长/下降率
			// vo.setSalesContrast(salesContrast);

			tmpList.add(vo);
		}

		return tmpList;
	}

	/**
	 * @param optDate1_start
	 * @param optDate1_end
	 * @param optDate2_start
	 * @param optDate2_end
	 * @param itemType
	 * @param orgId
	 * @return
	 */
	public List<List<SalesContrastVo>> search(String optDate1_start, String optDate1_end, String optDate2_start,
			String optDate2_end, String itemType, String orgId) {

		Map<String, String> param1 = Maps.newHashMap();
		param1.put("optDateStart", optDate1_start);
		param1.put("optDateEnd", optDate1_end);
		param1.put("orgId", orgId);
		param1.put("itemType", itemType);
		List<SalesDayTotalItem> list1 = salesDayTotalItemMyBatisDao.getContrastList(param1);

		Map<String, String> param2 = Maps.newHashMap();
		param2.put("optDateStart", optDate2_start);
		param2.put("optDateEnd", optDate2_end);
		param2.put("orgId", orgId);
		param2.put("itemType", itemType);
		List<SalesDayTotalItem> list2 = salesDayTotalItemMyBatisDao.getContrastList(param1);

		List<SalesContrastVo> tmpList = copyList_1_Value(list1);

		List<List<SalesContrastVo>> tmpRsList = checkBlankOrgList(tmpList, itemType);
		return tmpRsList;
	}

	/**
	 * 校验是否有略过的机构空行
	 * 
	 * @param tmpList
	 * @return
	 */
	private List<List<SalesContrastVo>> checkBlankOrgList(List<SalesContrastVo> tmpList, String itemType) {
		List<String> itemTypeList =Lists.newArrayList( itemType.split(","));
		
		List<List<SalesContrastVo>> _list = Lists.newArrayList();
		List<SalesContrastVo> _subList = null;
		String tmpOrgId = null;
		for (SalesContrastVo vo : tmpList) {
			if (null == tmpOrgId || !tmpOrgId.equals(vo.getOrgId())) {
				tmpOrgId = vo.getOrgId();
				_subList = Lists.newArrayList();

				_list.add(_subList);
			}
			_subList.add(vo);
		}

//		// 加空行(机构)
//		List<Organization> orgList = orgManager.getSubOrganization();
//		int index = 0;
//
//		List<List<SalesContrastVo>> _reList = Lists.newArrayList();
//		for (List<SalesContrastVo> subList : _list) {
//			if (null != subList && subList.size() > 0) {
//				String tmpOrgId1 = subList.get(0).getOrgId();
//				String tmpOrgId2 = orgList.get(index).getId();
//				if (tmpOrgId2.equals(tmpOrgId1)) {
//
//					List<SalesContrastVo> _ttList = Lists.newArrayList();
//					for (SalesContrastVo vo : subList) {
//						if(itemTypeList.contains(_vo.getItemClsNo())){
//							_ttList.add(vo);
//						}else{
//							SalesContrastVo _vo = new SalesContrastVo();
//							_vo.setOrgId(tmpOrgId2);
//							_vo.setItemClsNo(_itemType);
//							_ttList.add(_vo);
//						}
//					}
//					_reList.add(subList);
//					
//					
//				} else {// 无该机构，加指定行数的对比信息
//					List<SalesContrastVo> _ttList = Lists.newArrayList();
//
//					for (String _itemType : itemTypeList) {
//						SalesContrastVo _vo = new SalesContrastVo();
//						_vo.setOrgId(tmpOrgId2);
//						_vo.setItemClsNo(_itemType);
//						_ttList.add(_vo);
//					}
//
//					_reList.add(_ttList);
//				}
//				index++;
//			}
//
//		}

		return _list;
	}
}
