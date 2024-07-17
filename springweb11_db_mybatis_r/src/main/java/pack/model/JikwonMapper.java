package pack.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface JikwonMapper {
	
	@Select("select jikwon_no, jikwon_name, jikwon_jik, jikwon_rating from jikwon")
	@Results(id="jikwonMap", value = {
			@Result(property = "no", column = "jikwon_no"),
			@Result(property = "name", column = "jikwon_name"),
			@Result(property = "jik", column = "jikwon_jik"),
			@Result(property = "rating", column = "jikwon_rating"),
	})
	List<JikwonDto> selectAll();
	
}
