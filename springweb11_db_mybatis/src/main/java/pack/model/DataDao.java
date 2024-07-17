package pack.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import pack.controller.FormBean;

@Repository
@Slf4j		// 로그 출력 : 롬복이 지원
public class DataDao {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass()); // 현재 클래스에 로그 생성

	@Autowired
	private DataMappingInter mapping;
	
	// 전체 자료 읽어오기
	public List<SangpumDto> getDataAll(){
		List<SangpumDto> list = mapping.selectAll();
//		System.out.println("list.size() : " + list.size());
		logger.info("list.size() : " + list.size());
		return list;
	}
	
	// 검색 자료 읽어오기
	public List<SangpumDto> getDataSearch(FormBean bean){
		List<SangpumDto> searchList = mapping.selectSearch(bean);

		return searchList;
	}
}
