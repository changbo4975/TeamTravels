package com.tf.persistence;

import com.tf.domain.Photo;

public interface PhotoMapper {
	public Photo select(Photo photo);
	public boolean insert(Photo photo);
	public boolean update(Photo photo);
	public boolean delete(Photo photo);
}
