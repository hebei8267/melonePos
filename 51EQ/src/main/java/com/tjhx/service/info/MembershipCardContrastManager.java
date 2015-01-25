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
		return voList;
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
