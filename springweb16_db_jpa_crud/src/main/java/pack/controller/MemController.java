package pack.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@PostMapping("insert")
	public String insertProcess(MemBean bean, Model model) {
		String msg = memProcess.insert(bean);
		if(msg.equals("성공")) {
			return "redirect:/list";			
		} else {
			model.addAttribute("msg", msg);
			return "error";
		}
	}
	
	@GetMapping("error")
	public String error() {
		return "error";
	}
	
	@GetMapping("update")
	public String update(@RequestParam("num") String num, Model model) {
		//System.out.println("num : " + num);
		Mem mem = memProcess.getData(num);
		model.addAttribute("data", mem);
		return "update";
	}
	
	@PostMapping("update")
	public String updateProcess(MemBean bean, Model model) {
		String msg = memProcess.update(bean);
		if(msg.equals("성공")) {
			return "redirect:/list";			
		} else {
			model.addAttribute("msg", msg);
			return "error";
		}
	}
	
	@GetMapping("delete")
	public String deleteProcess(@RequestParam("num") int num, Model model) {
		String msg = memProcess.delete(num);
		if(msg.equals("성공")) {
			return "redirect:/list";			
		} else {
			model.addAttribute("msg", msg);
			return "error";
		}
	}
}
