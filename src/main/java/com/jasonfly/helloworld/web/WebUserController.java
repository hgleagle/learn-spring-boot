package com.jasonfly.helloworld.web;

import com.jasonfly.helloworld.domain.WebUser;
import com.jasonfly.helloworld.param.WebUserParam;
import com.jasonfly.helloworld.repository.test1.UserTest1Repository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class WebUserController {
    @Resource
    private UserTest1Repository userRepository;

    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    @RequestMapping("/add")
    public String add(@Valid WebUserParam userParam, BindingResult result, ModelMap model) {
        String errorMsg = "";
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                errorMsg = errorMsg + error.getCode() + "-" + error.getDefaultMessage() + ";\n";
            }
            model.addAttribute("errorMsg", errorMsg);
            return "user/userAdd";
        }

        WebUser u = userRepository.findByUserName(userParam.getUserName());
        if (u != null) {
            model.addAttribute("errorMsg", "user already exists.");
            return "user/userAdd";
        }

        WebUser user = new WebUser();
        BeanUtils.copyProperties(userParam, user);
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(new Date());
        user.setRegTime(formattedDate);
        userRepository.save(user);
        return "redirect:/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/userAdd";
    }

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue = "0") Integer page,
                       @RequestParam(value = "szie", defaultValue = "6") Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        Page<WebUser> users = userRepository.findList(pageable);
        model.addAttribute("users", users);
        return "user/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model, Long id) {
        WebUser user = userRepository.findById(id);
        model.addAttribute("user", user);
        return "user/userEdit";
    }

    @RequestMapping("/edit")
    public String edit(@Valid WebUserParam userParam, BindingResult result, ModelMap model) {
        String errorMsg = "";
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                errorMsg = errorMsg + error.getCode() + "-" + error.getDefaultMessage() + ";";
            }
            model.addAttribute("errorMsg", errorMsg);
            model.addAttribute("user", userParam);
            return "user/userEdit";
        }
        System.out.printf("%s %d %d", userParam.getUserName(), userParam.getAge(), userParam.getId());

        WebUser user = new WebUser();
        BeanUtils.copyProperties(userParam, user);
        System.out.println(userParam);
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(new Date());
        user.setRegTime(formattedDate);
        userRepository.save(user);
        return "redirect:/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        userRepository.delete(id);
        return "redirect:/list";
    }
}
