package com.hjm.controller;

import com.hjm.model.User;
import com.hjm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CheckClientController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public ModelAndView login(String name, String password){
        ModelAndView mav = new ModelAndView();
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user = userService.getOne(user);
        if (user!=null){
            mav.setViewName("client");
            mav.addObject("user", user);
            return mav;
        }
        mav.setViewName("error");
        return mav;
    }
}
