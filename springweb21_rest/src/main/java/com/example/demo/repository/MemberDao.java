package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.MemberDto;

@Repository
public class MemberDao {
	
	@Autowired
	private SqlSession session;
	
	// 회원 자료 전체 읽기
	public List<MemberDto> getListAll(){
		
		return session.selectList("member.getList");
	}

}
