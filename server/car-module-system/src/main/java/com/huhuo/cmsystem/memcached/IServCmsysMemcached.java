package com.huhuo.cmsystem.memcached;

import com.huhuo.carservicecore.constant.MemcachedRegion;
import com.huhuo.componentweb.core.IServHcwMemcached;

public interface IServCmsysMemcached extends IServHcwMemcached {

	/**
	 * key不压缩
	 * @see #get(MemcachedRegion, String, boolean, long)
	 */
	<T> T get(MemcachedRegion region, final String key);
	/**
	 * key不压缩
	 * @see #get(MemcachedRegion, String, boolean, long)
	 */
	<T> T get(MemcachedRegion region, String key, long timeout);
	/**
	 * @see #get(MemcachedRegion, String, boolean, long)
	 */
	<T> T get(MemcachedRegion region, final String key, boolean isCompressKey);
	/**
	 * @see {@link IServHcwMemcached#get(String, String, boolean, long)}
	 */
	<T> T get(MemcachedRegion region, String key, boolean isCompressKey, long timeout);
	/**
	 * key不压缩
	 * @see #set(MemcachedRegion, String, Object, boolean, int, long)
	 */
	boolean set(MemcachedRegion region, String key, Object value);
	/**
	 * @see #set(MemcachedRegion, String, Object, boolean, int, long)
	 */
	boolean set(MemcachedRegion region, String key, Object value, boolean isCompressKey);
	/**
	 * key不压缩
	 * @see #set(MemcachedRegion, String, Object, boolean, int, long)
	 */
	boolean set(MemcachedRegion region, String key, Object value, int exp, long timeout);
	/**
	 * @see {@link IServHcwMemcached#set(String, String, Object, boolean, int, long)}
	 */
	boolean set(MemcachedRegion region, String key, Object value, boolean isCompressKey, int exp, long timeout);
	/**
	 * 删除缓存
	 * @param region
	 * @param key
	 * @return
	 */
	boolean delete(MemcachedRegion region, final String key);
	/**
	 * 删除缓存
	 * @param region
	 * @param key
	 * @return
	 */
	boolean delete(MemcachedRegion region, final String key, boolean isCompressKey);
}
