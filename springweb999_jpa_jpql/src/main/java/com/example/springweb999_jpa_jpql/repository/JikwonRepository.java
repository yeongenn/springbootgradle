package com.example.springweb999_jpa_jpql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springweb999_jpa_jpql.entity.Jikwon;
import org.springframework.data.jpa.repository.Query;

public interface JikwonRepository extends JpaRepository<Jikwon, Integer>{
	// join X
	List<Jikwon> findByBuserBname(String bname);

	// 일반 join
	@Query("select j from Jikwon j join j.buser b where b.bname = ?1")
	List<Jikwon> findByBuserBnoJoin(String bname);

	// fetch join
	@Query("select j from Jikwon j join fetch j.buser b where b.bname = ?1")
	List<Jikwon> findByBuserBnoFetchJoin(String bname);
	
	//proxy 확인용
	Jikwon findByNo(int no);

}
