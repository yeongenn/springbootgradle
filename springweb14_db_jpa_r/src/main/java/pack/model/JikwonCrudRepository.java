package pack.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JikwonCrudRepository extends JpaRepository<Jikwon, Integer>{
	
	// findAll, findById, ... 기본적으로 지원
	
	// 연봉이 6000 초과인 직원 읽어오기~
	List<Jikwon> findByPayGreaterThan(int pay);
	
	// 부서번호별 직원 읽어오기
	// 메서드명만 적어줬는데 알아서 select 해준다 와~
	List<Jikwon> findByJik(String jik);
	

}
