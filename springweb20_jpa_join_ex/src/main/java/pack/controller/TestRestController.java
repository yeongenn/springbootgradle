package pack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pack.dto.JikwonDto;
import pack.repository.JikwonRepository;

@RestController
@CrossOrigin(origins = "*") // 특정 컨트롤러에만 CORS 적용
public class TestRestController {
	
	@Autowired
	private JikwonRepository jikwonRepository;
	
	// query String 
	@GetMapping("/test")
	public List<JikwonDto> test(@RequestParam("jik") String jik){ 
		List<JikwonDto> jlist = jikwonRepository.findByJik(jik)
				.stream()
				.map(JikwonDto::toDto)
				.toList();
		return jlist;
	}
	
	// path variable
//	@GetMapping("/test/{jikname}")
//	public List<JikwonDto> test(@PathVariable("jikname") String jikname){ // 바인딩 안하면 에러
//		List<JikwonDto> jlist = jikwonRepository.findByJik(jikname)
//								.stream()
//								.map(JikwonDto::toDto)
//								.toList();
//		return jlist;
//	}
	
	// 직급 미입력시 전체 자료 출력되도록
	// Mapping을 따로 하기
	// if문으로 호출되는 쿼리 다르게 하기
	// @RequestParam에 default값 걸어주기 <- 뭘로 줘야 전체 출력되지...?
	
	// @PathVariable에 default값 주기??
	// https://leeborn.tistory.com/entry/Spring-PathVariable-%EA%B8%B0%EB%B3%B8%EA%B0%92-%EC%84%A4%EC%A0%95%ED%95%98%EA%B8%B0
	// https://gyumin-kim.github.io/optional-pathvariable/
	

}
