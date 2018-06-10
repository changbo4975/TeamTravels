package com.tf.presentation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tf.domain.TravelRecord;
import com.tf.service.TravelRecordService;
import com.tf.util.SaveLoad;

@Controller
@RequestMapping(value = "/travelRecord")
public class TravelRecordController {
	private TravelRecordService travelService;
	
	@RequestMapping(value = "/addApp", method = RequestMethod.POST)
	public void addPost(String message) throws Exception {
		ObjectMapper o = new ObjectMapper();
		Map map = o.readValue(message, Map.class);
		
		System.out.println(message);

		TravelRecord travelRecord = new TravelRecord(map);
		
//		for(int i = 0; i < travelRecord.getPinpointList().size(); i++) {
//			MultipartFile multipartFile = req.getFile("");
//			File file = null;
//			FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
//			SaveLoad saveLoad = new SaveLoad();
//			saveLoad.save(file, path, fileName)
//		}

		System.out.println(travelRecord);
		
//		travelService.add(travelRecord);
		
		
	}
	@Transactional
	@RequestMapping(value = "/addAppa", method = RequestMethod.POST)
	public void foo(HttpServletRequest request) throws Exception {
		
		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
		
		MultipartFile mpfile = req.getFile("joo.jpg");
		String name = mpfile.getName();
		System.out.println(name);

		SaveLoad s = new SaveLoad();
		
		s.save(mpfile, "s", "a", name);
		
		BufferedImage b = s.load( "s", "a", name);
		System.out.println(b);
		
//		String message = req.getParameter("message");
//		System.out.println("travelController check");
//		System.out.println(message);
//		
//		ObjectMapper o = new ObjectMapper();
//		Map map = o.readValue(message, Map.class);
//		
//		TravelRecord travelRecord = new TravelRecord(map);
//		
//		for(int i = 0; i < travelRecord.getPinpointList().size(); i++) {
//			Pinpoint pinpoint = travelRecord.getPinpointList().get(i);
//			
//			for(int j = 0; j < pinpoint.getPhotoList().size(); j++) {
//				Photo photo = pinpoint.getPhotoList().get(j);
//				File file = null;
//				FileUtils.writeByteArrayToFile(file, 
//						req.getFile(travelRecord.getEmail() + '_' + i + '_' + j).getBytes());
//				
//			}
//		}
	
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
