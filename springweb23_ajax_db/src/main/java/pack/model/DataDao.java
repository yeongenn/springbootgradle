package pack.model;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pack.dto.SangpumDto;
import pack.entity.Sangpum;

@Repository
public class DataDao {

	@Autowired
	DataRepository dataRepository;
	
	public List<SangpumDto> getSangpumAll(){
		List<SangpumDto> slist = dataRepository.findAll().stream()
//									.map(sangpum -> SangpumDto.builder()
//											.code(sangpum.getCode())
//											.sang(sangpum.getSang())
//											.su(sangpum.getSu())
//											.dan(sangpum.getDan())
//											.build())
//									.collect(Collectors.toList());
									.map(SangpumDto::toDto)
									.toList();
		return slist;
		
	}
	
									
}
