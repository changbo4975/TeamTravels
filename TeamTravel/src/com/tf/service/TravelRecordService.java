package com.tf.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tf.domain.TravelRecord;

public interface TravelRecordService {
	public List<TravelRecord> list(TravelRecord travelRecord) throws Exception;
	public TravelRecord view(TravelRecord travelRecord) throws Exception;
	public boolean add(MultipartHttpServletRequest req) throws Exception;
	public boolean edit(TravelRecord travelRecord) throws Exception;
	public boolean delete(TravelRecord travelRecord) throws Exception;
}
