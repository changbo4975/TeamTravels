package com.tf.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Pinpoint implements Serializable {
	private int no;
	private int travelRecordNo;
	private String email;
	private double latitude;
	private double longitude;
	private int range;
	private int iconNo;
	private List<Photo> photoList;
	
	public Pinpoint() {}
	
	public Pinpoint(Map map) {
		this.no = (int)map.get("no");
		this.latitude = (double)map.get("latitude");
		this.longitude = (double)map.get("longitude");
		this.photoList = new ArrayList<Photo>();
		
		for(int i = 0; i < ((List)map.get("photoList")).size(); i++) {
			Map phMap = (Map)((List)map.get("photoList")).get(i);
			Photo photo = new Photo(phMap);
			photoList.add(photo);
		}
	}
	
	public List<Photo> getPhotoList() {
		return photoList;
	}
	public void setPhotoList(List<Photo> photoList) {
		this.photoList = photoList;
	}
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
