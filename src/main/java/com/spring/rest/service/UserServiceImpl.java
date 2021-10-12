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

    private UserRepository userRepository;
    private UserDto userDto;
    private UserConverterDto userConverterDto;

    public UserServiceImpl(UserRepository userRepository, UserDto userDto, UserConverterDto userConverterDto) {
        this.userRepository = userRepository;
        this.userDto = userDto;
        this.userConverterDto = userConverterDto;
    }


    @Override
    public List<UserDto> getAllUsersDto() {
        List<User> users = userRepository.findAll();
        List<UserDto> usersDto = new ArrayList<>();
        for (User user : users) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            usersDto.add(userConverterDto.converterUserToUserDto(user));
        }
        return usersDto;
    }

    @Override
    public UserDto getUserDtoById(Long id) {
        System.out.println(id);
        User user = userRepository.getById(id);
        user.setPassword("");
        UserDto userDto = userConverterDto.converterUserToUserDto(user);
        System.out.println(userDto);

        return userDto;
    }

    @Override
    public void saveUserDto(UserDto userDto) {
        User user = userConverterDto.converterUserDtoToUser(userDto);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


    public UserDto loadPrincipalDto(String email) {
        return userConverterDto.converterUserToUserDto((User) loadUserByUsername(email));
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

