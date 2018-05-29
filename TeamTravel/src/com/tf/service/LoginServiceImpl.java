package com.tf.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tf.domain.Manager;
import com.tf.domain.Member;
import com.tf.persistence.ManagerMapper;
import com.tf.persistence.MemberMapper;

@Service
public class LoginServiceImpl implements LoginService {

	@Resource
	private MemberMapper memberMapper;

	@Resource
	private ManagerMapper managerMapper;

	@Override
	public Object login(String userEmail, String password) {
		ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = servletRequestAttribute.getRequest();

		Member member = new Member();
		Member member2 = new Member();
		
		Manager manager = new Manager();
		Manager manager2 = new Manager();

		member.setEmail(userEmail);
		member.setPassword(password);

		manager.setEmail(userEmail);
		manager.setPassword(password);

		member2 = this.memberMapper.select(member);
		if (member2 != null) {
			HttpSession httpSession = request.getSession(true);
			httpSession.setAttribute("email", member.getEmail());
			httpSession.setAttribute("password", member.getPassword());

			return member2;
		} else if (member2 == null) {
			manager2 = this.managerMapper.select(manager);
			
			if (manager2 != null) {
				HttpSession httpSession = request.getSession(true);
				httpSession.setAttribute("email", manager.getEmail());
				httpSession.setAttribute("nickname", manager.getNickName());
				httpSession.setAttribute("password", manager.getPassword());

				return manager2;
			}
		}
		return null;
	}

	@Override
	public void logout(HttpServletRequest request) {
		HttpSession httpSession = request.getSession(false);
		if (httpSession != null) {
			httpSession.invalidate();
		}
	}
}
