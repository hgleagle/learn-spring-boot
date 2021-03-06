package com.jasonfly.helloworld.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @RequestMapping("/message")
    public String index(ModelMap map) {
        map.addAttribute("message", "http://jasonfly.me");
        return "hello";
    }

}
