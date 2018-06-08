package com.tf.domain;

import java.io.Serializable;

public class PinpointComment implements Serializable{
	private int no;
	private int pinpointNo;
	private String email;
	private String reply;
	private String pinpointDate;
	
	public PinpointComment() {
		
	}
	
	public PinpointComment(int no, int pinpointNo, String email, String reply, String pinpointDate) {
		super();
		this.no = no;
		this.pinpointNo = pinpointNo;
		this.email = email;
		this.reply = reply;
		this.pinpointDate = pinpointDate;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getPinpointDate() {
		return pinpointDate;
	}
	public void setPinpointDate(String pinpointDate) {
		this.pinpointDate = pinpointDate;
	}
}
