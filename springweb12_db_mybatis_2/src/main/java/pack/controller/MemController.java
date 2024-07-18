package pack.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.model.DataProcess;
import pack.model.MemDto;

@Controller
public class MemController {

	/*	 
	 요청값에 따라 컨트롤러 클래스 따로 만들어도 되고,
	 하나의 컨트롤러 클래스 안에 메서드로 만들어도 된다
	 편한대로~~~
	 */
	
	@Autowired
	private DataProcess dataProcess;
	
	// localhost:8080만 입력하면 start.html을 만난당
	@GetMapping("/")
	public String start() {
		return "start";
	}
	
	@GetMapping("list")
	public String list(Model model) {
		ArrayList<MemDto> list = (ArrayList<MemDto>) dataProcess.getDataAll();
		model.addAttribute("list", list);
		return "list";
	}
	
	@GetMapping("insert")
	public String insert() {
		return "insert";
	}
	
	@PostMapping("insert")
	public String insertProcess(MemBean bean) {
		boolean b = dataProcess.insert(bean);
		if(b) return "redirect:/list";
		else return "redirect:/error";
	}
	
	@GetMapping("error")
	public String error() {
		return "error";
	}
	
	@GetMapping("update")
	public String update(@RequestParam("num") String num, Model model) {
		MemDto dto = dataProcess.getData(num);
		model.addAttribute("data", dto);
		return "update";
	}
	
	@PostMapping("update")
	public String updateProcess(MemBean bean) {
		boolean b = dataProcess.update(bean);
		if(b) return "redirect:/list";
		else return "redirect:/error";
	}
	
	@GetMapping("delete")
	public String delete(@RequestParam("num") String num) {
		boolean b = dataProcess.delete(num);
		if(b) return "redirect:/list";
		else return "redirect:/error";
	}
}
