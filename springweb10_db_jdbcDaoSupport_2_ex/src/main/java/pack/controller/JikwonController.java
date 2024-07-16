package pack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.model.DataDao;
import pack.model.DataDao2;
import pack.model.JikwonDto;

@Controller
public class JikwonController {
	
	@Autowired
	private DataDao dataDao;
	
//	@Autowired
//	private DataDao2 dataDao;
	
	@PostMapping("jikwon")
	public String getJikwonByJik(Model model,
								@RequestParam(value="jik") String jik) {
		List<JikwonDto> list = dataDao.jikwonByJik(jik);
		model.addAttribute("datas", list);
		return "list";
		
	}

}
