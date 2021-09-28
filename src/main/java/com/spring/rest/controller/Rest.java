package com.spring.rest.controller;

import com.spring.rest.model.User;
import com.spring.rest.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/rest")
public class Rest {
    @Autowired
    UserService userService;

    @GetMapping()
    public List<User>getAllusers(){
        List<User> list = userService.getAllUsers();
        System.out.println(list );
        return list;
    }
}
