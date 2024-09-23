package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.MatchRepository;
import com.example.demo.model.MatchResultDto;

@Controller
public class MatchController {

	@Autowired
	private MatchRepository matchRepository;
	
	@GetMapping("/")
	public String main() {
		return "test";
	}
	
	@ResponseBody
	@GetMapping("/test1")
	public List<MatchResultDto> byYear(){
		
		List<MatchResultDto> list = matchRepository.matchResultByYear("ULS", "ULS", "2024");
		return list;
	}
}
