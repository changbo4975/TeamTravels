package com.tf.presentation;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.tf.domain.Pinpoint;
import com.tf.service.PinpointService;

@Controller
@RequestMapping("/pinpoint")
public class PinpointController {
	@Resource
	private PinpointService pinpointService;
	
	@RequestMapping(value="/pinpoint{no}", method = RequestMethod.GET)
	public ModelAndView editGet(@PathVariable int no, HttpServletRequest request) throws Exception {
		HttpSession httpSession = request.getSession(false);
		
		if(httpSession == null) {
			return new ModelAndView("/pinoint/find"); // 원래창 경로 기입
		} else {
			Pinpoint pinpoint = new Pinpoint();
			pinpoint.setNo(no);
			
			pinpoint = this.pinpointService.view(pinpoint);

			ModelAndView modelAndView = new ModelAndView("/pinpoint/update");
			modelAndView.addObject("Pinpoint", pinpoint);

			return modelAndView;
		}
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public ModelAndView editPost(HttpServletRequest request) throws Exception {
		String iconNo = request.getParameter("iconNo");
		int lastIconNo = Integer.parseInt(iconNo);
		
		Pinpoint pinpoint = new Pinpoint();
		pinpoint.setIconNo(lastIconNo);
		
		this.pinpointService.edit(pinpoint);
		
		return new ModelAndView(new RedirectView("/무슨값"));
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) throws Exception {
		HttpSession httpSession = request.getSession(false);
		
		if(httpSession == null) {
			return new ModelAndView("/login");
		} else {
			int travelRecordNo = (int)httpSession.getAttribute("travelRecordNo");
			
			List<Pinpoint> listPinpoint= this.pinpointService.list(travelRecordNo);
			
			ModelAndView modelAndView = new ModelAndView("/pinpoint/list");
			modelAndView.addObject("listPinpoint", listPinpoint);
			return modelAndView;
		}
	}
	
	@RequestMapping(value = "/view/{NO}", method = RequestMethod.GET)
	public ModelAndView view(@PathVariable int pinpointNo, HttpServletRequest request) throws Exception {
		HttpSession httpSession = request.getSession(false);
		
		if(httpSession == null){
			return null;
		} else {
			Pinpoint pinpoint = new Pinpoint();
			pinpoint.setNo(pinpointNo);
			pinpoint= this.pinpointService.view(pinpoint);
			ModelAndView modelAndView = new ModelAndView("/pinpoint/view");
			modelAndView.addObject("pinpoint", pinpoint);
			
			return modelAndView;
		}
	}
	@RequestMapping(value = "/remove{NO}", method = RequestMethod.GET)
	public ModelAndView remove(@PathVariable int pinpintNo) throws Exception {
		this.pinpointService.delete(pinpintNo);
		
		return new ModelAndView(new RedirectView("/경로"));
	}
}
