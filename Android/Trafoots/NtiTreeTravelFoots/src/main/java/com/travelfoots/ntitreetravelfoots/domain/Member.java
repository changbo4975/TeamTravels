package com.travelfoots.ntitreetravelfoots.domain;

import com.google.gson.internal.LinkedTreeMap;

import java.io.Serializable;

public class Member implements Serializable {
	private String email;
	private String nickname;
	private int age;
	private char gender;
	private String representativePhoto;
	private String selfintroduction;
	private int restrictionCount;
	private String password;

	public Member(LinkedTreeMap linkedTreeMap) {
		this.email = (String)linkedTreeMap.get("email");
		this.nickname = (String)linkedTreeMap.get("nickname");
		this.age = (int)((double)linkedTreeMap.get("age"));
		this.gender = (char)linkedTreeMap.get("gender");
		this.representativePhoto = (String)linkedTreeMap.get("representativePhoto");
		this.selfintroduction = (String)linkedTreeMap.get("selfintroduction");
		this.restrictionCount = (int)((double)linkedTreeMap.get("restrictionCount"));
		this.password = (String)linkedTreeMap.get("password");
	}

	public Member() {
		email = "";
		nickname = "";
		age = -1;
		gender = ' ';
		representativePhoto = "";
		selfintroduction = "";
		restrictionCount = -1;
		password = "";
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
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
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
