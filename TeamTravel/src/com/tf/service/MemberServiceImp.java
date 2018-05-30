package com.tf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tf.domain.Member;
import com.tf.persistence.MemberMapper;

@Service
public class MemberServiceImp implements MemberService {
	@Autowired
	private MemberMapper memberMapper;
	 
	@Override
	public List<Member> list(Member member) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member view(Member member) throws Exception {
		return memberMapper.select(member);
	}

	@Override
	public boolean add(Member member) throws Exception {
		memberMapper.insert(member);
		
		return false;
	}

	@Override
	public boolean edit(Member member) throws Exception {
		memberMapper.update(member);
		
		return false;
	}

	@Override
	public int remove(Member member) throws Exception {
			memberMapper.delete(member);
		return 0;
	}

}
