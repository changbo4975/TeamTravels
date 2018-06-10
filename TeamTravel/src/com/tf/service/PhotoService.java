package com.tf.service;

import com.tf.domain.Photo;

public interface PhotoService {	
	public Photo view(Photo photo) throws Exception;
	public boolean add(Photo photo) throws Exception;
	public boolean edit(Photo photo) throws Exception;
	public boolean delete(int no) throws Exception;
}
