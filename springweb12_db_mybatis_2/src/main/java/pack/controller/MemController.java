package pack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemController {

	/*	 
	 요청값에 따라 컨트롤러 클래스 따로 만들어도 되고,
	 하나의 컨트롤러 클래스 안에 메서드로 만들어도 된다
	 편한대로~~~
	 */
	
	// localhost:8080만 입력하면 start.html을 만난당
	@GetMapping("/")
	public String start() {
		return "start";
	}
}
