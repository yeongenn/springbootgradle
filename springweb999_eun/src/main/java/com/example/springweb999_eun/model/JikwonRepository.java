package com.example.springweb999_eun.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JikwonRepository extends JpaRepository <Jikwon, String> {

    // query method
    List<Jikwon> findByBuserName(String buserName);

    // JPQL
    @Query("select j from Jikwon j where j.pay >= (select avg(j2.pay) from Jikwon j2)")
    List<Jikwon> findByPayGreaterThanEqualAvg();

    //
    @Query("select j from Jikwon j where (select count(g) from Gogek g where j = g.jikwon) > 0")
    List<Jikwon> findByCountGogek();



}
