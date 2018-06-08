package com.tf.presentation;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.tf.domain.TravelRoute;
import com.tf.service.TravelRouteService;

@Controller
@RequestMapping(value = "/travelRoute")
public class TravelRouteController {
	@Resource
	private TravelRouteService travelRouteService;
	
	@RequestMapping(value = "/view{NO}", method = RequestMethod.GET)
	public ModelAndView view(@PathVariable int travelRouteNo, HttpServletRequest request) throws Exception {
		HttpSession httpSession = request.getSession(false);
		
		if(httpSession == null) {
			return new ModelAndView("/");
		} else {
			TravelRoute travelRoute = new TravelRoute();
			travelRoute.setNo(travelRouteNo);
			travelRoute = this.travelRouteService.select(travelRoute);
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("travelRoute", travelRoute);
			
			return modelAndView;
		}
	}
	
	@RequestMapping(value ="/remove{NO}", method = RequestMethod.GET)
	public ModelAndView remove(@PathVariable int travelRouteNo) throws Exception{
		TravelRoute travelRoute = new TravelRoute();
		travelRoute.setNo(travelRouteNo);;
		this.travelRouteService.delete(travelRoute);
		
		return new ModelAndView(new RedirectView(""));
		
	}
}
