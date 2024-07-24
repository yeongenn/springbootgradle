package pack.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pack.entity.Dept;
import pack.entity.Emp;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeptDto {
	
	/*
	 * 엔티티에 선언된 필드 중 필요한 필드만 사용 가능
	 */

	private int deptno;

	private String dname;

	private String loc;

	private int count; // 근무 인원수 저장
	private List<String> names; // 근무 직원 이름들

	// Entity to DTO
	public static DeptDto toDto(Dept dept) {
		List<String> names = new ArrayList<String>();
		for (Emp temp : dept.getEmpList()) {
			names.add(temp.getEname());
		}

		// 메서드 체이닝 통해 생성자로 주입, 원하는 필드만 입력 가능
		return DeptDto.builder()
				.deptno(dept.getDeptno())
				.dname(dept.getDname())
				.loc(dept.getLoc())
				.count(dept.getEmpList().size())
				.names(names)
				.build();
	}
}
