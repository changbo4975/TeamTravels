package com.tf.service;

import java.util.List;

import javax.annotation.Resource;

import com.tf.domain.Photo;
import com.tf.domain.Pinpoint;
import com.tf.domain.TravelRecord;
import com.tf.persistence.PhotoMapper;
import com.tf.persistence.PinpointMapper;
import com.tf.persistence.TravelRecordMapper;

public class TravelRecordServiceImp implements TravelRecordService {
	@Resource
	private TravelRecordMapper travelRecordMapper;
	@Resource
	private PinpointMapper pinpointMapper;
	@Resource
	private PhotoMapper photoMapper;

	@Override
	public List<TravelRecord> list(TravelRecord travelRecord) throws Exception {
		return travelRecordMapper.list(travelRecord);
	}

	@Override
	public TravelRecord view(int no) throws Exception {
		TravelRecord travelRecord = new TravelRecord();
		
		travelRecord = travelRecordMapper.select(no);
		List<Pinpoint> pinpointList = null;
		
		pinpointList = pinpointMapper.list(travelRecord.getNo());
		
		for(Pinpoint p : pinpointList) {
			p.setPhotoList(photoMapper.list(p.getNo()));
		}
		
		travelRecord.setPinpointList(pinpointList);
		
		return travelRecord;
	}

	// 오류체크 안함
	@Override
	public boolean add(TravelRecord travelRecord) throws Exception {
		travelRecordMapper.insert(travelRecord);
		
		for(Pinpoint pinpoint : travelRecord.getPinpointList()) {
			pinpointMapper.insert(pinpoint);
			for(Photo photo : pinpoint.getPhotoList()) {
				photoMapper.insert(photo);
			}
		}
		
		return false;
	}

	@Override
	public boolean edit(TravelRecord travelRecord) throws Exception {
		travelRecordMapper.update(travelRecord);
		return false;
	}

	@Override
	public boolean delete(int no) throws Exception {
		travelRecordMapper.delete(no);
		return false;
	}

}
