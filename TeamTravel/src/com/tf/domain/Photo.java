package com.tf.domain;

import java.io.Serializable;
import java.util.Map;

public class Photo implements Serializable {
	int no;
	int pinpointNo;
	String uri;
	String name;
	
	public Photo() {}
	
	public Photo(Map map) {
		this.no = (int)map.get("no");
		this.pinpointNo = (int)map.get("pinpointNo");
		this.uri = (String)map.get("url");
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
