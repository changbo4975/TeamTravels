package com.tf.domain;

import java.io.Serializable;

public class Manager implements Serializable {
	private String email;
	private String nickName;
	private String password;
	
	public Manager(){
		
	}
	
	public Manager(String email, String nickName, String password) {
		super();
		this.email = email;
		this.nickName = nickName;
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
	
