package pack.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductCrudRepository extends JpaRepository<ProductVo, Integer> {
	// Repository > PagingAndSortingRepository > JpaRepository
	// Repository > CrudRepository
	
	// JPQL ---------------------------------------------------------
	@Query(value="select p from ProductVo as p")
	List<ProductVo> findAllData();
	
	// 메서드 이름으로 쿼리 생성 방법(규칙) - find + (엔티티명) + By + 변수명
	// 엔티티명은 생략 가능
	ProductVo findByCode(Integer code);
	
	// where절
	// 위치기반, 이름기반
	// 이름기반은 @Param으로 바인딩해줘야한다
	@Query(value="select p from ProductVo as p where p.code = :code")
	ProductVo findByCodeMy(@Param("code") int code);
	
	@Query(value="select p from ProductVo as p where p.code = ?1")
	ProductVo findByCodeMy2(int code);
	
	// 위치기반은 순서대로 자동 바인딩된다
	@Query(value="select p from ProductVo as p where p.code = ?1 or p.sang = ?2")
	List<ProductVo> findByData(int code, String sang);
	
	// Native Query -------------------------------------------------
	// nativeQuery = true라고 명시해줘야한다
	// RDB에 따라 달라지겠지?
	@Query(value="select code, sang, su, dan from product where code <= 5", nativeQuery = true)
	List<ProductVo> findAllData2();
	
}
