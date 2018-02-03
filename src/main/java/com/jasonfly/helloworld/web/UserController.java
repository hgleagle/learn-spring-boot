package com.jasonfly.helloworld.web;

import com.jasonfly.helloworld.entity.UserEntity;
import com.jasonfly.helloworld.mapper.UserMapper;
import com.jasonfly.helloworld.param.UserParam;
import com.jasonfly.helloworld.result.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/user/getUsers")
    public List<UserEntity> getUsers() {
        List<UserEntity> users=userMapper.getAll();
        return users;
    }

    @RequestMapping("/user/getList")
    public Page<UserEntity> getList(UserParam userParam) {
        List<UserEntity> users=userMapper.getList(userParam);
        long count=userMapper.getCount(userParam);
        Page page = new Page(userParam, count, users);
        return page;
    }

    @RequestMapping("/user/getUser")
    public UserEntity getUser(Long id) {
        UserEntity user=userMapper.getOne(id);
        return user;
    }

    @RequestMapping("/user/add")
    public void save(UserEntity user) {
        userMapper.insert(user);
    }

    @RequestMapping(value="update")
    public void update(UserEntity user) {
        userMapper.update(user);
    }

    @RequestMapping(value="/user/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        userMapper.delete(id);
    }
}
