package com.spring.rest.dao.users;



import com.spring.rest.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void addUser(User user);

    void updateUser(User user);

    User getUserById(long id);

    void deleteUser(User user);

    void deleteUserById(long id);

    User getUserByEmail(String firstname);
}
