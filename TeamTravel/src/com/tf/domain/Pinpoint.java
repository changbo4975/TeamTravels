package com.tf.domain;

import java.io.Serializable;

public class Pinpoint implements Serializable {
	private int no;
	private int travelRecordNo;
	private String email;
	private double latitude;
	private double longitude;
	private int range;
	private int iconNo;
	
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
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public int getIconNo() {
		return iconNo;
	}
	public void setIconNo(int iconNo) {
		this.iconNo = iconNo;
	}
}
