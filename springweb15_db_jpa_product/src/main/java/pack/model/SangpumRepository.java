package pack.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SangpumRepository extends JpaRepository<Sangpum, Integer> {
	
	// Query Method ------------------------------------------------------
	// 메서드 네이밍룰 - findBy[엔티티명]칼럼명, readBy..., getBy...
	// findBy변수명And변수명, findBy변수명Or변수명, findBy변수명GreaterThanEqual ...
	
	// 상품명으로 검색
	// findBy칼럼명Containing - 검색어가 포함된(like '%검색어%')
	List<Sangpum> findBySangContaining(String searchValue);
	
	// ...StartingWith - 해당 검색어로 시작되는(like '검색어%')
	List<Sangpum> findBySangStartingWith(String searchValue);
	
	// ...EndingWith - 해당 검색어로 끝나는(like '%검색어')
	List<Sangpum> findBySangEndingWith(String searchValue);
	
	// JPQL --------------------------------------------------------------
	@Query(value="select s from Sangpum s where s.sang like %:searchValue%")
	List<Sangpum> searchLike(@Param("searchValue") String searchValue);
	

}
