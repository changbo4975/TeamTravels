package com.travelfoots.ntitreetravelfoots.domain;

import java.io.Serializable;

public class Member implements Serializable {
	private String email;
	private String nickName;
	private int age;
	private int gender;
	private String representativePhoto;
	private String introduction;
	private int restrictionCount;
	private String password;

	public Member() {
		email = null;
		nickName = null;
		age = -1;
		gender = -1;
		representativePhoto = null;
		introduction = " ";
		restrictionCount = -1;
		password = null;
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
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
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
