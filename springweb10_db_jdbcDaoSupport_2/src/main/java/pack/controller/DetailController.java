package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.model.MemberDao;
import pack.model.MemberDto;

@Controller
public class DetailController {
	
	@Autowired
	private MemberDao memberDao;
	
	@GetMapping("detail")
	public String detailProcess(@RequestParam(value="id") String id, Model model) {
		MemberDto dto = memberDao.getMember(id);
		model.addAttribute("member", dto);
		return "detail";
	}
}
