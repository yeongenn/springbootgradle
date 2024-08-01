package com.example.springweb999_eun.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JikwonDto {

	private String no;
	private String name;
	private int pay;
//	private Buser buser;
//	private List<Gogek> gogekList;
	
	public static JikwonDto toDto(Jikwon jikwon) {
		return JikwonDto.builder()
						.no(jikwon.getNo())
						.name(jikwon.getName())
						.pay(jikwon.getPay())
						.build();
	}
}
