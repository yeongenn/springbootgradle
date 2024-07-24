package pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pack.entity.Dept;

@Repository
public interface DeptRepository extends JpaRepository<Dept, Integer> {
	/*
	 int - primitive type
	 Integer - reference type
	 */
}
