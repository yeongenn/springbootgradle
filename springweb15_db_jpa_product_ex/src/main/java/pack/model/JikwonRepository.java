package pack.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JikwonRepository extends JpaRepository<Jikwon, Integer>{
	
	List<Jikwon> findByJik(String jik);

}
