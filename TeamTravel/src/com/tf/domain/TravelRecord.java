package com.tf.domain;

import java.io.Serializable;
import java.util.List;

public class TravelRecord implements Serializable {
	private int no;
	private String email;
	private int range;
	private String nation;
	private String startDate;
	private String endDate;
	private int state;
	private String title;
	private List<Pinpoint> pinpointList;
	
	public List<Pinpoint> getPinpointList() {
		return pinpointList;
	}
	public void setPinpointList(List<Pinpoint> pinpointList) {
		this.pinpointList = pinpointList;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}