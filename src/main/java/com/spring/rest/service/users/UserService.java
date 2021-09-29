package com.spring.rest.service.users;



import com.spring.rest.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void addUser(User user);

    boolean updateUserById(User user, long id);

    void updateUser(User user, String[] selectedRoles);

    User getUserById(Long id);

    void deleteUser(User user);

    boolean deleteUserById(long id);

    User getUserByEmail(String email);

    String getRolesString(User user);

}
