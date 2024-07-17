package pack.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DataMappingInter {

	// 테이블 내 특정 칼럼만 가져오려니 Dto 필드명이랑 칼럼명이 다르다!
	// 어떻게 맵핑시키지? (xml 파일은 사용 X)
	
	// 방법 1) sql문에 alias 달아주기
	/*
	@Select("select jikwon_no as no, "
			+ "jikwon_name as name, "
			+ "jikwon_gen as gender, "
			+ "jikwon_pay as pay "
			+ "from jikwon "
			+ "where jikwon_jik = #{jik}")
	List<JikwonDto> selectSearch(String jik);
	*/
	
	// 방법 2) mybatis 어노테이션 사용하기
	@Select("select jikwon_no, jikwon_name, jikwon_gen, jikwon_pay from jikwon where jikwon_jik = #{jik}")
	@Results(id="jikwonMap", value= { // id 재활용 가능 -> @ResultMap("jikwonMap")
			@Result(property = "no", column = "jikwon_no"),
			@Result(property = "name", column = "jikwon_name"),
			@Result(property = "gender", column = "jikwon_gen"),
			@Result(property = "pay", column = "jikwon_pay")
	})
	List<JikwonDto> selectSearch(String jik);
	
	// 
	@ResultMap("jikwonMap")
	@Select("select jikwon_no, jikwon_name, jikwon_gen, jikwon_pay from jikwon")
	List<JikwonDto> selectAll();
	
	
	
}
