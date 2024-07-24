package pack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pack.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	// query method naming rule
	public List<Member> findAllByOrderByNumDesc();
}
