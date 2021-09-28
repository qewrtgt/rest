package com.spring.rest.service.users;



import com.spring.rest.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void addUser(User user);

    void updateUser(User user, String[] selectedRoles);

    User getUserById(Long id);

    void deleteUser(User user);

    void deleteUserById(long id);

    User getUserByEmail(String email);

    String getRolesString(User user);

}
