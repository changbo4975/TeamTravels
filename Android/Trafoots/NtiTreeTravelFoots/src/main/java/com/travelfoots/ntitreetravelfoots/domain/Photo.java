package com.travelfoots.ntitreetravelfoots.domain;

import java.io.Serializable;

public class Photo implements Serializable {
	int no;
	int pinpointNo;
	String uri;
	String filepath;

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getPinpointNo() {
		return pinpointNo;
	}
	public void setPinpointNo(int pinpointNo) {
		this.pinpointNo = pinpointNo;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
}
