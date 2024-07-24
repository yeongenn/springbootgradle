package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.dto.MemberDto;
import pack.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/member/mlist")
	public String memList(Model model) {
		// ...
		memberService.getList(model); // return type void?!
		/*
		 model.addAttribute("key", value);
		 해주지 않아도 mlist 라는 키에 model 값이 담겨서 보내진단다
		 당연하지 getList(Model model) 안에서 수행되니까
		 */
		return "member/mlist";
	}
	
	// insert
	@GetMapping("/member/insertform")
	public String insertform() {
		return "member/insertform";
	}
	
	@PostMapping("member/insert")
	public String insert(MemberDto memBean) { // formbean 안 만들고 dto로~
		
		memberService.insert(memBean);

		return "member/insert";
	}
	
	// update
	@GetMapping("member/updateform")
	public String updateform(@RequestParam("num") Long num, Model model) {
		memberService.getData(num, model);
		return "member/updateform";
	}
	
	@PostMapping("member/update")
	public String update(MemberDto memBean) {
		memberService.update(memBean);

		return "member/update";
	}
	
	// delete
		@GetMapping("member/delete")
		public String delete(@RequestParam("num") Long num, Model model) {
			memberService.delete(num);
			return "redirect:/member/mlist";
		}
}
