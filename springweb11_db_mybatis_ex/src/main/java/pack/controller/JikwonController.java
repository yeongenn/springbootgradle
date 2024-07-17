package pack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.model.DataDao;
import pack.model.JikwonDto;

@Controller
public class JikwonController {
	
	@Autowired
	private DataDao dataDao;
	
	// index.html을 static에 안 넣고 tmeplates에 넣었을 때
	// localhost 띄웠을 때 index.html 불러오기
	/*
	@GetMapping("/")
	public String start() {
		return "index";
	}
	*/
	
	
	@GetMapping("jikwon")
	public String getJikwonByJik(Model model,
								@RequestParam(value="jik") String jik) {
		List<JikwonDto> jikwonList = dataDao.getJikwon(jik);
		model.addAttribute("datas", jikwonList);
		return "list";
		
	}
	
	@GetMapping("test")
	public String getJikwonAll(Model model) {
		List<JikwonDto> list = dataDao.getJikwonAll();
		model.addAttribute("datas", list);
		return "testList";
	}

}
