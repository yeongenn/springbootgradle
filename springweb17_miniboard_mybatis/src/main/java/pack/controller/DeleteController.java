package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pack.model.BoardDao;

@Controller
public class DeleteController {

    @Autowired
    private BoardDao boardDao;

    @GetMapping("delete")
    public String deleteSubmit(BoardBean bean) {
        boolean b = boardDao.deleteData(bean);
        if(b) {
            return "redirect:/list";
        } else {
            return "error";
        }
    }

}
