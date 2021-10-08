package com.spring.rest.dto;

import com.spring.rest.model.Role;
import com.spring.rest.model.User;
import com.spring.rest.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

//        List<String> roles = user.getRoles().stream()
//                .map(roleRepository::findRoleByRoleName)
//                .collect(Collectors.toList());
        List<String> roles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            roles.add(role.getName());
        }



        userDto.setRoles(roles);
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

//        Set<Role> roles = userDto.getRoles().stream()
//                .map(role -> role.getName())
//                .collect(Collectors.toSet());

        Set<Role> roles = null;
        for (String roleName : userDto.getRoles()) {
            roles.add(roleRepository.findRoleByName(roleName));
        }
        return user;
    }

}
