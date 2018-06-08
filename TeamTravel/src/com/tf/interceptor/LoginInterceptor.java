package com.tf.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession httpSession = request.getSession(true);

		Object email = httpSession.getAttribute("email");
		
		//System.out.println(email);
		if(email == null) {
		
			response.sendRedirect("/login");
			return false;
			}
		System.out.println("d");
		return true;
	}
}

/* (userAgent.indexOf("Windows") > -1) {
	Object email = httpSession.getAttribute("email");
	if (email != null) {
		return super.preHandle(request, response, handler);

		response.sendRedirect(request.getContextPath() + "/login");
		return false;
}*/