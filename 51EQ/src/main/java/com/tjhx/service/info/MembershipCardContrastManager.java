/**
 * 
 */
package com.tjhx.service.info;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tjhx.daobw.MembershipCardMyBatisDao;
import com.tjhx.entity.bw.MembershipCard;
import com.tjhx.entity.struct.Organization;
import com.tjhx.service.struct.OrganizationManager;

@Service
@Transactional(readOnly = true)
public class MembershipCardContrastManager {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private MembershipCardMyBatisDao membershipCardMyBatisDao;

	public List<MembershipCard> getContrastList(String preStartOptDate, String preEndOptDate, String currentStartOptDate,
			String currentEndOptDate, String orgId) {

		List<MembershipCard> voList = initBlankVoList(orgId);
		// 取得金卡发行次数
		copyList1Value_IssueInfo(voList, preStartOptDate, preEndOptDate);
		// 取得金卡发行次数
		copyList2Value_IssueInfo(voList, currentStartOptDate, currentEndOptDate);

		// 取得金卡返利金额
		copyList1Value_RetAmtInfo(voList, preStartOptDate, preEndOptDate);
		// 取得金卡返利金额
		copyList2Value_RetAmtInfo(voList, currentStartOptDate, currentEndOptDate);

		// 取得金卡充值金额
		copyList1Value_RechargeAmtInfo(voList, preStartOptDate, preEndOptDate);
		// 取得金卡充值金额
		copyList2Value_RechargeAmtInfo(voList, currentStartOptDate, currentEndOptDate);

		// 取得金卡消费次数
		copyList1Value_ConsumeCntInfo(voList, preStartOptDate, preEndOptDate);
		// 取得金卡消费次数
		copyList2Value_ConsumeCntInfo(voList, currentStartOptDate, currentEndOptDate);

		// 取得消费次数
		copyList1Value_TotalConsumeCntInfo(voList, preStartOptDate, preEndOptDate);
		// 取得消费次数
		copyList2Value_TotalConsumeCntInfo(voList, currentStartOptDate, currentEndOptDate);

		// 取得金卡消费金额
		copyList1Value_ConsumeAmtInfo(voList, preStartOptDate, preEndOptDate);
		// 取得金卡消费金额
		copyList2Value_ConsumeAmtInfo(voList, currentStartOptDate, currentEndOptDate);

		// 取得消费金额
		copyList1Value_TotalConsumeAmtInfo(voList, preStartOptDate, preEndOptDate);
		// 取得消费金额
		copyList2Value_TotalConsumeAmtInfo(voList, currentStartOptDate, currentEndOptDate);

		// 计算合计
		calTotal(voList);

		return voList;
	}

	private void setTotalValue(MembershipCard total, MembershipCard membershipCard) {
		// 金卡发行次数-1
		total.setIssueCnt1(total.getIssueCnt1() + membershipCard.getIssueCnt1());
		// 金卡发行次数-2
		total.setIssueCnt2(total.getIssueCnt2() + membershipCard.getIssueCnt2());

		// 金卡返利-1
		total.setRetAmt1(total._getRetAmt1().add(membershipCard._getRetAmt1()));
		// 金卡返利-2
		total.setRetAmt2(total._getRetAmt2().add(membershipCard._getRetAmt2()));

		// 金卡充值-1
		total.setRechargeAmt1(total._getRechargeAmt1().add(membershipCard._getRechargeAmt1()));
		// 金卡充值-2
		total.setRechargeAmt2(total._getRechargeAmt2().add(membershipCard._getRechargeAmt2()));

		// 金卡消费次数-1
		total.setConsumeCnt1(total.getConsumeCnt1() + membershipCard.getConsumeCnt1());
		// 金卡消费次数-2
		total.setConsumeCnt2(total.getConsumeCnt2() + membershipCard.getConsumeCnt2());

		// 消费次数-1
		total.setTotalConsumeCnt1(total.getTotalConsumeCnt1() + membershipCard.getTotalConsumeCnt1());
		// 消费次数-2
		total.setTotalConsumeCnt2(total.getTotalConsumeCnt2() + membershipCard.getTotalConsumeCnt2());

		// 金卡消费金额
		total.setSaleAmt1(total._getSaleAmt1().add(membershipCard._getSaleAmt1()));
		// 金卡消费金额
		total.setSaleAmt2(total._getSaleAmt2().add(membershipCard._getSaleAmt2()));

		// 消费金额-1
		total.setTotalSaleAmt1(total._getTotalSaleAmt1().add(membershipCard._getTotalSaleAmt1()));
		// 消费金额-2
		total.setTotalSaleAmt2(total._getTotalSaleAmt2().add(membershipCard._getTotalSaleAmt2()));

	}

	/**
	 * 计算合计
	 * 
	 * @param voList
	 */
	private void calTotal(List<MembershipCard> voList) {

		MembershipCard total = new MembershipCard();
		total.setOrgName("总计");

		MembershipCard total_Eq_Old = new MembershipCard();
		total_Eq_Old.setOrgName("老EQ+");
		List<String> orgName_Eq_Old = Lists.newArrayList("01D", "03D", "04D", "05D", "07D", "08D", "09D", "10D", "11D");

		MembershipCard total_Eq_New = new MembershipCard();
		total_Eq_New.setOrgName("新EQ+");
		List<String> orgName_Eq_New = Lists.newArrayList("02D", "15D", "16D", "18D", "20D", "22D");

		MembershipCard total_Infancy = new MembershipCard();
		total_Infancy.setOrgName("Infancy");
		// List<String> orgName_Infancy = Lists.newArrayList("06D", "13D",
		// "17D", "19D", "21D", "23D", "24D");

		for (MembershipCard membershipCard : voList) {

			// ==================================================================
			// 总计
			// ==================================================================
			setTotalValue(total, membershipCard);

			// ==================================================================
			// 老EQ+
			// ==================================================================
			if (orgName_Eq_Old.contains(membershipCard.getOrgName())) {
				setTotalValue(total_Eq_Old, membershipCard);
			}
			// ==================================================================
			// 新EQ+
			// ==================================================================
			else if (orgName_Eq_New.contains(membershipCard.getOrgName())) {
				setTotalValue(total_Eq_New, membershipCard);

			}
			// ==================================================================
			// Infancy
			// ==================================================================
			else {
				setTotalValue(total_Infancy, membershipCard);
			}
		}

		// 总计
		voList.add(0, total);
		// 老EQ+
		voList.add(1, total_Eq_Old);
		// 新EQ+
		voList.add(2, total_Eq_New);
		// Infancy
		voList.add(3, total_Infancy);

	}

	/**
	 * 取得消费金额
	 * 
	 * @param voList
	 * @param currentStartOptDate
	 * @param currentEndOptDate
	 */
	private void copyList2Value_TotalConsumeAmtInfo(List<MembershipCard> voList, String currentStartOptDate,
			String currentEndOptDate) {
		Map<String, String> param = Maps.newHashMap();
		param.put("optDateStart", currentStartOptDate + " 00:00:00.000");
		param.put("optDateEnd", currentEndOptDate + " 23:59:59.900");
		List<MembershipCard> list2 = membershipCardMyBatisDao.getTotalConsumeAmtInfoList(param);

		for (MembershipCard vo : voList) {
			int _index = list2.indexOf(vo);
			if (-1 != _index) {
				MembershipCard value = list2.get(_index);
				// 消费金额
				vo.setTotalSaleAmt2(value.get_totalSaleAmt());
			}
		}

	}

	/**
	 * 取得消费金额
	 * 
	 * @param voList
	 * @param preStartOptDate
	 * @param preEndOptDate
	 */
	private void copyList1Value_TotalConsumeAmtInfo(List<MembershipCard> voList, String preStartOptDate, String preEndOptDate) {
		Map<String, String> param = Maps.newHashMap();
		param.put("optDateStart", preStartOptDate + " 00:00:00.000");
		param.put("optDateEnd", preEndOptDate + " 23:59:59.900");
		List<MembershipCard> list1 = membershipCardMyBatisDao.getTotalConsumeAmtInfoList(param);

		for (MembershipCard vo : voList) {
			int _index = list1.indexOf(vo);
			if (-1 != _index) {
				MembershipCard value = list1.get(_index);
				// 消费金额
				vo.setTotalSaleAmt1(value.get_totalSaleAmt());
			}
		}
	}

	/**
	 * 取得金卡消费金额
	 * 
	 * @param voList
	 * @param currentStartOptDate
	 * @param currentEndOptDate
	 */
	private void copyList2Value_ConsumeAmtInfo(List<MembershipCard> voList, String currentStartOptDate, String currentEndOptDate) {
		Map<String, String> param = Maps.newHashMap();
		param.put("optDateStart", currentStartOptDate + " 00:00:00.000");
		param.put("optDateEnd", currentEndOptDate + " 23:59:59.900");
		List<MembershipCard> list2 = membershipCardMyBatisDao.getMembershipCardConsumeAmtInfoList(param);

		for (MembershipCard vo : voList) {
			int _index = list2.indexOf(vo);
			if (-1 != _index) {
				MembershipCard value = list2.get(_index);
				// 金卡消费金额
				vo.setSaleAmt2(value.get_saleAmt());
			}
		}

	}

	/**
	 * 取得金卡消费金额
	 * 
	 * @param voList
	 * @param preStartOptDate
	 * @param preEndOptDate
	 */
	private void copyList1Value_ConsumeAmtInfo(List<MembershipCard> voList, String preStartOptDate, String preEndOptDate) {
		Map<String, String> param = Maps.newHashMap();
		param.put("optDateStart", preStartOptDate + " 00:00:00.000");
		param.put("optDateEnd", preEndOptDate + " 23:59:59.900");
		List<MembershipCard> list1 = membershipCardMyBatisDao.getMembershipCardConsumeAmtInfoList(param);

		for (MembershipCard vo : voList) {
			int _index = list1.indexOf(vo);
			if (-1 != _index) {
				MembershipCard value = list1.get(_index);
				// 金卡消费金额
				vo.setSaleAmt1(value.get_saleAmt());
			}
		}

	}

	/**
	 * 取得消费次数
	 * 
	 * @param voList
	 * @param currentStartOptDate
	 * @param currentEndOptDate
	 */
	private void copyList2Value_TotalConsumeCntInfo(List<MembershipCard> voList, String currentStartOptDate,
			String currentEndOptDate) {
		Map<String, String> param = Maps.newHashMap();
		param.put("optDateStart", currentStartOptDate + " 00:00:00.000");
		param.put("optDateEnd", currentEndOptDate + " 23:59:59.900");
		List<MembershipCard> list2 = membershipCardMyBatisDao.getTotalConsumeCntInfoList(param);

		for (MembershipCard vo : voList) {
			int _index = list2.indexOf(vo);
			if (-1 != _index) {
				MembershipCard value = list2.get(_index);
				// 消费次数
				vo.setTotalConsumeCnt2(value.get_totalConsumeCnt());
			}
		}

	}

	/**
	 * 取得消费次数
	 * 
	 * @param voList
	 * @param preStartOptDate
	 * @param preEndOptDate
	 */
	private void copyList1Value_TotalConsumeCntInfo(List<MembershipCard> voList, String preStartOptDate, String preEndOptDate) {
		Map<String, String> param = Maps.newHashMap();
		param.put("optDateStart", preStartOptDate + " 00:00:00.000");
		param.put("optDateEnd", preEndOptDate + " 23:59:59.900");
		List<MembershipCard> list1 = membershipCardMyBatisDao.getTotalConsumeCntInfoList(param);

		for (MembershipCard vo : voList) {
			int _index = list1.indexOf(vo);
			if (-1 != _index) {
				MembershipCard value = list1.get(_index);
				// 消费次数
				vo.setTotalConsumeCnt1(value.get_totalConsumeCnt());
			}
		}
	}

	/**
	 * 取得金卡消费次数
	 * 
	 * @param voList
	 * @param currentStartOptDate
	 * @param currentEndOptDate
	 */
	private void copyList2Value_ConsumeCntInfo(List<MembershipCard> voList, String currentStartOptDate, String currentEndOptDate) {
		Map<String, String> param = Maps.newHashMap();
		param.put("optDateStart", currentStartOptDate + " 00:00:00.000");
		param.put("optDateEnd", currentEndOptDate + " 23:59:59.900");
		List<MembershipCard> list2 = membershipCardMyBatisDao.getMembershipCardConsumeCntInfoList(param);

		for (MembershipCard vo : voList) {
			int _index = list2.indexOf(vo);
			if (-1 != _index) {
				MembershipCard value = list2.get(_index);
				// 金卡消费次数
				vo.setConsumeCnt2(value.get_consumeCnt());
			}
		}

	}

	/**
	 * 取得金卡消费次数
	 * 
	 * @param voList
	 * @param preStartOptDate
	 * @param preEndOptDate
	 */
	private void copyList1Value_ConsumeCntInfo(List<MembershipCard> voList, String preStartOptDate, String preEndOptDate) {
		Map<String, String> param = Maps.newHashMap();
		param.put("optDateStart", preStartOptDate + " 00:00:00.000");
		param.put("optDateEnd", preEndOptDate + " 23:59:59.900");
		List<MembershipCard> list1 = membershipCardMyBatisDao.getMembershipCardConsumeCntInfoList(param);

		for (MembershipCard vo : voList) {
			int _index = list1.indexOf(vo);
			if (-1 != _index) {
				MembershipCard value = list1.get(_index);
				// 金卡消费次数
				vo.setConsumeCnt1(value.get_consumeCnt());
			}
		}
	}

	/**
	 * 取得金卡充值金额
	 * 
	 * @param voList
	 * @param currentStartOptDate
	 * @param currentEndOptDate
	 */
	private void copyList2Value_RechargeAmtInfo(List<MembershipCard> voList, String currentStartOptDate, String currentEndOptDate) {
		Map<String, String> param = Maps.newHashMap();
		param.put("optDateStart", currentStartOptDate + " 00:00:00.000");
		param.put("optDateEnd", currentEndOptDate + " 23:59:59.900");
		List<MembershipCard> list2 = membershipCardMyBatisDao.getMembershipCardRechargeAmtInfoList(param);

		for (MembershipCard vo : voList) {
			int _index = list2.indexOf(vo);
			if (-1 != _index) {
				MembershipCard value = list2.get(_index);
				// 金卡充值
				vo.setRechargeAmt2(value.get_rechargeAmt());
			}
		}

	}

	/**
	 * 取得金卡充值金额
	 * 
	 * @param voList
	 * @param preStartOptDate
	 * @param preEndOptDate
	 */
	private void copyList1Value_RechargeAmtInfo(List<MembershipCard> voList, String preStartOptDate, String preEndOptDate) {
		Map<String, String> param = Maps.newHashMap();
		param.put("optDateStart", preStartOptDate + " 00:00:00.000");
		param.put("optDateEnd", preEndOptDate + " 23:59:59.900");
		List<MembershipCard> list1 = membershipCardMyBatisDao.getMembershipCardRechargeAmtInfoList(param);

		for (MembershipCard vo : voList) {
			int _index = list1.indexOf(vo);
			if (-1 != _index) {
				MembershipCard value = list1.get(_index);
				// 金卡充值
				vo.setRechargeAmt1(value.get_rechargeAmt());
			}
		}

	}

	/**
	 * 取得金卡返利金额
	 * 
	 * @param voList
	 * @param currentStartOptDate
	 * @param currentEndOptDate
	 */
	private void copyList2Value_RetAmtInfo(List<MembershipCard> voList, String currentStartOptDate, String currentEndOptDate) {
		Map<String, String> param = Maps.newHashMap();
		param.put("optDateStart", currentStartOptDate + " 00:00:00.000");
		param.put("optDateEnd", currentEndOptDate + " 23:59:59.900");
		List<MembershipCard> list2 = membershipCardMyBatisDao.getMembershipCardRetAmtInfoList(param);

		for (MembershipCard vo : voList) {
			int _index = list2.indexOf(vo);
			if (-1 != _index) {
				MembershipCard value = list2.get(_index);
				// 金卡返利
				vo.setRetAmt2(value.get_retAmt());
			}
		}

	}

	/**
	 * 取得金卡返利金额
	 * 
	 * @param voList
	 * @param preStartOptDate
	 * @param preEndOptDate
	 */
	private void copyList1Value_RetAmtInfo(List<MembershipCard> voList, String preStartOptDate, String preEndOptDate) {
		Map<String, String> param = Maps.newHashMap();
		param.put("optDateStart", preStartOptDate + " 00:00:00.000");
		param.put("optDateEnd", preEndOptDate + " 23:59:59.900");
		List<MembershipCard> list1 = membershipCardMyBatisDao.getMembershipCardRetAmtInfoList(param);

		for (MembershipCard vo : voList) {
			int _index = list1.indexOf(vo);
			if (-1 != _index) {
				MembershipCard value = list1.get(_index);
				// 金卡返利
				vo.setRetAmt1(value.get_retAmt());
			}
		}

	}

	/**
	 * 取得金卡发行次数
	 * 
	 * @param voList
	 * @param currentStartOptDate
	 * @param currentEndOptDate
	 */
	private void copyList2Value_IssueInfo(List<MembershipCard> voList, String currentStartOptDate, String currentEndOptDate) {
		Map<String, String> param = Maps.newHashMap();
		param.put("optDateStart", currentStartOptDate + " 00:00:00.000");
		param.put("optDateEnd", currentEndOptDate + " 23:59:59.900");
		List<MembershipCard> list2 = membershipCardMyBatisDao.getMembershipCardIssueInfoList(param);

		for (MembershipCard vo : voList) {
			int _index = list2.indexOf(vo);
			if (-1 != _index) {
				MembershipCard value = list2.get(_index);
				// 金卡发行次数
				vo.setIssueCnt2(value.get_issueCnt());
			}
		}

	}

	/**
	 * 取得金卡发行次数
	 * 
	 * @param voList
	 * @param preStartOptDate
	 * @param preEndOptDate
	 */
	private void copyList1Value_IssueInfo(List<MembershipCard> voList, String preStartOptDate, String preEndOptDate) {
		Map<String, String> param = Maps.newHashMap();
		param.put("optDateStart", preStartOptDate + " 00:00:00.000");
		param.put("optDateEnd", preEndOptDate + " 23:59:59.900");
		List<MembershipCard> list1 = membershipCardMyBatisDao.getMembershipCardIssueInfoList(param);

		for (MembershipCard vo : voList) {
			int _index = list1.indexOf(vo);
			if (-1 != _index) {
				MembershipCard value = list1.get(_index);
				// 金卡发行次数
				vo.setIssueCnt1(value.get_issueCnt());
			}
		}
	}

	/**
	 * @param orgId
	 * @return
	 */
	private List<MembershipCard> initBlankVoList(String orgId) {
		List<MembershipCard> voList = Lists.newArrayList();

		List<Organization> orgList = null;

		if (StringUtils.isBlank(orgId)) {// 全机构
			orgList = orgManager.getSubOrganization();
		} else {
			Organization org = orgManager.getOrganizationByOrgIdInCache(orgId);

			orgList = Lists.newArrayList();
			orgList.add(org);
		}

		for (Organization org : orgList) {
			MembershipCard vo = new MembershipCard();

			// 机构编号
			vo.setOrgId(org.getId());
			// 机构名称
			vo.setOrgName(org.getName());
			// 机构资金编号-百威
			vo.setBwBranchNo(org.getBwBranchNo());

			voList.add(vo);
		}
		return voList;
	}
}
