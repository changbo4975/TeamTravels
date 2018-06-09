package com.travelfoots.ntitreetravelfoots.domain;

import com.google.gson.internal.LinkedTreeMap;

import java.io.Serializable;
import java.util.List;
//TODO: 여행기록객체
public class TravelRecord implements Serializable {
	private int no;
	private String email;
	private int range;
	private String nation;
	private String startDate;
	private String endDate;
	private String titile;
	private int state;
	private List<Pinpoint> pinpointList;
	private List<GPSMetaData> gpsMetaDataList;

	public TravelRecord() {}

	public TravelRecord(LinkedTreeMap map) {
		this.email = (String)map.get("email");
		this.range = (int) ((double)map.get("range"));
		this.nation = (String)map.get("nation");
		this.startDate = (String)map.get("startDate");
		this.endDate = (String)map.get("endDate");
		this.titile = (String)map.get("titile");
		this.state = (int) ((double)map.get("state"));

	}


	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public List<GPSMetaData> getGpsMetaDataList() {
		return gpsMetaDataList;
	}
	public void setGpsMetaDataList(List<GPSMetaData> gpsMetaDataList) {
		this.gpsMetaDataList = gpsMetaDataList;
	}
	public String getTitile() {	return titile; }
	public void setTitile(String titile) { this.titile = titile; }
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
	public List<Pinpoint> getPinpointList() {
		return pinpointList;
	}
	public void setPinpointList(List<Pinpoint> pinpointList) {
		this.pinpointList = pinpointList;
	}
}
