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

import com.tf.domain.PinpointComment;
import com.tf.service.PinpointCommentService;

@Controller
@RequestMapping(value = "/pinpointComment")
public class PinpointCommentController {
	@Resource
	private PinpointCommentService pinpointCommentService;
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addGet() throws Exception{
		
		return new ModelAndView("/pinpointComment/add");
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addPost(HttpServletRequest request) throws Exception{
		HttpSession httpSession = request.getSession(false);
		String reply = request.getParameter("reply");
		
		PinpointComment pinpointComment = new PinpointComment();
		pinpointComment.setReply(reply);
		
		this.pinpointCommentService.add(pinpointComment);
		
		if(httpSession == null) {
			return new ModelAndView("/");
		} else {
			return new ModelAndView("//");
		}
	}
	
	@RequestMapping(value = "/view{NO}", method = RequestMethod.GET)
	public ModelAndView view(@PathVariable int pinpointCommentNo, HttpServletRequest request) throws Exception{
		HttpSession httpSession = request.getSession(false);
		
		if(httpSession == null) {
			return new ModelAndView("//");
		} else {
			PinpointComment pinpointComment = new PinpointComment();
			pinpointComment.setNo(pinpointCommentNo);
			pinpointComment = this.pinpointCommentService.view(pinpointComment);
			ModelAndView modelAndView = new ModelAndView("/pinpointComment/view");
			modelAndView.addObject("pinpointComment", pinpointComment);
			
			return modelAndView;
		}
	}
	@RequestMapping(value = "/pinpointComment{NO}", method = RequestMethod.GET)
	public ModelAndView editGet(@PathVariable int no, HttpServletRequest request) throws Exception{
		HttpSession httpSession = request.getSession(false);
		
		if(httpSession == null) {
			return new ModelAndView("/pinpointComment/");
		}else {
			PinpointComment pinpointComment = new PinpointComment();
			pinpointComment.setNo(no);
			
			pinpointComment = this.pinpointCommentService.view(pinpointComment);
			
			ModelAndView modelAndView = new ModelAndView("");
			modelAndView.addObject("pinpointComment", pinpointComment);
			
			return modelAndView;
		}
	}
	@RequestMapping(value="/", method = RequestMethod.POST)
	public ModelAndView editPost(HttpServletRequest request) throws Exception {
		String pinpointCommentReply = request.getParameter("pinpointCommentReply");
		
		PinpointComment pinpointComment = new PinpointComment();
		pinpointComment.setReply(pinpointCommentReply);
		
		this.pinpointCommentService.edit(pinpointComment);
		
		return new ModelAndView(new RedirectView(""));
	}
	
	@RequestMapping(value = "/remove{NO}", method = RequestMethod.GET)
	public ModelAndView remove(@PathVariable int pinpointCommentNo) throws Exception{
		PinpointComment pinpointComment = new PinpointComment();
		pinpointComment.setNo(pinpointCommentNo);
		this.pinpointCommentService.remove(pinpointComment);
		
		return new ModelAndView(new RedirectView("/°æ·Î"));
	}
}
