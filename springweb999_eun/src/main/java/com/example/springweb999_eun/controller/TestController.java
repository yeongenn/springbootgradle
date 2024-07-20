package com.example.springweb999_eun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/")
    public String main(Model model){
        return "main";
    }

    @GetMapping("test")
    public String test(Model model){
        model.addAttribute("messages", "test for upcoming review~");
        return "result";
    }
}
