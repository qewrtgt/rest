package com.spring.rest.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class UserDto implements Comparable<UserDto>{
        private Long id;
        private String firstName;
        private String lastName;
        private int age;
        private String email;
        private List<String> roles;
        private String password;

    @Override
    public int compareTo(@NotNull UserDto userDto) {
        return id.compareTo(userDto.getId());
    }
}
