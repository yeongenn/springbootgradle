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
	
	// 자료 추가
	public void insert(MemberDto dto) {
		session.insert("member.insert", dto);
	}
	
	// 자료 1개 가져오기 - 수정용
	public MemberDto getData(int num) {
		return session.selectOne("member.getData", num);
	}
	
	// 자료 수정
	public void update(MemberDto dto) {
		session.update("member.update", dto);
	}
	
	// 자료 삭제
	public void delete(int num) {
		session.delete("member.delete", num);
	}

}
