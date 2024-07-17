package pack.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GogekMapper {

	@Select("select gogek_no, gogek_name, gogek_tel from gogek where gogek_damsano = #{no}")
	@Results(id="gogekMap", value = {
			@Result(property = "no", column = "gogek_no"),
			@Result(property = "name", column = "gogek_name"),
			@Result(property = "tel", column = "gogek_tel")
	})
	List<GogekDto> selectMyGogek(String no);
}
