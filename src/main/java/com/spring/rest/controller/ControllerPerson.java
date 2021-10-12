package com.spring.rest.controller;

import com.spring.rest.model.Role;
import com.spring.rest.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "")

public class ControllerPerson {

    @GetMapping(value = "")
    public String getPerson() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (role.getName().contains("ADMIN")) {
                return "/admin";
            }
        }
        return "/person";
    }
}