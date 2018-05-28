package com.tf.persistence;

import java.sql.Connection;
import java.util.List;

import com.tf.domain.TravelRecord;

public class TravelRecordDAO {
	Connection connection;
	
	public int update(String qurey) {
		return 0;
	}
	
	public List<TravelRecord> select(String qurey) {
		
		return null;
	}
	
	public TravelRecord getRow(TravelRecord travelRecord) {
		return null;
	}
	
	public List<TravelRecord> getList(TravelRecord travelRecord) {
		return null;
	}
	
	public int insert(TravelRecord travelRecord) {
		return 0;
	}
	
	public int edit(TravelRecord travelRecord) {
		return 0;
	}
	
	public int delete(TravelRecord travelRecord) {
		return 0;
	}
}
