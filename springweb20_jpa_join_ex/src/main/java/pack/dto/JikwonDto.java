package pack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pack.entity.Jikwon;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JikwonDto {
	
	// 사번, 이름, 직급, 부서명, 관리고객여부
	private int jno;
	
	private String jname;
	
	private String jik;
	
	private String bname;
	
	private String hasGogek;
	
	private int pay;
	
	public static JikwonDto toDto(Jikwon jikwon) {
		// Entity to DTO
		return JikwonDto.builder()
				.jno(jikwon.getJno())
				.jname(jikwon.getJname())
				.jik(jikwon.getJik())
				.pay(jikwon.getPay())
				.bname(jikwon.getBuser().getBname())
				.hasGogek((jikwon.getGogekList().size() > 0) ? "O" : "X")
				.build();
	}
	
	
	
}
