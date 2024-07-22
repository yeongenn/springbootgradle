package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import pack.model.Board;
import pack.model.BoardDao;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DetailController {

    @Autowired
    private BoardDao boardDao;

    @RequestMapping("detail")
    public String detail(@RequestParam("num") String num, Model model) {
        Board board = boardDao.detail(num);
        model.addAttribute("board", board);
        return "detail";
    }


}
