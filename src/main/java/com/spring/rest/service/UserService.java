package com.spring.rest.service;

import com.spring.rest.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<UserDto> getAllUsersDto();

    UserDto getUserDtoById(Long id);

    void saveUserDto(UserDto userDto);

    void deleteUserById(Long id);

    UserDto loadPrincipalDto(String email);

}
