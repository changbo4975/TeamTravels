package com.tf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tf.domain.Pinpoint;
import com.tf.persistence.PinpointMapper;

@Service
public class PinpointServiceImpl implements PinpointService {
	@Autowired
	private PinpointMapper pinpointMapper;
	
	@Override
	public List<Pinpoint> list(Pinpoint pinpoint) throws Exception {
		Pinpoint listPinpoint = new Pinpoint();

		listPinpoint.setEmail(pinpoint.getEmail());

		List<Pinpoint> listNoticeBoard = this.pinpointMapper.list(listPinpoint);
		return listNoticeBoard;
	}

	@Override
	public Pinpoint view(Pinpoint pinpoint) throws Exception {
		
		return pinpointMapper.select(pinpoint);
	}

	@Override
	public boolean edit(Pinpoint pinpoint) throws Exception {
		
		pinpointMapper.update(pinpoint);
		
		return false;
	}

	@Override
	public boolean delete(Pinpoint pinpoint) throws Exception {
		pinpointMapper.delete(pinpoint);
		
		return false;
	}
}
