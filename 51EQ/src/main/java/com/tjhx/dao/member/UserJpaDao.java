package com.tjhx.dao.member;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.member.User;

public interface UserJpaDao extends CrudRepository<User, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);

	/**
	 * 取得用户信息
	 * 
	 * @param loginName 登录名称
	 * @return 用户信息
	 */
	public User findByLoginName(String loginName);

	/**
	 * 取得督导员信息列表
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Query("select u from User u where u.mngFlg = '1' ")
	public Iterable getMngUser(Sort sort);
}
