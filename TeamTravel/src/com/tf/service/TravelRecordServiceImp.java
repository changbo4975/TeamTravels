package com.tf.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tf.domain.Photo;
import com.tf.domain.Pinpoint;
import com.tf.domain.TravelRecord;
import com.tf.persistence.PhotoMapper;
import com.tf.persistence.PinpointMapper;
import com.tf.persistence.TravelRecordMapper;
import com.tf.util.SaveLoad;

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
	public TravelRecord view(TravelRecord travelRecord) throws Exception {
		return travelRecordMapper.select(travelRecord);
	}

	// 오류체크 안함
	@Override
	public boolean add(MultipartHttpServletRequest req) throws Exception {
		TravelRecord travelRecord = new TravelRecord();
		SaveLoad saveLoad = new SaveLoad();
		
		travelRecord.setNo(Integer.parseInt(req.getParameter("travelRecordNo")));
		travelRecord.setEmail(req.getParameter("email"));
		travelRecord.setStartDate(req.getParameter("startDate"));
		travelRecord.setEndDate(req.getParameter("endDate"));
		
		String str = null;
		for(int cnt = 1; (str = req.getParameter("pinpointNo" + cnt)) != null; cnt++) {
			Pinpoint pinpoint = new Pinpoint();
			pinpoint.setNo(Integer.parseInt(str));
			pinpoint.setEmail(travelRecord.getEmail());
			pinpoint.setLatitude(Double.parseDouble(req.getParameter("latitude" + cnt)));
			pinpoint.setLongitude(Double.parseDouble(req.getParameter("longitude" + cnt)));
			
			pinpointMapper.insert(pinpoint);
			
			for(int fileCnt = 1; (str = req.getParameter(cnt + "_" + fileCnt)) != null; fileCnt++) {
				Photo photo = new Photo();
				photo.setUri(cnt + "_" + fileCnt);
				saveLoad.save(req.getFile(cnt+"_"+fileCnt), "", cnt + "_" + fileCnt);
				//photoMapper.insert(photo);
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
	public boolean delete(TravelRecord travelRecord) throws Exception {
		travelRecordMapper.delete(travelRecord);
		return false;
	}

}
