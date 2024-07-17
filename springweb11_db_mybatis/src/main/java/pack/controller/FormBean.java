package pack.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormBean {
	private String code, sang, su, dan; // 추가, 수정 시에 필요
	private String searchValue; // 검색용~
}
