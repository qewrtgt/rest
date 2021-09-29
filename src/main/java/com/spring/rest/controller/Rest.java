package com.spring.rest.controller;

import com.spring.rest.model.User;
import com.spring.rest.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest")
public class Rest {
    @Autowired
    UserService userService;

    public Rest(UserService userService) {
        this.userService = userService;
    }

    public Rest() {
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        final List<User> list = userService.getAllUsers();
        return list != null && !list.isEmpty()
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "id") long id) {
        User user = userService.getUserById(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(name = "id") long id,
                                        @RequestBody User user) {
//        final boolean updatedUser = userService.updateUserById(user, id);
//        return updatedUser
//                ? new ResponseEntity<>(HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        userService.updateUser(user,null);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping(value = "")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") long id){
        final boolean deletedUser = userService.deleteUserById(id);
        return deletedUser
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
