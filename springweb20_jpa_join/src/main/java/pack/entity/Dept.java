package pack.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
//@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Dept {
	
	@Id
	private int deptno;
	
	private String dname;
	
	private String loc;
	
	@OneToMany(mappedBy = "dept", fetch = FetchType.EAGER) // fetch = FetchType.EAGER
	@Builder.Default // Emp 엔티티가 생성될 때 empList를 초기화
	private List<Emp> empList = new ArrayList<Emp>();
}
