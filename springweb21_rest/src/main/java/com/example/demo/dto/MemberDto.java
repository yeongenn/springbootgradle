package com.example.demo.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Alias("memberDto") // 객체에 별명을 주고 mapper.xml에서 사용할 수 있다
public class MemberDto {

	private int num;
	private String name;
	private String addr;
}
