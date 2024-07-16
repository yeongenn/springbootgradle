package pack.controller;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberBean {
	private String id, name, passwd, reg_date;
}
