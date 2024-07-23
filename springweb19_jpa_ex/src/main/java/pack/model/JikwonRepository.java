package pack.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JikwonRepository extends JpaRepository<Jikwon, Integer>{
	
	List<Jikwon> findByBuserNum(int buserNum);
	
	List<Jikwon> findByBuserNumAndJikwonRating(int buserNum, String rating);

}
