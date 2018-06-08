package com.tf.persistence;

import com.tf.domain.TravelRoute;

public interface TravelRouteMapper {
	public TravelRoute select(TravelRoute travelRoute);
	public boolean insert(TravelRoute travelRoute);
	public boolean delete(TravelRoute travelRoute);
}
