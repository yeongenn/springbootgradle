package pack.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.model.DataDao;
import pack.model.JikwonDto;

@Controller
public class JikwonController {
	@Autowired
	private DataDao dataDao;
	
	@PostMapping("jikwon")
	public String listProcess(Model model,
								@RequestParam(value="jik") String jik) {
		ArrayList<JikwonDto> list = dataDao.jikwonByJik(jik);
		model.addAttribute("datas", list);
		return "list";
	}
}
