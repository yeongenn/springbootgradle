package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pack.model.BoardDao;

@Controller
public class InsertController {

	@Autowired
	private BoardDao boardDao;
	
	//@RequestMapping(value="insert", method = RequestMethod.GET)
	@GetMapping("insert")
	public String insert() {
		
		return "insform";
	}
	
	@RequestMapping(value="insert", method = RequestMethod.POST)
	public String insertSubmit(BoardBean bean) {
		boolean b = boardDao.insertData(bean);
		
		if(b) {
			return "redirect:/list";
		} else {
			return "error";
		}
	}
	
}
