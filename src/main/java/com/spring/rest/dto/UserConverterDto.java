package com.spring.rest.dto;

import com.spring.rest.model.User;
import com.spring.rest.repository.RoleRepository;
import org.springframework.stereotype.Component;

@Component
public class UserConverterDto {

    private RoleRepository roleRepository;

    public UserConverterDto(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public UserDto converterUserToUserDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setAge(user.getAge());
        userDto.setPassword(user.getPassword());

        userDto.setRoles(user.getRoles());
        return userDto;
    }

    public User converterUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setAge(userDto.getAge());
        user.setPassword(userDto.getPassword());


        user.setRoles(userDto.getRoles());
        return user;
    }

}
