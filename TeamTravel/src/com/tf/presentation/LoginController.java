package com.tf.presentation;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.tf.domain.Member;
import com.tf.service.LoginService;

@Controller
public class LoginController {
	@Resource
	private LoginService loginService;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request) {
		HttpSession httpSession = request.getSession(true);
		if(httpSession.getAttribute("email") != null) {
			return new ModelAndView(new RedirectView("/NewFile"));
		} else {
			return new ModelAndView("/login");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ModelAndView logined(HttpServletRequest request, HttpServletResponse response, String userEmail,
			String pw) throws Exception {
		//°ªµé¾î¿È
		
		if (userEmail != null && "".equals(userEmail) == false && pw != null && "".equals(pw) == false) {
			Object member = (Object)this.loginService.login(userEmail, pw);
			
			if (member != null) {
				return new ModelAndView(new RedirectView("/NewFile"));
			}
		}
		return new ModelAndView("/login");
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request) throws Exception {
		this.loginService.logout(request);

		return new ModelAndView(new RedirectView("/login"));
	}
}
