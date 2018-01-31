package com.jasonfly.helloworld.web;

import com.jasonfly.helloworld.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ExampleController {
    @RequestMapping("/example")
    public String index(ModelMap map) {
        map.addAttribute("userName", "jasonfly");
        map.addAttribute("flag", "yes");
        map.addAttribute("users", getUserList());
        map.addAttribute("type", "link");
        map.addAttribute("pageId", "springcloud/2017/09/11");
        map.addAttribute("img", "http://www.ityouknow.com/assets/images/neo.jpg");
        map.addAttribute("count", 12);
        map.addAttribute("date", new Date());
        return "example";
    }

    private List<User> getUserList() {
        List<User> list = new ArrayList<User>();
        User user1 = new User("cow", 12, "123455");
        User user2 = new User("bull", 12, "123455");
        list.add(user1);
        list.add(user2);
        return list;
    }
}
