package com.spring.rest.service;

import com.spring.rest.dao.UserRepository;
import com.spring.rest.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user, String[] selectedRoles) {
    }

    @Override
    public User getUserById(int id) {
        User user = null;
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            user = userOptional.get();
        }
        return user;
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public String getRolesString(User user) {
        return null;
    }
}
