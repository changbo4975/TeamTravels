package com.tf.presentation;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ModelAndView addGet() throws Exception {
		
		return new ModelAndView("/member/add");
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addPost(Member member) throws Exception {
		if(member.getEmail() != null || member.getNickname() != null || member.getPassword() != null) {
			member.setRepresentativePhoto("»çÁø");
			memberService.add(member);
		}
	
		return new ModelAndView("/member/add");
	}
	
	@RequestMapping(value = "/addApp", method = RequestMethod.POST)
	public void addPostApp(Member member) throws Exception {

	
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
