package com.tf.presentation;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.tf.domain.Photo;
import com.tf.service.PhotoService;

@Controller
@RequestMapping(value = "/photo")
public class PhotoController {
	@Resource
	private PhotoService photoService;
	
	@RequestMapping(value = "/view{NO}", method = RequestMethod.GET)
	public ModelAndView view(@PathVariable int no, HttpServletRequest request) throws Exception {
		HttpSession httpSession = request.getSession(false);
		
		if(httpSession == null) {
			return new ModelAndView("//");
		} else {
			Photo photo = new Photo();
			photo.setNo(no);
			photo = this.photoService.view(photo);
			ModelAndView modelAndView = new ModelAndView("/photo/view");
			modelAndView.addObject("photo", photo);
			
			return modelAndView;
		}
	}
	
	@RequestMapping(value = "/remove{NO}", method = RequestMethod.GET)
	public ModelAndView remove(@PathVariable int no) throws Exception {
		Photo photo = new Photo();
		photo.setNo(no);
		this.photoService.delete(no);
		
		return new ModelAndView(new RedirectView("/°æ·Î"));
	}
}
