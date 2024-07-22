package pack.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import pack.model.Board;
import pack.model.BoardDao;

@Controller
public class SearchController {

    @Autowired
    private BoardDao boardDao;

    @PostMapping("search")
    public String search(BoardBean bean, Model model) {
        ArrayList<Board> list = (ArrayList<Board>) boardDao.searchList(bean);
        model.addAttribute("list", list);
        return "list";
    }
}
