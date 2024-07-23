package pack.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DataRepository extends JpaRepository<Board, Integer> {

	// JPQL
	
	@Query("select b from Board b where b.author like %?1%") // 위치기반
	List<Board> searchLike(String searchValue); // 작성자 검색
	
	@Query("select b from Board b where b.title like %:searchValue%") // 이름기반
	List<Board> searchLike2(@Param("searchValue") String searchValue); // 제목 검색
	
	// 추가할 때 pk 삼을 가장 큰 번호 얻기
	@Query("select max(b.num) from Board b ")
	int maxNum();
	
	// 상세보기 할 때 조회수 증가
	/*
	 JPA는 내부적으로 벌크 연산(update, delete, insert)을 한다
	 영속성 컨텍스트와에 있는 자료(Board)와
	 DB에 있는 Board(springboard) 값이 다를 수 있다
	 따라서 벌크 연산 수행 후 영속성 컨텍스트에 있는 쿼리를 refresh(clear)해줘야 한다
	 
	 @Modifying(clearAutomatically = true) 가 그 역할을 수행
	 1차 캐시를 비워주는 설정으로 영속성 컨텍스트의 쿼리를 초기화한다
	 */
	@Modifying(clearAutomatically = true)
	@Query("update Board b set b.readcnt = b.readcnt + 1 where b.num = ?1")
	void updateReadcnt(int num);
}
