package pack.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.model.DataDao;
import pack.model.Jikwon;

@Controller
public class JikwonController {
	
	@Autowired
	private DataDao dataDao;

	@GetMapping("/")
	public String main() {
		return "main";
	}
	
	@GetMapping("jikwon")
	public String jikwon(@RequestParam("jik") String jik, Model model) {
		ArrayList<Jikwon> jikwonList = (ArrayList<Jikwon>) dataDao.getJikwonByJik(jik);
		model.addAttribute("datas", jikwonList);
		return "list";
	}
}
