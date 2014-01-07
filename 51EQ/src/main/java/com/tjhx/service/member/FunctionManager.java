package com.tjhx.service.member;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.cache.memcached.SpyMemcachedClient;

import com.tjhx.dao.member.FunctionJpaDao;
import com.tjhx.entity.member.Function;
import com.tjhx.globals.MemcachedObjectType;

@Service
@Transactional(readOnly = true)
public class FunctionManager {
	private static Logger logger = LoggerFactory.getLogger(FunctionManager.class);

	@Resource
	private FunctionJpaDao functionJpaDao;
	@Resource
	private SpyMemcachedClient spyMemcachedClient;

	/**
	 * 取得所有Function信息
	 * 
	 * @return Function信息列表
	 */
	@SuppressWarnings("unchecked")
	public List<Function> getAllFunction() {

		List<Function> _funList = spyMemcachedClient.get(MemcachedObjectType.FUN_LIST.getObjKey());

		if (null == _funList) {
			// 从数据库中取出全量权限信息(List格式)
			_funList = (List<Function>) functionJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "uuid")));
			// 将权限信息Map保存到memcached
			spyMemcachedClient.set(MemcachedObjectType.FUN_LIST.getObjKey(),
					MemcachedObjectType.FUN_LIST.getExpiredTime(), _funList);

			logger.debug("权限信息不在 memcached中,从数据库中取出并放入memcached");
		} else {
			logger.debug("从memcached中取出权限信息");
		}
		return _funList;
	}

}