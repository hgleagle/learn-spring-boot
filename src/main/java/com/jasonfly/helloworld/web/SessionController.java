package com.jasonfly.helloworld.web;

import com.jasonfly.helloworld.entity.UserEntity;
import com.jasonfly.helloworld.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SessionController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/setSession", method = RequestMethod.GET)
    public Map<String, Object> setSession(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        request.getSession().setAttribute("message", request.getRequestURL());
        map.put("request Url", request.getRequestURL());
        return  map;
    }

    @RequestMapping(value = "/getSession", method = RequestMethod.GET)
    public Object getSession(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("message"));
        return  map;
    }

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, String userName, String password) {
        String msg = "logon failure";
        List<UserEntity> users = userMapper.getByName(userName);
        for(UserEntity user : users) {
            if (user.getPassWord().equals(password)) {
                request.getSession().setAttribute("user", user);
                msg = "login successfull";
            }
        }

        return msg;
    }

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request) {
        String msg = "index content";
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            msg = "please login first!";
        }
        return msg;
    }
}
