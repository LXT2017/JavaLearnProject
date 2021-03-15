package com.shawn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.shawn.dao.User;
import com.shawn.dao.UserDao;

@Component
public class UserDaoImpl implements UserDao{

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public User getUserByUserName(String username) {
		String sql = "select username,password from users where username = ?";
		List<User> list = jdbcTemplate.query(sql, new String[]{username}, new RowMapper<User>(){

			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				return user;
			}
		});
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}
	
	public List<String> getRolesByUserName(String username) {
		
		String sql = "select role_name from user_role where user_name = ?";
		return jdbcTemplate.query(sql, new String[]{username}, new RowMapper<String>(){
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("role_name");
			}
		});
	}
}
