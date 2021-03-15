package com.shawn.dao;

import java.util.List;

public interface UserDao {

	User getUserByUserName(String username);

	List<String> getRolesByUserName(String username);
	
}
