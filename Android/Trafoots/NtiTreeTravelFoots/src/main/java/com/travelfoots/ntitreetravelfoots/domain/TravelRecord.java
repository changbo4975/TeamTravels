package com.travelfoots.ntitreetravelfoots.domain;

import java.io.Serializable;
import java.util.List;
//TODO: 여행기록객체
public class TravelRecord implements Serializable {
	private int no;
	private String email;
	private String nation;
	private String startDate;
	private String endDate;
	private int state;
	private List<Pinpoint> pinpoints;
	private List<GPSMetaData> gpsMetaDataList;

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
	public List<Pinpoint> getPinpoints() {
		return pinpoints;
	}
	public void setPinpoints(List<Pinpoint> pinpoints) {
		this.pinpoints = pinpoints;
	}
}
