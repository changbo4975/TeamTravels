package com.tf.persistence;

import java.sql.Connection;
import java.util.List;

import com.tf.domain.Member;

public class MemberDAO {
	Connection connection;
	
	public int update(String qurey) {
		return 0;
	}
	
	public List<Member> select(String qurey) {
		
		return null;
	}
	
	public Member getRow(Member member) {
		return null;
	}
	
	public List<Member> getList(Member member) {
		return null;
	}
	
	public int insert(Member member) {
		return 0;
	}
	
	public int edit(Member member) {
		return 0;
	}
	
	public int delete(Member member) {
		return 0;
	}
}
