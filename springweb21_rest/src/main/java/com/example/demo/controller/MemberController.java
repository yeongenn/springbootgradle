package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MemberDto;
import com.example.demo.repository.MemberDao;

//@Controller
//@ResponseBody
@RestController // (@Controller + @ResponseBody) - 객체 데이터를 JSON 형태로 변환해 반환하는 역할
public class MemberController {

	@Autowired
	private MemberDao dao;
	
//	@GetMapping("/members")
//	public String list(Model model) {
//		List<MemberDto> list = dao.getListAll();
//		model.addAttribute("list", list);
//		return "list";
//	}
	
	/*
	@GetMapping("/members")
	public MemberDto list(Model model) {
		MemberDto dto = new MemberDto();
		dto.setNum(1);
		dto.setName("가나다");
		dto.setAddr("강남구 역삼동");
		return dto;
	}
	
	@GetMapping("/insertform")
	public String insertform() {		
		return "insertform";
	}
	
	@PostMapping("/insert")
	public String insert(@RequestParam("name") String name,
							@RequestParam("addr") String addr) {
		MemberDto dto = new MemberDto();
		dto.setName(name);
		dto.setAddr(addr);
		dao.insert(dto);
		return "redirect:/members"; // 추가 후 목록보기
	}
	*/
	
	// REST 요청 처리 -----------------------------------------------------------------
	
	// GET - 전체 자료 읽어오기
	@GetMapping("/members")
	public List<MemberDto> getList(){
		/*
		 DB 자료를 읽어 JSON 형태로 변환해 
		 클라이언트(자바스크립트 비동기요청)에 반환
		 */
		System.out.println("GET 요청~");
		return dao.getListAll();
	}
	
	// POST 요청 - 추가하기
	@PostMapping("/members")
	public Map<String, Object> insert(@RequestBody MemberDto dto){
		// @RequestBody - 요청 본문(body)에 담긴 값(JSON)을 자바 객체로 변환
		dao.insert(dto);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSuccess", true);
		return map;
	}
	
	// GET - 단일 자료 가져오기
	// 요청명 - http://localhost:80/members/3
	// 동적 변수 맵핑 - {}로 감싸기, 이때 변수명은 임의지정
	// @PathVariable 사용 - @PathVariable("[맵핑할 때 중괄호로 감싼 변수명]")
	@GetMapping("/members/{num}")
	public MemberDto getData(@PathVariable("num") int num){
		
		return dao.getData(num);
	}
	
	// PUT - 수정하기
	@PutMapping("/members/{num}")
	public Map<String, Object> update(@PathVariable("num") int num,
							@RequestBody MemberDto dto){
		dto.setNum(num);
		dao.update(dto);
		
		/*
		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("isSuccess", true);
		 return map;
		 */
		return Map.of("isSuccess", true);
	}
	
	// DELETE - 삭제하기
	@DeleteMapping("/members/{num}")
	public Map<String, Object> delete(@PathVariable("num") int num){
		dao.delete(num);
		return Map.of("isSuccess", true);
	}
}
