package pack.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class DataDao{ 
	
	@Autowired
	private DataMappingInter mapping;
	
	public List<JikwonDto> getJikwon(String jik){
		List<JikwonDto> jikwonList = mapping.selectSearch(jik);
		return jikwonList;		
	}
	
	// mybatis 어노테이션 확인용~
	public List<JikwonDto> getJikwonAll(){
		List<JikwonDto> allList = mapping.selectAll();
		return allList;		
	}
	
	
}
