package com.shawn.dao;

public class User {
	
	private String username;
	private String password;
	private Boolean rememberMe;
	
	
	public Boolean getRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [usernameString=" + username + ", passwordString=" + password + "]";
	}
	
	
}
