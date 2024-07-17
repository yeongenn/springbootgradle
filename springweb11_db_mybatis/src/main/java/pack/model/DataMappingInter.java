package pack.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import pack.controller.FormBean;

@Mapper // Mybatis 어노테이션
public interface DataMappingInter {

	@Select("select * from sangdata")
	List<SangpumDto> selectAll(); // 전체 자료 읽기
	
	@Select("select code, sang, su, dan "
			+ "from sangdata "
			+ "where sang like concat('%', #{searchValue}, '%')")
//	@Select("select code, sang, su, dan from sangdata where sang like '%'||#{searchValue}||'%'")
	List<SangpumDto> selectSearch(FormBean bean);
	
	
	
}
