package pack.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pack.DataSource;

@Repository
public class DataDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Autowired
	private DataSource dataSource;
	
	public ArrayList<JikwonDto> jikwonByJik(String jik) {
		ArrayList<JikwonDto> list = new ArrayList<JikwonDto>();
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("select " + 
										"jikwon_no, jikwon_name, jikwon_gen, jikwon_pay " +
										"from jikwon where jikwon_jik = ?");
			pstmt.setString(1, jik);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				JikwonDto dto = new JikwonDto();
				dto.setNo(rs.getString("jikwon_no"));
				dto.setName(rs.getString("jikwon_name"));
				dto.setGender(rs.getString("jikwon_gen"));
				dto.setPay(rs.getString("jikwon_pay"));				
				list.add(dto);
				
			}
		} catch (Exception e) {
			System.out.println("jikwonByJik error : " + e.getMessage());
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				
			}
		}
		return list;
	}
}
