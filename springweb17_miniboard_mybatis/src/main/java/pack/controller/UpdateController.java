package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pack.model.BoardDao;

@Controller
public class UpdateController {

    @Autowired
    private BoardDao boardDao;

    @PostMapping("update")
    public String updateSubmit(BoardBean bean) {
        boolean b = boardDao.updateData(bean);
        if(b) {
            return "redirect:/list";
        } else {
            return "error";
        }
    }

}
