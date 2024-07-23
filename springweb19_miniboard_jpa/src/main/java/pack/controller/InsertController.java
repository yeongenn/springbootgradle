package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pack.model.BoardDao;

@Controller
public class InsertController {

    @Autowired
    private BoardDao boardDao;

    @GetMapping("insert")
    public String insert() {

        return "insform";
    }

    
    @PostMapping("insert")
    public String insertSubmit(BoardBean bean, Model model) {
        String s = boardDao.insertData(bean);

        if(s.equals("success")) {
            return "redirect:/list";
        } else {
        	model.addAttribute("msg", s);
            return "error";
        }
    }
    

}
