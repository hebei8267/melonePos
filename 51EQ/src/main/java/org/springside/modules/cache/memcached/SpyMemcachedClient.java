/**
 * Copyright (c) 2005-2012 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.springside.modules.cache.memcached;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import net.spy.memcached.MemcachedClient;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;

/**
 * 对SpyMemcached Client的二次封装,提供常用的Get/GetBulk/Set/Delete/Incr/Decr函数的同步与异步操作封装.
 * 
 * 未提供封装的函数可直接调用getClient()取出Spy的原版MemcachedClient来使用.
 * 
 * @author calvin
 */
public class SpyMemcachedClient implements DisposableBean {

	private static Logger logger = LoggerFactory.getLogger(SpyMemcachedClient.class);

	private MemcachedClient memcachedClient;

	private long shutdownTimeout = 2500;

	private long updateTimeout = 2500;

	/**
	 * Get方法, 转换结果类型并屏蔽异常, 仅返回Null.
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		try {
			return (T) memcachedClient.get(key);
		} catch (RuntimeException e) {
			handleException(e, key);
			return null;
		}
	}

	/**
	 * GetBulk方法, 转换结果类型并屏蔽异常.
	 */
	@SuppressWarnings("unchecked")
	public <T> Map<String, T> getBulk(Collection<String> keys) {
		try {
			return (Map<String, T>) memcachedClient.getBulk(keys);
		} catch (RuntimeException e) {
			handleException(e, StringUtils.join(keys, ","));
			return null;
		}
	}

	/**
	 * 异步Set方法, 不考虑执行结果.
	 */
	public void set(String key, int expiredTime, Object value) {
		memcachedClient.set(key, expiredTime, value);
	}

	/**
	 * 安全的Set方法, 保证在updateTimeout秒内返回执行结果, 否则返回false并取消操作.
	 */
	public boolean safeSet(String key, int expiration, Object value) {
		Future<Boolean> future = memcachedClient.set(key, expiration, value);
		try {
			return future.get(updateTimeout, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			future.cancel(false);
		}
		return false;
	}

	/**
	 * 异步 Delete方法, 不考虑执行结果.
	 */
	public void delete(String key) {
		memcachedClient.delete(key);
	}

	/**
	 * 安全的Delete方法, 保证在updateTimeout秒内返回执行结果, 否则返回false并取消操作.
	 */
	public boolean safeDelete(String key) {
		Future<Boolean> future = memcachedClient.delete(key);
		try {
			return future.get(updateTimeout, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			future.cancel(false);
		}
		return false;
	}

	/**
	 * Incr方法.
	 */
	public long incr(String key, int by, long defaultValue) {
		return memcachedClient.incr(key, by, defaultValue);
	}

	/**
	 * Decr方法.
	 */
	public long decr(String key, int by, long defaultValue) {
		return memcachedClient.decr(key, by, defaultValue);
	}

	/**
	 * 异步Incr方法, 不支持默认值, 若key不存在返回-1.
	 */
	public Future<Long> asyncIncr(String key, int by) {

		return memcachedClient.asyncIncr(key, by);
	}

	/**
	 * 异步Decr方法, 不支持默认值, 若key不存在返回-1.
	 */
	public Future<Long> asyncDecr(String key, int by) {
		return memcachedClient.asyncDecr(key, by);
	}

	private void handleException(Exception e, String key) {
		logger.warn("spymemcached client receive an exception with key:" + key, e);
	}

	public void destroy() throws Exception {
		if (memcachedClient != null) {
			memcachedClient.shutdown(shutdownTimeout, TimeUnit.MILLISECONDS);
		}
	}

	/**
	 * Replace an object with the given value (transcoded with the default
	 * transcoder) iff there is already a value for the given key.
	 * 
	 * The exp value is passed along to memcached exactly as given, and will be
	 * processed per the memcached protocol specification:
	 * 
	 * The actual value sent may either be Unix time (number of seconds since
	 * January 1, 1970, as a 32-bit value), or a number of seconds starting from
	 * current time. In the latter case, this number of seconds may not exceed
	 * 60*60*24*30 (number of seconds in 30 days); if the number sent by a
	 * client is larger than that, the server will consider it to be real Unix
	 * time value rather than an offset from current time.
	 * 
	 * @param key - the key under which this object should be added.
	 * @param exp - the expiration of this object
	 * @param o - the object to store
	 * @return a future representing the processing of this operation
	 */
	public Future<Boolean> replace(String key, int exp, Object o) {
		return memcachedClient.replace(key, exp, o);
	}

	public MemcachedClient getMemcachedClient() {
		return memcachedClient;
	}

	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	public void setUpdateTimeout(long updateTimeout) {
		this.updateTimeout = updateTimeout;
	}

	public void setShutdownTimeout(long shutdownTimeout) {
		this.shutdownTimeout = shutdownTimeout;
	}
}