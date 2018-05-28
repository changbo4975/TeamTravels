package com.tf.service;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {
	public Object login(String userEmail, String password);
	public void logout(HttpServletRequest request);
}
