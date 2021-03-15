package com.shawn.util;

import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
@Component
public class JeditsUtil {
	
	@Autowired
	private JedisPool jedisPool;

	private Jedis getResource() {
		return jedisPool.getResource();
	}
	public byte[] set(byte[] key, byte[] value) {
		// TODO Auto-generated method stub
		Jedis jedis = getResource();
		
		try {
			jedis.set(key, value);
			return value;
		} finally {
			jedis.close();
		}
	}
	public void expire(byte[] key, int i) {
		// TODO Auto-generated method stub
				Jedis jedis = getResource();
				
				try {
					jedis.expire(key, i);
				} finally {
					jedis.close();
				}
	}
	public byte[] get(byte[] key) {
		// TODO Auto-generated method stub
		Jedis jedis = getResource();
		
		try {
			return jedis.get(key);
		} finally {
			jedis.close();
		}
	}
	public void del(byte[] key) {
		// TODO Auto-generated method stub
		Jedis jedis = getResource();
		
		try {
			jedis.del(key);
		} finally {
			jedis.close();
		}
	}
	public Set<byte[]> keys(String sHIRO) {
		// TODO Auto-generated method stub
		Jedis jedis = getResource();
		
		try {
			return jedis.keys((sHIRO +"*").getBytes());
		} finally {
			jedis.close();
		}
	}
	
}
