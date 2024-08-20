package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pack.model.Board;
import pack.model.BoardDaoImpl;

@Controller
public class UpdateController {
	@Autowired
	private BoardDaoImpl daoImpl;
	
	@GetMapping("update")
	public String edit(@RequestParam("num")int num, 
			@RequestParam("page")String page,
			Model model) {
		// 수정 대상 자료 읽기
		Board dto = daoImpl.detail(num);
		
		model.addAttribute("data", dto);
		model.addAttribute("page", page);
		
		return "update";
	}

	@PostMapping("update")
	public String editProcess(BoardBean bean, 
			@RequestParam("page") String page, 
			Model model) {
		// 비밀번호 확인을 위해 DB에서 비밀번호 읽기
		String pass = daoImpl.selectPass(bean.getNum());
		System.out.println("bean.getPass:" + bean.getPass() + " pass : " + pass);

		if(bean.getPass().equals(pass) || bean.getPass() == pass) {  // 비밀번호 비교 
			String str = daoImpl.update(bean);
			
			if(str.equals("success")) {
				// 상세보기로 이동
				//return "redirect:detail?num=" + bean.getNum() + "&page=" + (page + 1);

				// 목록보기로 이동
				//int currentPage = Integer.parseInt(page);  // 문자열을 정수로 변환
				//return "redirect:/list?page=" + (currentPage + 1);  // 수정 후 목록 보기
				// 페이지 번호를 증가시키지 않고 원래 페이지로 이동
	            return "redirect:/list?page=" + page;
			}else {
				model.addAttribute("msg", str);
				return "forward:/error";
			}
		}else{
			model.addAttribute("page", page);
			return "forward:/update_err";
		}
	}
}
