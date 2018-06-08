package com.tf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tf.domain.TravelRoute;
import com.tf.persistence.TravelRouteMapper;

@Service
public class TravelRouteServiceImpl implements TravelRouteService{
	@Autowired
	private TravelRouteMapper travelRouteMapper;
	
	@Override
	public TravelRoute select(TravelRoute travelRoute) throws Exception {
		
		return travelRouteMapper.select(travelRoute);
	}

	@Override
	public boolean delete(TravelRoute travelRoute) throws Exception {
		this.travelRouteMapper.delete(travelRoute);
		
		return false;
	}

}
