package com.travelfoots.ntitreetravelfoots.domain;

import java.io.Serializable;
import java.util.List;

public class Pinpoint implements Serializable {
	private int no;
	private double latitude;
	private double longitude;
	private List<String> filePaths;



	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
	public List<String> getFilePaths() {
		return filePaths;
	}
	public void setFilePaths(List<String> filePaths) {
		this.filePaths = filePaths;
	}
}
