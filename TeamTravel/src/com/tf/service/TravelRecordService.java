package com.tf.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tf.domain.TravelRecord;

public interface TravelRecordService {
	public List<TravelRecord> list(TravelRecord travelRecord) throws Exception;
	public TravelRecord view(int no) throws Exception;
	public boolean add(TravelRecord travelRecord) throws Exception;
	public boolean edit(TravelRecord travelRecord) throws Exception;
	public boolean delete(int no) throws Exception;
}
