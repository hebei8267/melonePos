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
}
