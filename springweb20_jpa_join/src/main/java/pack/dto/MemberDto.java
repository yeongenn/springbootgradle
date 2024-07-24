package pack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pack.entity.Member;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

	private Long num;
	private String name;
	private String addr;

	// Entity to DTO
	public static MemberDto toDto(Member member) {
		return MemberDto.builder()
				.num(member.getNum())
				.name(member.getName())
				.addr(member.getAddr())
				.build();
	}

}
