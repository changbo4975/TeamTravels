package com.tf.service;

import com.tf.domain.TravelRecordComment;

public interface TravelRecordCommentService {
	public boolean add(TravelRecordComment travelRecordComment)throws Exception;
	public boolean edit(TravelRecordComment travelRecordComment) throws Exception;
	public boolean remove(TravelRecordComment travelRecordComment) throws Exception;
	public TravelRecordComment view(TravelRecordComment travelRecordComment) throws Exception;
}
