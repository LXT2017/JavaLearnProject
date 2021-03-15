package com.shawn.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import com.shawn.util.JeditsUtil;

@Component
public class RedisSessionDao extends AbstractSessionDAO{

	@Resource
	private JeditsUtil jedisUtil;
	
	private final String SHIRO = "imooc-session";
	
	private byte[] getkey(String key) {
		return (SHIRO+key).getBytes();
	}
	
	private void saveSession(Session session) {
		if(session != null && session.getId()!= null) {
		byte[] key = getkey(session.getId().toString());
		byte[] value = SerializationUtils.serialize(session);
		jedisUtil.set(key,value);
		jedisUtil.expire(key,600);
		}
	}
	@Override
	public void update(Session session) throws UnknownSessionException {
		// TODO Auto-generated method stub
		saveSession(session);
	}

	@Override
	public void delete(Session session) {
		// TODO Auto-generated method stub
		if(session == null ||session.getId() == null) {
			return;
		}
		byte[] key = getkey(session.getId().toString());
		jedisUtil.del(key);
	}

	@Override
	public Collection<Session> getActiveSessions() {
		// TODO Auto-generated method stub
		Set<byte[]> keys = jedisUtil.keys(SHIRO);
		Set<Session> sessions = new HashSet<Session>();
		if(CollectionUtils.isEmpty(keys)) {
			return sessions;
		}
		for(byte[] key: keys) {
			Session session = (Session) SerializationUtils.deserialize(jedisUtil.get(key));
			sessions.add(session);
		}
		return sessions;
	}

	@Override
	protected Serializable doCreate(Session session) {
		System.out.println("create");
		Serializable sessionid = generateSessionId(session);
		assignSessionId(session, sessionid);
		saveSession(session);
		return sessionid;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		// TODO Auto-generated method stub
		System.out.println("read");
		if(sessionId == null) {
			return null;
		}
		byte[] key = getkey(sessionId.toString());
		byte[] value = jedisUtil.get(key);
		return (Session) SerializationUtils.deserialize(value);
	}

}
