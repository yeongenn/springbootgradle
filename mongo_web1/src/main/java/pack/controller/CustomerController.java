package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pack.service.CustomerService;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/alldatas")
	public String allDatas() {
		customerService.printAllData();
		return "전체 자료 출력 : 확인은 이클립스 콘솔";
	}
	
	// http://localhost:8080/updatedata?name=홍길동 <- 수정 URI
	@GetMapping("/updatedata")
	public String upDatas(@RequestParam(name="name") String name) {
		customerService.updateData(name);
		return "자료 수정 : 이클립스 콘솔 확인";
	}
	
	// http://localhost:8080/deletedata?name=공기밥 <- 삭제 URI
	@GetMapping("/deletedata")
	public String delDatas(@RequestParam(name="name") String name) {
		customerService.deleteData(name);
		return "자료 삭제 : 이클립스 콘솔 확인";
	}

}
