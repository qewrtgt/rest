package com.spring.rest.service;

import com.spring.rest.dto.UserConverterDto;
import com.spring.rest.dto.UserDto;
import com.spring.rest.model.User;
import com.spring.rest.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

   /* private UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        return users;
    }

    @Override
    public User getUserById(Long id) {
        User user = userRepository.getById(id);
        user.setPassword("");
        return user;
    }

    @Override
    public void saveUser(User user) {
        if (user.getPassword().equals("")) {
            user.setPassword(loadUserByUsername(user.getEmail()).getPassword());
        } else {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }*/

    private UserRepository userRepository;
    private UserDto userDto;
    private UserConverterDto userConverterDto;

    public UserServiceImpl(UserRepository userRepository, UserDto userDto, UserConverterDto userConverterDto) {
        this.userRepository = userRepository;
        this.userDto = userDto;
        this.userConverterDto = userConverterDto;
    }


    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> usersDto = new ArrayList<>();
        for (User user : users) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            usersDto.add(userConverterDto.converterUserToUserDto(user));
        }
        return usersDto;
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}

