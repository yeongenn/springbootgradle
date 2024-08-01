package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dto.MemberDto;
import com.example.demo.repository.MemberDao;

@Controller
public class MemberController {

	@Autowired
	private MemberDao dao;
	
	@GetMapping("/members")
	public String list(Model model) {
		List<MemberDto> list = dao.getListAll();
		model.addAttribute("list", list);
		return "list";
	}
}
