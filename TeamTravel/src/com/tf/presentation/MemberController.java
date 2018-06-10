package com.tf.presentation;

import java.nio.charset.Charset;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tf.domain.Member;
import com.tf.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Resource
	private MemberService memberService;
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addGet() throws Exception {
		
		return new ModelAndView("/member/add");
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addPost(Member member) throws Exception {
		if(member.getEmail() != null || member.getNickname() != null || member.getPassword() != null) {
			member.setRepresentativePhoto("사진");
			memberService.add(member);
		}
		
		return new ModelAndView("/member/add");
	}
	
	@RequestMapping(value = "/addApp", method = RequestMethod.POST)
	public String addPostApp(String message) throws Exception {
		
		ObjectMapper o = new ObjectMapper();
		Map map = o.readValue(message, Map.class);
		
		Member member = new Member(map);
		
		memberService.add(member);
		
		return null;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editGet() throws Exception {
		
		Member member = new Member();
		member.setEmail("test@email.com");
		member = memberService.view(member);
		
		ModelAndView modelAndView = new ModelAndView("/member/edit");
		modelAndView.addObject("member", member);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView editPost(Member member) throws Exception {
		memberService.edit(member);
		
		return new ModelAndView("/member/view/" + member.getEmail());
	}
	
	public ModelAndView list(Member member) throws Exception {
		return null;
	}
	
	@RequestMapping(value = "/viewApp", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Member> viewApp(HttpServletRequest req) throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("text", "json", Charset.forName("UTF-8")));
		
		Member member = new Member();
		member.setEmail(req.getParameter("email"));
		member = this.memberService.view(member);
		
		System.out.println(req.getParameter("email"));
		
		return new ResponseEntity<Member>(member, httpHeaders, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/view/{email:.+}", method = RequestMethod.GET)
	public ModelAndView view(@PathVariable("email") String email) throws Exception {
		ModelAndView modelAndView = new ModelAndView("/member/view");
		Member member = new Member();
		member.setEmail(email);
		member = memberService.view(member);
		modelAndView.addObject("member", member);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/remove/{email:.+}", method = RequestMethod.GET)
	public ModelAndView remove(@PathVariable("email") String email) throws Exception {
		ModelAndView modelAndView = new ModelAndView("/member/view");
		Member member = new Member();
		member.setEmail(email);
		memberService.remove(member);
		modelAndView.addObject("member", member);
		
		return modelAndView;
	}
}
