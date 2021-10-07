package com.spring.rest.service;

import com.spring.rest.model.User;
import com.spring.rest.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users =  userRepository.findAll();
        for (User user:users){
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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}

