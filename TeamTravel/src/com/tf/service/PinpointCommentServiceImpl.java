package com.tf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tf.domain.PinpointComment;
import com.tf.persistence.PinpointCommentMapper;

@Service
public class PinpointCommentServiceImpl implements PinpointCommentService{
	@Autowired
	private PinpointCommentMapper pinpointCommentMapper;
	
	@Override
	public boolean add(PinpointComment pinpointComment) throws Exception {
		pinpointCommentMapper.insert(pinpointComment);
		
		return false;
	}

	@Override
	public boolean edit(PinpointComment pinpointComment) throws Exception {
		pinpointCommentMapper.update(pinpointComment);
		
		return false;
	}

	@Override
	public boolean remove(PinpointComment pinpointComment) throws Exception {
		pinpointCommentMapper.delete(pinpointComment);
		
		return false;
	}

	@Override
	public PinpointComment view(PinpointComment pinpointComment) throws Exception {
		return pinpointCommentMapper.select(pinpointComment);
	}

}
