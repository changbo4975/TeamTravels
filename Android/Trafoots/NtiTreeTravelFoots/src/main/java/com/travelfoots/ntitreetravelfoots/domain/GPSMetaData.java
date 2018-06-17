package com.travelfoots.ntitreetravelfoots.domain;

import java.io.Serializable;
import java.util.Date;

public class GPSMetaData implements Serializable {
    private double userLat;
    private double userLng;
    private Date userDate;


    public GPSMetaData(){
        super();
    }
    public GPSMetaData(double userLat, double userLng, Date userDate) {
        this.userLat = userLat;
        this.userLng = userLng;
        this.userDate = userDate;
    }

    public double getUserLat() {
        return userLat;
    }

    public void setUserLat(double userLat) {
        this.userLat = userLat;
    }

    public double getUserLng() {
        return userLng;
    }

    public void setUserLng(double userLng) {
        this.userLng = userLng;
    }

    public Date getUserDate() {
        return userDate;
    }

    public void setUserDate(Date userDate) {
        this.userDate = userDate;
    }
}
