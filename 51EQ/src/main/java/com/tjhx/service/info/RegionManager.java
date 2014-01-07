package com.tjhx.service.info;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.cache.memcached.SpyMemcachedClient;

import com.tjhx.dao.info.RegionJpaDao;
import com.tjhx.entity.info.Region;
import com.tjhx.globals.MemcachedObjectType;

@Service
@Transactional(readOnly = true)
public class RegionManager {
	private static Logger logger = LoggerFactory.getLogger(RegionManager.class);
	@Resource
	private RegionJpaDao regionJpaDao;
	@Resource
	private SpyMemcachedClient spyMemcachedClient;

	/**
	 * 取得所有区域信息
	 * 
	 * @return 区域信息列表
	 */
	@SuppressWarnings("unchecked")
	public List<Region> getAllRegion() {
		List<Region> _regionList = spyMemcachedClient.get(MemcachedObjectType.REGION_LIST.getObjKey());

		if (null == _regionList) {
			// 从数据库中取出全量区域信息(List格式)
			_regionList = (List<Region>) regionJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "uuid")));
			// 将银行信息List保存到memcached
			spyMemcachedClient.set(MemcachedObjectType.REGION_LIST.getObjKey(),
					MemcachedObjectType.REGION_LIST.getExpiredTime(), _regionList);

			logger.debug("区域信息不在 memcached中,从数据库中取出并放入memcached");
		} else {
			logger.debug("从memcached中取出区域信息");
		}

		return _regionList;
	}

}