package pack.model;

import org.springframework.data.jpa.repository.JpaRepository;

import pack.entity.Sangpum;

public interface DataRepository extends JpaRepository<Sangpum, Integer>{

	
}
