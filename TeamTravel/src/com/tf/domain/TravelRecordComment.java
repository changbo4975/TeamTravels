package com.tf.domain;

import java.io.Serializable;

public class TravelRecordComment implements Serializable {
	private int no;
	private int travelRecordNo;
	private String email;
	private String reply;
	private String recordDate;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getTravelRecordNo() {
		return travelRecordNo;
	}
	public void setTravelRecordNo(int travelRecordNo) {
		this.travelRecordNo = travelRecordNo;
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
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
}
