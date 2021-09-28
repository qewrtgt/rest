package com.spring.rest.dao.users;


import com.spring.rest.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository

public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        List<User> list = entityManager.createQuery("from User", User.class).getResultList();
        return list;
    }
    @Transactional
    public void addUser(User user) {
        entityManager.persist(user);
    }
    @Transactional
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Transactional(readOnly = true)
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }
    @Transactional
    public void deleteUser(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }
    @Transactional
    public void deleteUserById(long id) {
        User delUser = getUserById(id);
        entityManager.remove(delUser);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByEmail(String email) {
        return entityManager.createQuery(
                        "from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

}

