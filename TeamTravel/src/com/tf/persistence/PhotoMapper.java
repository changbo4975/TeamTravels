package com.tf.persistence;

import java.util.List;

import com.tf.domain.Photo;

public interface PhotoMapper {
	public List<Photo> list(int pinpointNo);
	public Photo select(Photo photo);
	public boolean insert(Photo photo);
	public boolean update(Photo photo);
	public boolean delete(int no);
}