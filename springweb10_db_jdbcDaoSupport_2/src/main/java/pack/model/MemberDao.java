package pack.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import pack.controller.MemberBean;

@Repository
public class MemberDao extends JdbcDaoSupport{
	
	// 해당 클래스 객체 생성 전에 생성자 통해서 먼저 DB 연결 정보 넘겨주기
	// 따라서 상속 시에는 필드 주입 대신 생성자 주입 해주기
	@Autowired
	public MemberDao(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	// 전체 자료 읽기
	public List<MemberDto> getMemberList(){
		String sql = "select * from membertab";
		
		/*
		// 내부 클래스 작성하는 대신 내부 무명 클래스로 작성
		List<MemberDto> list = getJdbcTemplate().query(sql, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberDto dto = new MemberDto();
				dto.setId(rs.getString("id"));
				dto.setPassword(rs.getString("passwd"));
				dto.setName(rs.getString("name"));
				dto.setReg_date(rs.getString("reg_date"));
				return dto;
			}
		});
		*/
		
		// 람다 표현식 사용
		List<MemberDto> list = getJdbcTemplate().query(sql, (ResultSet rs, int rowNum) -> {
			MemberDto dto = new MemberDto();
			dto.setId(rs.getString("id"));
			dto.setPasswd(rs.getString("passwd"));
			dto.setName(rs.getString("name"));
			dto.setReg_date(rs.getString("reg_date"));
			return dto;
		});
		
		return list;
	}
	
	// 추가
	public void insData(MemberBean bean) {
		String sql = "insert into membertab values (?, ?, ?, now())";
		
		// 하나의 자료가 넘어오더라도 Object 배열로 받아줘야 한다
		Object[] params = {bean.getId(), bean.getName(), bean.getPasswd()};
		// insert는 update()
		getJdbcTemplate().update(sql, params);
	}
	
	// 수정
	
	// 삭제
	

	
}
