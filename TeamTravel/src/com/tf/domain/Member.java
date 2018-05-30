package com.tf.domain;

import java.io.Serializable;

public class Member implements Serializable {
	private String email;
	private String nickname;
	private int age;
	private int gender;
	private String representativePhoto;
	private String selfintroduction;
	private String password;
	private int restrictionCount;
	
	public Member() {
		
	}
	
	public Member(String email, String nickname, int age, int gender, String representativePhoto, String selfintroduction, String password, int restrictionCount) {
		this.email = email;
		this.nickname = nickname;
		this.age = age;
		this.gender = gender;
		this.representativePhoto = representativePhoto;
		this.selfintroduction = selfintroduction;
		this.password = password;
		this.restrictionCount = restrictionCount;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
