package pack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pack.entity.Emp;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmpDto {
	
	
	private Integer empno;

	private String ename;

	private String job;

	private Integer deptno;
	
	private String dname;
	
	// Entity to DTO
	public static EmpDto toDto(Emp emp) {
		return EmpDto.builder()
				.empno(emp.getEmpno())
				.ename(emp.getEname())
				.job(emp.getJob())
				.deptno(emp.getDept().getDeptno())
				.dname(emp.getDept().getDname())
				.build();
	}
}
