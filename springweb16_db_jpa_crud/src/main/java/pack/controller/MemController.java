package pack.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pack.model.Mem;
import pack.model.MemProcess;


@Controller
public class MemController {
	@Autowired
	private MemProcess memProcess;
	
	@GetMapping("/")
	public String main() {
		return "start";
	}
	
	@GetMapping("list")
	public String list(Model model) {
		ArrayList<Mem> mlist = (ArrayList<Mem>) memProcess.getDataAll();
		model.addAttribute("list", mlist);		
		return "list";
	}
	
	@GetMapping("insert")
	public String insert() {
		return "insert";
	}
	
//	@PostMapping("insert")
//	public String insertProcess(MemBean bean) {
//		boolean b = memProcess.insert(bean);
//		if(b) return "redirect:/list";
//		else return "redirect:/error";
//	}
	
	@GetMapping("error")
	public String error() {
		return "error";
	}
	
}
