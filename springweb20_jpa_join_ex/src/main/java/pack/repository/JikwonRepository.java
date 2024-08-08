package pack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pack.entity.Jikwon;

public interface JikwonRepository extends JpaRepository<Jikwon, Integer>{

	// query method
	Jikwon findByJno(int jno);
	
	// vue.js 문제풀이 - by jikwon_jik
	List<Jikwon> findByJik(String jik);

}
