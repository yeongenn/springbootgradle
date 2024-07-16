package pack;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {

    @GetMapping("myinfo")
    public String method1(@RequestParam(value="name") String name,
                          @RequestParam(value="nickName") String nickName,
                          Model model){
        String[] myinfo = {name, nickName};
        model.addAttribute("info", myinfo);
        return "show";
    }
}
