package com.tf.service;

import java.util.List;

import com.tf.domain.Pinpoint;

public interface PinpointService {
	public List<Pinpoint> list(int travelRecordNo) throws Exception;
	public Pinpoint view(Pinpoint pinpoint) throws Exception;
	public boolean edit(Pinpoint pinpoint) throws Exception;
	public boolean delete(int no) throws Exception;
}
