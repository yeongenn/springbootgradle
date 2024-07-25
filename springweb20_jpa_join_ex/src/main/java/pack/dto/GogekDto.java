package pack.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pack.entity.Gogek;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GogekDto {
	
	// 고객번호, 고객명, 성별, 나이, 전화
	
	private int gno;
	
	private String gname;
	
	private String gtel;
	
	private int age;
	
	private String gender;
	
	public static GogekDto toDto(Gogek gogek) {
		// 고객 나이
		// 2000년대 ?
		int age = LocalDate.now().getYear() - Integer.parseInt("19" + gogek.getJumin().substring(0, 2));
		
		// 고객 성별
		String gender = "";
		int a = Integer.parseInt(gogek.getJumin().substring(7, 8));
		if(a == 1 || a == 3) {
			gender = "남";
		} else if(a == 2 || a == 4) {
			gender = "여";
		} else {
			
		}
		
		// Entity to DTO
		return GogekDto.builder()
				.gno(gogek.getGno())
				.gname(gogek.getGname())
				.gtel(gogek.getGtel())
				.age(age)
				.gender(gender)
				.build();
		
	}
}
