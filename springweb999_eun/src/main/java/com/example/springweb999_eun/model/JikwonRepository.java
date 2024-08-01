package com.example.springweb999_eun.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JikwonRepository extends JpaRepository <Jikwon, String> {

    // query method
    List<Jikwon> findByBuserName(String buserName);

    // JPQL
    // 평균 연봉 이상 직원
    @Query("select j from Jikwon j where j.pay >= (select avg(j2.pay) from Jikwon j2)")
    List<Jikwon> findByPayGreaterThanEqualAvg();

    // JPQL
    // 담당 고객 한명 이상 직원
    @Query("select j from Jikwon j where (select count(g) from Gogek g where j = g.jikwon) > 0")
    List<Jikwon> findByCountGogek();



}
