package pack.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pack.model.Board;
import pack.model.BoardDao;

@Controller
public class ListController {

    @Autowired
    private BoardDao boardDao;

    @GetMapping("list")
    public String list(Model model) {
        ArrayList<Board> list = (ArrayList<Board>) boardDao.list();
        model.addAttribute("list", list);
        return "list";
    }
    
    @PostMapping("search")
    public String search(Model model, BoardBean bean) {
    	ArrayList<Board> slist = (ArrayList<Board>) boardDao.searchList(bean);
        model.addAttribute("list", slist);
        return "list";
    }
}