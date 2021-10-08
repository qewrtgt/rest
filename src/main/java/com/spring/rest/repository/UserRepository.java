package com.spring.rest.repository;

import com.spring.rest.model.User;


import com.sun.istack.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("FROM User u JOIN FETCH u.roles AS roles WHERE u.email = :email")
    User findUserByEmail(@Param("email") String email);
}
