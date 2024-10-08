package com.example.springweb999_eun.controller;

import com.example.springweb999_eun.model.Gogek;
import com.example.springweb999_eun.model.Jikwon;
import com.example.springweb999_eun.model.JikwonDto;
import com.example.springweb999_eun.model.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestController {
    @Autowired
    private TestDao testDao;

    @GetMapping("/")
    public String main(Model model){
        return "main";
    }

    @GetMapping("test")
    public String test(Model model){
        model.addAttribute("messages", "test for upcoming review~");
        return "result";
    }

    @GetMapping("test2")
    public String test2(Model model,
                        @RequestParam("buserName") String buserName){
        List<Jikwon> jikwonList = testDao.getJikwonByBuser(buserName);
        model.addAttribute("datas", jikwonList);
        return "list";
    }

    @GetMapping("test3")
    public String test3(Model model,
                        @RequestParam(value="no", defaultValue = "0") String jikwonNo){
        List<Gogek> gogekList = testDao.getGogekByJikwon(jikwonNo);
        model.addAttribute("datas", gogekList);
        return "list2";
    }
    
    // 240801
    // react + springboot
    @GetMapping("/test4")
    @ResponseBody
    public List<JikwonDto> test4(){   
    	List<JikwonDto> list = testDao.getJikwonOverAvgPay()
    							.stream()
    							.map(JikwonDto::toDto)
    							.toList();
    	/*
    		반환 타입을 Entity로 지정하니까 데이터 무한 생성 에러
    		Could not write JSON: Document nesting depth (1001) exceeds the maximum allowed
    	*/
    	return list;
    }
    
    
}
