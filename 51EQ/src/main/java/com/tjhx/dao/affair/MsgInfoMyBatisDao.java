package com.tjhx.dao.affair;

import java.util.List;

import com.tjhx.entity.affair.MsgInfo;

public interface MsgInfoMyBatisDao {

	/**
	 * 取得未读状态的信息列表
	 * 
	 * @param msgInfo
	 * @return
	 */
	public List<MsgInfo> getMsgInfoList_UnreadStatus(String loginName);

	public List<MsgInfo> getMsgInfoList(MsgInfo msgInfo);

}
