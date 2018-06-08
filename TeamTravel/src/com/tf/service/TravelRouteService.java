package com.tf.service;

import com.tf.domain.TravelRoute;

public interface TravelRouteService {
	public TravelRoute select(TravelRoute travelRoute) throws Exception;
	public boolean delete(TravelRoute travelRoute) throws Exception;
}
