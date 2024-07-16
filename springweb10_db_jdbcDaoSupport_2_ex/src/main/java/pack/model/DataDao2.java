package pack.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import pack.model.DataDao.JikwonRowMapper;

@Repository
public class DataDao2 { // JdbcTemplate 포함관계
	@Autowired
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;
	
	public List<JikwonDto> jikwonByJik(String jik) {
		String sql = "select jikwon_no, jikwon_name, jikwon_gen, jikwon_pay from jikwon where jikwon_jik = ?";
		
//		Object[] params = {jik};
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<JikwonDto> list = (List) jdbcTemplate.query(sql, new JikwonRowMapper(), jik);
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
