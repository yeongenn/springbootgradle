package com.example.springweb999_eun.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GogekRepository extends JpaRepository<Gogek, String> {

    // query method
    List<Gogek> findByJikwonNo(String jikwonNo);
}
