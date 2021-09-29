package com.spring.rest.dao.users;



import com.spring.rest.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void addUser(User user);

    void updateUser(User user);

    boolean updateUserById(User user, long id);

    User getUserById(long id);

    void deleteUser(User user);

    boolean deleteUserById(long id);

    User getUserByEmail(String firstname);
}
