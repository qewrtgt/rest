package com.spring.rest.controller;

import com.spring.rest.dto.UserDto;
import com.spring.rest.model.Role;
import com.spring.rest.repository.RoleRepository;
import com.spring.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class Rest {

    UserService userService;
   @Autowired
    RoleRepository roleRepository;

    public Rest(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsersDto() {
        List<UserDto> list = userService.getAllUsersDto();
        return list != null && !list.isEmpty()
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getUserDtoById(@PathVariable(name = "id") long id) {
        System.out.println("Rest" + id);
        UserDto userDto = userService.getUserDtoById(id);
        return userDto != null
                ? new ResponseEntity<>(userDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto) {
        System.out.println("Edit" + userDto);
        userService.saveUserDto(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping(value = "")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        userService.saveUserDto(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value ="/principal")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getPrincipal() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails);
        return userService.loadPrincipalDto(userDetails.getUsername());
    }

    @GetMapping(value ="/allroles")
    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }
}
