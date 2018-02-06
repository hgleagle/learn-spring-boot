package com.jasonfly.helloworld.web;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @RequestMapping("/hello")
    @Cacheable(value = "hellocache")
    public String hello(String name) {
        System.out.println("don't go through cache");
        return "Hello World " + name;
    }

    @RequestMapping("/condition")
    @Cacheable(value = "condition", condition = "#name.length() <= 4")
    public String conditon(String name) {
        System.out.println("don't go through cache");
        return "helo " + name;
    }
}
