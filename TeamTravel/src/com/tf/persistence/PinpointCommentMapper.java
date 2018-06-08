package com.tf.persistence;

import com.tf.domain.PinpointComment;

public interface PinpointCommentMapper {
	public PinpointComment select(PinpointComment pinpointComment);
	public boolean insert(PinpointComment pinpointComment);
	public boolean update(PinpointComment pinpointComment);
	public boolean delete(PinpointComment pinpointComment);
}
