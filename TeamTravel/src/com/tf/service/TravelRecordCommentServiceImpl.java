package com.tf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tf.domain.TravelRecordComment;
import com.tf.persistence.TravelRecordCommentMapper;

@Service
public class TravelRecordCommentServiceImpl implements TravelRecordCommentService{
	@Autowired
	private TravelRecordCommentMapper travelRecordCommentMapper;
	
	@Override
	public boolean add(TravelRecordComment travelRecordComment) throws Exception {
		
		travelRecordCommentMapper.insert(travelRecordComment);
		
		return false;
	}

	@Override
	public boolean edit(TravelRecordComment travelRecordComment) throws Exception {
		travelRecordCommentMapper.update(travelRecordComment);
		
		return false;
	}

	@Override
	public boolean remove(TravelRecordComment travelRecordComment) throws Exception {
		travelRecordCommentMapper.delete(travelRecordComment);
		
		return false;
	}

	@Override
	public TravelRecordComment view(TravelRecordComment travelRecordComment) throws Exception {
		return travelRecordCommentMapper.select(travelRecordComment);
	}

}
