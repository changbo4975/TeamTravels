package com.tf.presentation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.tf.domain.Photo;
import com.tf.domain.Pinpoint;
import com.tf.domain.TravelRecord;
import com.tf.service.TravelRecordService;

@Controller
@RequestMapping(value = "/travelRecord")
public class TravelRecordController {
	private TravelRecordService travelService;
	
	@RequestMapping(value = "/addApp", method = RequestMethod.POST)
	public void addPost(MultipartHttpServletRequest req) throws Exception {
		
		
	}
	
	public ModelAndView editGet(TravelRecord travelRecord) throws Exception {
		return null;
	}
	
	public ModelAndView editPost(TravelRecord travelRecord) throws Exception {
		return null;
	}
	
	public ModelAndView list(TravelRecord travelRecord) throws Exception {
		return null;
	}
	
	public ModelAndView view(TravelRecord travelRecord) throws Exception {
		return null;
	}
	
	public ModelAndView remove(TravelRecord travelRecord) throws Exception {
		return null;
	}
}
