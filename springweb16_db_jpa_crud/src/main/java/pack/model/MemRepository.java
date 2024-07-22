package pack.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemRepository extends JpaRepository<Mem, Integer>{
	
	// pk auto_increment
	@Query("select max(m.num) from Mem m")
	//@Query(value="select max(num) from mem", nativeQuery = true)
	int findByMaxNum();
	
	@Query("select m from Mem m where m.num = ?1")
	Mem findByNum(String num);

}
