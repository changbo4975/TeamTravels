package com.tf.persistence;

import com.tf.domain.PinpointComment;
import com.tf.domain.TravelRecordComment;

public interface TravelRecordCommentMapper {
	public TravelRecordComment select(TravelRecordComment travelRecordComment);
	public boolean insert(TravelRecordComment travelRecordComment);
	public boolean update(TravelRecordComment travelRecordComment);
	public boolean delete(TravelRecordComment travelRecordComment);
}
