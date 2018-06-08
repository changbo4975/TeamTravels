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

import com.tf.domain.TravelRecordComment;
import com.tf.service.TravelRecordCommentService;

@Controller
@RequestMapping(value = "/travelRecordComment")
public class TravelRecordCommentController {
	@Resource
	private TravelRecordCommentService travelRecordCommentService;
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addGet() throws Exception {
		
		return new ModelAndView("/travelRecordComment/add");
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addPost(HttpServletRequest request) throws Exception {
		HttpSession httpSession = request.getSession(false);
		String reply = request.getParameter("reply");
		
		TravelRecordComment travelRecordComment = new TravelRecordComment();
		travelRecordComment.setReply(reply);
		
		this.travelRecordCommentService.add(travelRecordComment);
		
		if(httpSession == null) {
			return new ModelAndView("/");
		} else {
			return new ModelAndView("//");
		}
	}
	
	@RequestMapping(value = "/travelRecordComment{NO}", method = RequestMethod.GET)
	public ModelAndView editGet(@PathVariable int no, HttpServletRequest request) throws Exception {
		HttpSession httpSession = request.getSession(false);
		
		if(httpSession == null) {
			return new ModelAndView("/travelRecordComment");
		} else {
			TravelRecordComment travelRecordComment = new TravelRecordComment();
			travelRecordComment.setNo(no);
			
			travelRecordComment = this.travelRecordCommentService.view(travelRecordComment);
			
			ModelAndView modelAndView = new ModelAndView("");
			modelAndView.addObject("travelRecordComment", travelRecordComment);
			
			return modelAndView;
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView editPost(HttpServletRequest request) throws Exception {
		String travelRecordCommentReply = request.getParameter("travelRecordCommentReply");
		
		TravelRecordComment travelRecordComment = new TravelRecordComment();
		travelRecordComment.setReply(travelRecordCommentReply);
		
		this.travelRecordCommentService.edit(travelRecordComment);
		
		return new ModelAndView(new RedirectView(""));
	}
	
	@RequestMapping(value = "/view{NO}", method = RequestMethod.GET)
	public ModelAndView view(@PathVariable int travelRecordCommentNo, HttpServletRequest request) throws Exception {
		HttpSession httpSession = request.getSession(false);
		
		if(httpSession == null) {
			return new ModelAndView("//");
		} else {
			TravelRecordComment travelRecordComment = new TravelRecordComment();
			travelRecordComment.setNo(travelRecordCommentNo);
			travelRecordComment = this.travelRecordCommentService.view(travelRecordComment);
			ModelAndView modelAndView = new ModelAndView("/travelRecordComment/view");
			modelAndView.addObject("travelRecordComment", travelRecordComment);
			
			return modelAndView;
		}
	}
	
	@RequestMapping(value = "/remove{NO}", method = RequestMethod.GET)
	public ModelAndView remove(@PathVariable int travelRecordCommentNo) throws Exception {
		TravelRecordComment travelRecordComment = new TravelRecordComment();
		travelRecordComment.setNo(travelRecordCommentNo);
		this.travelRecordCommentService.remove(travelRecordComment);
		
		return new ModelAndView(new RedirectView("/°æ·Î"));
	}
}
