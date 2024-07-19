package pack.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JikwonCrudRepository extends JpaRepository<Jikwon, Integer>{
	
	// findAll, findById, ... 기본적으로 지원
	
	// 연봉이 6000 초과인 직원 읽어오기~
	List<Jikwon> findByPayGreaterThan(int pay);
	
	// 부서별 직원 읽어오기
	// 메서드명만 적어줬는데 알아서 select 해준다 와~
	
	// fetch join
	//@Query("select j from Jikwon j join fetch j.buser b where b.buserName = ?1")
	List<Jikwon> findByBuserBuserName(String buserName); // findByBuserName 이라고 이름지었더니 에러
	
	/*
	 findByBuserName 이라고 이름지었더니 에러 - No property 'name' found for type 'Buser'
	 얘는 Buser를 엔티티로 인식... 그럼 엔티티를 명시해줘야겠다
	 findByBuserBuserName로 수정
	 fetch join문 거니까 메서드 이름은 상관없어지기는 하더라
	 */


}
