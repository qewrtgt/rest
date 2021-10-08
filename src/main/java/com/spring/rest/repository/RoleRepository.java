package com.spring.rest.repository;

import com.spring.rest.model.Role;
import com.spring.rest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);

//    @Query("FROM Role r  WHERE r.name = :name")
//    Role findRoleByRoleName(@Param("name") String name);
}
