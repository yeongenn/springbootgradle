package pack.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pack.controller.MemBean;

@Repository
public class DataProcess {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DataMapperInter mapper; // Spring에서 Hikari Pool 자동지원
	
	// 전체 자료 읽기
	public List<MemDto> getDataAll(){
		List<MemDto> list = mapper.selectAll();
//		logger.info("전체 자료 크기 : " + list.size());
		return list;
	}
	
	// 부분 자료 읽기
	public MemDto getData(String num) {
		MemDto dto = mapper.selectPart(num);
		return dto;
	}
	
	// 자료 추가하기
	public boolean insert(MemBean bean) {
		// 번호 중복 방지 또는 번호 자동 증가 작업이 필요하겠지?
		// 여기서는 생략~
		int re = mapper.insertData(bean);
		if(re > 0) return true;
		else return false;
	}
	
	// 자료 수정하기
	public boolean update(MemBean bean) {
		int re = mapper.updateData(bean);
		if(re > 0) return true;
		else return false;
	}
	
	// 자료 삭제하기
	public boolean delete(String num) {
		int re = mapper.deleteData(num);
		if(re > 0) return true;
		else return false;
	}
}
