package pack.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import pack.model.DataSource;

@Repository
public class DataDao extends JdbcDaoSupport { // JdbcDaoSupport 상속관계 사용
	
	@Autowired
	public DataDao(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	public List<JikwonDto> jikwonByJik(String jik) {
		String sql = "select jikwon_no, jikwon_name, jikwon_gen, jikwon_pay from jikwon where jikwon_jik = ?";
		
		Object[] params = {jik};
		// 파라미터가 하나일 경우에는 객체배열로 받지 않고 바로 건네줘도 가능
		List<JikwonDto> list = (List) getJdbcTemplate().query(sql, new JikwonRowMapper(), params);
		return list;
	}
	
	class JikwonRowMapper implements RowMapper<Object>{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			JikwonDto dto = new JikwonDto();
			dto.setNo(rs.getString("jikwon_no"));
			dto.setName(rs.getString("jikwon_name"));
			dto.setGender(rs.getString("jikwon_gen"));
			dto.setPay(rs.getString("jikwon_pay"));
			return dto;
		}
	}
	
	
}
