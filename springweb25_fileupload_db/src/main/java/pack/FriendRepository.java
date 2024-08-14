package pack;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FriendRepository extends JpaRepository<Friend, Integer>{

	// 번호 최댓값 구하기
	@Query("select max(f.bunho) from Friend f")
	Integer findLastBunho();
}
