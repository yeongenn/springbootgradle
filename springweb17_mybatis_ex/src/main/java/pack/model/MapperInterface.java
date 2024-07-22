package pack.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MapperInterface {
	
	List<Jikwon> selectJikwon1(int buserNum);
	List<Jikwon> selectJikwon2(@Param("buserNum") int buserNum, 
								@Param("rating") String rating);

}
