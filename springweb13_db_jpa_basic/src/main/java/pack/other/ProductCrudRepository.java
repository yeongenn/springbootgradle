package pack.other;

import org.springframework.data.repository.CrudRepository;

public interface ProductCrudRepository extends CrudRepository<ProductVo, Integer>{
	// CrudRepository<T, ID> - T(Entity), ID(PK 타입)
	// save(), findById(), count(), ... 기본적으로 지원 받음
	// 추가하고 싶은 메서드가 있다면 작성해주면 된다
	
}
