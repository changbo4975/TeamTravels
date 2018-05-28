package com.tf.domain;

import java.io.Serializable;

public class Content implements Serializable {
	int no;
	int pinpointNo;
	String content;
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
