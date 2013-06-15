package com.huhuo.cmsystem.memcached;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huhuo.carservicecore.constant.MemcachedRegion;
import com.huhuo.componentweb.core.IServHcwMemcached;
import com.huhuo.componentweb.core.ServHcwMemcached;

@Service("cmsystemServCmsysMemcached")
public class ServCmsysMemcached extends ServHcwMemcached implements
		IServCmsysMemcached {

	@Resource(name = "componentwebMemcachedService")
	private IServHcwMemcached iServHcwMemcached;

	@Override
	public <T> T get(MemcachedRegion region, String key) {
		return iServHcwMemcached.get(region.getRegion(), key);
	}

	@Override
	public <T> T get(MemcachedRegion region, String key, long timeout) {
		return iServHcwMemcached.get(region.getRegion(), key, timeout);
	}

	@Override
	public <T> T get(MemcachedRegion region, String key, boolean isCompressKey) {
		return iServHcwMemcached.get(region.getRegion(), key, isCompressKey);
	}

	@Override
	public <T> T get(MemcachedRegion region, String key, boolean isCompressKey,
			long timeout) {
		return iServHcwMemcached.get(region.getRegion(), key, isCompressKey, timeout);
	}

	@Override
	public boolean set(MemcachedRegion region, String key, Object value) {
		return iServHcwMemcached.set(region.getRegion(), key, value);
	}

	@Override
	public boolean set(MemcachedRegion region, String key, Object value,
			boolean isCompressKey) {
		return iServHcwMemcached.set(region.getRegion(), key, value, isCompressKey);
	}

	@Override
	public boolean set(MemcachedRegion region, String key, Object value,
			int exp, long timeout) {
		return iServHcwMemcached.set(region.getRegion(), key, value, exp, timeout);
	}

	@Override
	public boolean set(MemcachedRegion region, String key, Object value,
			boolean isCompressKey, int exp, long timeout) {
		return iServHcwMemcached.set(region.getRegion(), key, value, isCompressKey, exp, timeout);
	}

	@Override
	public boolean delete(MemcachedRegion region, String key) {
		return iServHcwMemcached.delete(region.getRegion(), key);
	}

	@Override
	public boolean delete(MemcachedRegion region, String key,
			boolean isCompressKey) {
		return iServHcwMemcached.delete(region.getRegion(), key, isCompressKey);
	}
	
	
}
