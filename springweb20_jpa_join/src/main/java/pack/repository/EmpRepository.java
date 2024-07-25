package pack.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pack.entity.Emp;

@Repository
public interface EmpRepository extends JpaRepository<Emp, Integer> {
	// query method
	public List<Emp> findAllByOrderByEmpnoAsc();
	public List<Emp> findAllByOrderByEmpnoDesc();
	
	// JPQL
	@Query("select e from Emp e order by e.empno asc")
	public List<Emp> findAllList();
	
	// parameter binding
	@Query("select e from Emp e where e.sal > :salary order by e.sal asc")
	List<Emp> findList(@Param("salary") double salary);
	
	// 이름기반
	@Query("select e from Emp e where e.sal > :sal1 and e.sal < :sal2 order by e.sal asc")
	List<Emp> findListBetween(@Param("sal1") int salary1, @Param("sal2") int salary2);
	
	// 위치기반
	@Query("select e from Emp e where e.sal > ?1 and e.sal < ?2 order by e.sal asc")
	List<Emp> findListBetween2(int salary1, int salary2);

	// FETCH JOIN 확인용
	@Query("select e from Emp e join fetch e.dept d where e.empno = ?1")
	Optional<Emp> getById(int empno);
}
