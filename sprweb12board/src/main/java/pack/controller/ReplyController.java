package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import pack.model.BoardDaoImpl;

@Controller
public class ReplyController {
	@Autowired
	private BoardDaoImpl daoImpl;
	
	@GetMapping("reply")
	public String reply(@RequestParam("num")int num, 
						@RequestParam("page")String page,
						Model model) {
		model.addAttribute("data", daoImpl.detail(num));
		model.addAttribute("page", page);
		return "reply";
	}
	
	@PostMapping("reply")
	public String replyProcess(BoardBean bean, 
			@RequestParam("page")String page,
			Model model){
		// onum 갱신
		System.out.println("1--------" + bean.getOnum() + 1);
		bean.setOnum(bean.getOnum() + 1);
		daoImpl.updateOnum(bean);
		
		// 댓글 저장 처리 시작 -----------------------
		bean.setNum(daoImpl.currentMaxNum() + 1);

		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		String ip = req.getHeader("X-FORWARDED-FOR");
		if (ip == null) ip = req.getRemoteAddr();
		bean.setBip(ip);
		
		bean.setBdate();
		bean.setNested(bean.getNested() + 1);
		
		String str = daoImpl.insertReply(bean);    // 저장 처리
		if(str.equals("success")) {
			return "redirect:/list?page=" + page;  // 댓글 추가 후 목록 보기
		}else {
			model.addAttribute("msg", str);
			return "forward:/error";
		}
	}
}
