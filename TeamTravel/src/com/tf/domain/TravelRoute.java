package com.tf.domain;

import java.io.Serializable;

public class TravelRoute implements Serializable{
	private int no;
	private int travelRecordNo;
	private int latitude;
	private int longitude;
	private int count;
	
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
	public int getLatitude() {
		return latitude;
	}
	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}
	public int getLongitude() {
		return longitude;
	}
	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
