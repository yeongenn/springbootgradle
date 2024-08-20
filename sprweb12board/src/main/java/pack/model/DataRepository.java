package pack.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DataRepository extends JpaRepository<Board, Integer>{
	// 전체 자료 읽기
	Page<Board> findByOrderByNumDesc(Pageable pageable);
	
	//JPQL 사용
	// 검색용
	@Query("select b from Board b where b.name like %?1%")   // name별 검색
	Page<Board> searchLike(Pageable pageable, String searchValue);   
	@Query("select b from Board b where b.title like %:searchValue%")  // title별 검색
	Page<Board> searchLike2(Pageable pageable, @Param("searchValue") String searchValue);
	
	// 추가시 가장 큰 num 읽기
	@Query("select max(b.num) from Board as b")
	int maxNum();
	
	// 상세보기 할 때 조회수 증가
	// @Modifying은 @Query로 작성된 INSERT, UPDATE, DELETE 쿼리를 사용할 때 필요하며(SELECT 제외), 
	// 주로 다중 UPDATE 또는 DELETE 같은 복잡한 벌크 연산을 하나의 쿼리로 수행할 때 사용.
	// https://wildeveloperetrain.tistory.com/142 참조
	@Modifying(clearAutomatically = true)  // 1차 캐시를 비워주는 설정을 부여
	@Query(value="update Board bo set bo.readcnt=bo.readcnt + 1 where bo.num = ?1")
	void updateReadcnt(int num);
	
	// 비밀번호 얻기
	@Query("select b.pass from Board as b where b.num=?1")
	String selectPass(int num);
	
	// 댓글 처리에서 같은 그룹내에서 onum 값 변경
	@Modifying(clearAutomatically = true)  // 1차 캐시를 비워주는 설정을 부여
	@Query(value="update Board bo set bo.onum=bo.onum + 1 where bo.gnum = ?1 and bo.onum >= ?2")
	void updateOnum(int gnum, int onum);
}
