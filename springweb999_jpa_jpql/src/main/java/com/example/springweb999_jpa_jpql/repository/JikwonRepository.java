package com.example.springweb999_jpa_jpql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springweb999_jpa_jpql.entity.Jikwon;

public interface JikwonRepository extends JpaRepository<Jikwon, Integer>{
	// fetch join X
	List<Jikwon> findByBuserNum(int num);

}
