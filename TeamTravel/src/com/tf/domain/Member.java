package com.tf.domain;

import java.io.Serializable;

public class Member implements Serializable {
	private String email;
	private String nickName;
	private int age;
	private int gender;
	private String representativePhoto;
	private String selfintroduction;
	private String password;
	private int restrictionCount;
	
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getRepresentativePhoto() {
		return representativePhoto;
	}
	public void setRepresentativePhoto(String representativePhoto) {
		this.representativePhoto = representativePhoto;
	}
	public String getSelfintroduction() {
		return selfintroduction;
	}
	public void setSelfintroduction(String selfintroduction) {
		this.selfintroduction = selfintroduction;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRestrictionCount() {
		return restrictionCount;
	}
	public void setRestrictionCount(int restrictionCount) {
		this.restrictionCount = restrictionCount;
	}
	
}
