package com.tf.service;

import java.util.List;

import com.tf.domain.Member;

public interface MemberService {	
	public List<Member> list(Member member) throws Exception;
	public Member view(Member member) throws Exception;
	public boolean add(Member member) throws Exception;
	public boolean edit(Member member) throws Exception;
	public int remove(Member member) throws Exception;
}
