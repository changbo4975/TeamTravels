package com.tf.presentation;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tf.domain.Member;
import com.tf.service.MemberService;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	@Resource
	private MemberService memberService;
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addGet(Member member) throws Exception {
		System.out.println("ee");
		
		return new ModelAndView("/member/memberAdd");
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addPost(Member member) throws Exception {
		if(member.getEmail() != null || member.getNickName() != null || member.getPassword() != null)
			memberService.add(member);
	
		return new ModelAndView("/member/memberAdd");
	}
	
	@RequestMapping(value = "/addApp", method = RequestMethod.POST)
	public void addPostApp(Member member) throws Exception {
//		System.out.println(req.getParameter("email"));
//		System.out.println(req.getParameter("nickname"));
//		System.out.println(req.getParameter("password"));
		
		System.out.println(member.getEmail());
		System.out.println(member.getNickName());
		System.out.println(member.getPassword());
		System.out.println(member.getAge());
		System.out.println(member.getGender());
		System.out.println(member.getIntroduction());

	
		//return new ModelAndView("/member/memberAdd");
	}
	
	public ModelAndView editGet(Member member) throws Exception {
		return null;
	}
	
	public ModelAndView editPost(Member member) throws Exception {
		return null;
	}
	
	public ModelAndView list(Member member) throws Exception {
		return null;
	}
	
	public ModelAndView view(Member member) throws Exception {
		return null;
	}
	
	public ModelAndView remove(Member member) throws Exception {
		return null;
	}
}
