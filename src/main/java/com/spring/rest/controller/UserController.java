package com.spring.rest.controller;

import com.spring.rest.model.User;
import com.spring.rest.service.users.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user")
    public String getAllUsers(ModelMap model) {
        User autorizedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("authorizedUser", autorizedUser);
        model.addAttribute("userRolesStr", userService.getRolesString(autorizedUser));
        return "users/person";
    }
}
