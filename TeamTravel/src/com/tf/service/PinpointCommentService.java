package com.tf.service;

import com.tf.domain.PinpointComment;

public interface PinpointCommentService {
	public boolean add(PinpointComment pinpointComment)throws Exception;
	public boolean edit(PinpointComment pinpointComment) throws Exception;
	public boolean remove(PinpointComment pinpointComment) throws Exception;
	public PinpointComment view(PinpointComment pinpointComment) throws Exception;
}
