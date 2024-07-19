package pack.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DataDao {
	
	@Autowired
	private SangpumRepository repository;
	
	// 전체 자료 출력
	public List<Sangpum> getDataAll(){
		List<Sangpum> list = repository.findAll(); // 기본 지원 메서드
		return list;
	}
	
	// 검색 자료 출력
	public List<Sangpum> getDataSearch(String searchValue){
		//List<Sangpum> list = repository.findBySangContaining(searchValue); // query method
		List<Sangpum> list = repository.searchLike(searchValue); // jpql
		System.out.println("list : " + list.size());
		return list;
	}

}
