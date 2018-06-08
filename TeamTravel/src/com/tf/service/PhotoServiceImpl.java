package com.tf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tf.domain.Photo;
import com.tf.persistence.PhotoMapper;

@Service
public class PhotoServiceImpl implements PhotoService {
	@Autowired
	private PhotoMapper photoMapper;
	
	@Override
	public Photo view(Photo photo) throws Exception {
		return photoMapper.select(photo);
	}

	@Override
	public boolean add(Photo photo) throws Exception {
		photoMapper.insert(photo);
		
		return false;
	}

	@Override
	public boolean edit(Photo photo) throws Exception {
		photoMapper.update(photo);
		
		return false;
	}

	@Override
	public boolean delete(Photo photo) throws Exception {
		photoMapper.delete(photo);
		
		return false;
	}

}
