package pack.model;

import java.util.List;

import org.apache.ibatis.annotations.*;

@Mapper
public interface JikwonMapper {

	@Select("select jikwon_no, jikwon_name, jikwon_jik, jikwon_rating from jikwon")
	@Results(id="jikwonMap", value = {
			@Result(property = "no", column = "jikwon_no"),
			@Result(property = "name", column = "jikwon_name"),
			@Result(property = "jik", column = "jikwon_jik"),
			@Result(property = "rating", column = "jikwon_rating")
	})
	List<JikwonDto> selectAll();

	@Select("select jikwon_no, jikwon_name, jikwon_jik, jikwon_rating from jikwon inner join buser on buser_no = buser_num where buser_name = #{depName}")
	@Results(id="jikwonMap2", value = {
			@Result(property = "no", column = "jikwon_no"),
			@Result(property = "name", column = "jikwon_name"),
			@Result(property = "jik", column = "jikwon_jik"),
			@Result(property = "rating", column = "jikwon_rating"),
			@Result(property = "gogekList", column = "jikwon_no", many=@Many(select="pack.model.GogekMapper.selectMyGogek"))
	})
	List<JikwonDto> selectAll2(String depName);
	
}
