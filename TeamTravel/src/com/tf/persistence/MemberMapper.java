package com.tf.persistence;

import java.util.List;

import com.tf.domain.Member;

public interface MemberMapper {
	public Member select(Member member);
	public void update(Member member);
	public void insert(Member order);
	public List<Member> list(Member order);
	public void delete(Member member);
}
