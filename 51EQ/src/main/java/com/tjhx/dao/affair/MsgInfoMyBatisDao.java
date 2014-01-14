package com.tjhx.dao.affair;

import java.util.List;

import com.tjhx.entity.affair.MsgInfo;

public interface MsgInfoMyBatisDao {

	/**
	 * 取得 公告/消息 信息列表（根据用户编号取得### 未读状态+已读状态>=4）
	 * 
	 * @param msgInfo
	 * @return
	 */
	public List<MsgInfo> getMsgInfoList_UnreadStatus(String loginName);

	public List<MsgInfo> getMsgInfoList(MsgInfo msgInfo);

}
