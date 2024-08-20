package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pack.model.Board;
import pack.model.BoardDaoImpl;

@Controller
public class ListController {
	@Autowired
	private BoardDaoImpl daoImpl;
	
	@GetMapping("list")
	public String listProcess(@RequestParam(required = false, defaultValue = "0", value = "page")int page, Model model) {
		// paging 처리를 함 -----------------
		Page<Board> list = daoImpl.listAll(page);

		int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("data", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        // ---------------------------------------
		
		return "list";
	}
	
	@GetMapping("search")
	public String searchProcess(BoardBean bean, Model model) {
		System.out.println("searchProcess : " + bean.getSearchName() + " " + bean.getSearchValue());
		Page<Board> list = daoImpl.search(bean);
		
		int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());
        
		model.addAttribute("data", list);
		model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage); 
		return "list";
	}
}
