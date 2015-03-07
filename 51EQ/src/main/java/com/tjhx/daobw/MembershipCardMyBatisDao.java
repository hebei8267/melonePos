/**
 * 
 */
package com.tjhx.daobw;

import java.util.List;
import java.util.Map;

import com.tjhx.entity.bw.MembershipCard;

public interface MembershipCardMyBatisDao {

	/**
	 * 取得金卡发行次数
	 * 
	 * @param param
	 * @return
	 */
	public List<MembershipCard> getMembershipCardIssueInfoList(Map<String, String> param);

	/**
	 * 取得金卡返利金额
	 * 
	 * @param param
	 * @return
	 */
	public List<MembershipCard> getMembershipCardRetAmtInfoList(Map<String, String> param);

	/**
	 * 取得金卡充值金额
	 * 
	 * @param param
	 * @return
	 */
	public List<MembershipCard> getMembershipCardRechargeAmtInfoList(Map<String, String> param);

	/**
	 * 取得金卡消费次数
	 * 
	 * @param param
	 * @return
	 */
	public List<MembershipCard> getMembershipCardConsumeCntInfoList(Map<String, String> param);

	/**
	 * 取得消费次数
	 * 
	 * @param param
	 * @return
	 */
	public List<MembershipCard> getTotalConsumeCntInfoList(Map<String, String> param);

	/**
	 * 取得金卡消费金额
	 * 
	 * @param param
	 * @return
	 */
	public List<MembershipCard> getMembershipCardConsumeAmtInfoList(Map<String, String> param);

	/**
	 * 取得消费金额
	 * 
	 * @param param
	 * @return
	 */
	public List<MembershipCard> getTotalConsumeAmtInfoList(Map<String, String> param);

	/**
	 * 取得金卡余额
	 * 
	 * @return
	 */
	public List<MembershipCard> getMembershipCardBalanceInfo();
}
