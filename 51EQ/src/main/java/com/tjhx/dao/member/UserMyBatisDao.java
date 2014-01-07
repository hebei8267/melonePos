package com.tjhx.dao.member;

import java.util.List;

import com.tjhx.entity.member.User;

public interface UserMyBatisDao {

	public List<User> getAllUserList();
	
	public List<User> getUserListByOrgUuid(int orgUuid);

}
