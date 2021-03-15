package com.shawn.chche;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import com.shawn.util.JeditsUtil;
@Component
public class RedisCache<K,V> implements Cache<K,V>{
	
	private final String cache_prefix = "shawn-cache";
	@Resource
	private JeditsUtil jeditsUtil;
	private byte[] getKey(K k) {
		// TODO Auto-generated method stub
		if(k instanceof String) {
			return (cache_prefix + k).getBytes();
		}
		return SerializationUtils.serialize(k);
	}
	@Override
	public void clear() throws CacheException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public V get(K k) throws CacheException {
		System.out.println("从redis获取数据");
		byte[] value = jeditsUtil.get(getKey(k));
		if(value != null) {
			return (V) SerializationUtils.deserialize(value);
		}
		return null;
	}

	@Override
	public Set<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V put(K arg0, V arg1) throws CacheException {
		byte[] key = getKey(arg0);
		byte[] value = SerializationUtils.serialize(arg1);
		jeditsUtil.set(key, value);
		jeditsUtil.expire(key, 600);
		return arg1;
	}

	@Override
	public V remove(K arg0) throws CacheException {
		byte[] key = getKey(arg0);
		byte[] value = jeditsUtil.get(key);
		jeditsUtil.del(key);
		if(value != null) {
			return (V) SerializationUtils.deserialize(value);
		}
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

}
