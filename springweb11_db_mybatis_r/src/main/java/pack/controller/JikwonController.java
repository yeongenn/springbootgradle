package pack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.model.DataDao;
import pack.model.GogekDto;
import pack.model.JikwonDto;

@Controller
public class JikwonController {

	@Autowired
	private DataDao dataDao;
	
	@GetMapping("jikwon")
	public String ourJikwon(Model model) {
		List<JikwonDto> jList = dataDao.getJikwonAll();
		model.addAttribute("list", jList);
		return "result";
	}
	
	@GetMapping("mygogek")
	public String myGogekList(Model model,
								@RequestParam(value="no") String no) {
		List<GogekDto> gList = dataDao.getMyGogek(no);
		model.addAttribute("list", gList);
//		model.addAttribute("list", "메롱메롱");
		return "gogekList";
	}

	@GetMapping("buser")
	public String listByBuser(@RequestParam(value="depName") String depName,
							  Model model){
		List<JikwonDto> list = dataDao.getByBuser(depName);
		model.addAttribute("list", list);
		return "buserList";
	}
}
