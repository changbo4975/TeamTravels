package com.tf.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tf.domain.Member;
import com.tf.persistence.MemberDAO;

@Service
public class MemberServiceImp implements MemberService {
	private MemberDAO memberDAO;
	 
	@Override
	public List<Member> list(Member member) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member view(Member member) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Member member) throws Exception {
		System.out.println(member.getEmail());
		//memberDAO.insert(member);
		return false;
	}

	@Override
	public boolean edit(Member member) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Member member) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
