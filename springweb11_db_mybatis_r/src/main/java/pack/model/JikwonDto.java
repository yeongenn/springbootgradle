package pack.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JikwonDto {
	
	private String no, name, jik, rating;
	
	// 이 방법으로?!
	// https://taegyunwoo.github.io/mybatis/MyBatis_MyBatisFunction#1%EB%8C%80%EB%8B%A4-%EA%B4%80%EA%B3%84-%EC%B2%98%EB%A6%AC-many
	private List<GogekDto> gogekList;

}
