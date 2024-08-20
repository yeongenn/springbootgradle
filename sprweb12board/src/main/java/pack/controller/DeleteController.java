package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pack.model.BoardDaoImpl;

@Controller
public class DeleteController {
	@Autowired
	private BoardDaoImpl daoImpl;
	
	@GetMapping("delete")
	public String del(@RequestParam("num")int num,
			@RequestParam("page")String page, Model model) {
		// 사실 비번 삭제는 비밀번호 비교해야 함. 생략...
		String msg = daoImpl.delete(num);
		if(msg.equals("success"))
			return "redirect:list?page=" + page;
		else
			model.addAttribute("msg", msg);
			return "error";
	}
}
