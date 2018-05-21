package com.tf.service;

import java.util.List;

import com.tf.domain.Photo;

public interface PhotoService {	
	public List<Photo> list(Photo photo) throws Exception;
	public Photo view(Photo photo) throws Exception;
	public boolean add(Photo photo) throws Exception;
	public boolean edit(Photo photo) throws Exception;
	public boolean delete(Photo photo) throws Exception;
}
