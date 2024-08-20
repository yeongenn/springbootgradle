package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import pack.model.BoardDaoImpl;

@Controller
public class InsertController {
	@Autowired
	private BoardDaoImpl daoImpl;
	
	@GetMapping("insert")
	public String insertform() {
		return "insform";
	}
	
	@PostMapping("insert")
	public String insertProcess(BoardBean bean, Model model) {
		// Spring 에서 client ip 가져오는 법 ----------
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		String ip = req.getHeader("X-FORWARDED-FOR");
		if (ip == null) ip = req.getRemoteAddr();
		// ------------------------------------------------
		bean.setBip(ip);
		bean.setBdate();
		int newNum = daoImpl.currentMaxNum() + 1;  // 새글 번호
		bean.setNum(newNum);
		bean.setReadcnt(0);
		bean.setGnum(newNum);
		bean.setOnum(0);
		bean.setNested(0);
		
		String str = daoImpl.insert(bean);
		if(str.equals("success")) {
			return "redirect:/list";  // 추가 후 목록 보기
		}else {
			model.addAttribute("msg", str);
			return "forward:/error";
		}
	}
}
