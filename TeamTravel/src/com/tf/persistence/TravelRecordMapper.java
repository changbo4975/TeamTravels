package com.tf.persistence;

import java.util.List;

import com.tf.domain.TravelRecord;

public interface TravelRecordMapper {
	public TravelRecord select(int no);
	public void update(TravelRecord travelRecord);
	public void insert(TravelRecord travelRecord);
	public List<TravelRecord> list(TravelRecord travelRecord);
	public void delete(int no);
}

