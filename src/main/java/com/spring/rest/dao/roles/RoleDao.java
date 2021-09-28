package com.spring.rest.dao.roles;


import com.spring.rest.model.Role;

import java.util.List;

public interface RoleDao{
   List<Role> getAllRoles();

   void addRole(Role role);

   void updateRole(Role role);

   Role getRoleById(long id);

   void deleteRole(Role role);

   void deleteRoleById(long id);

   Role getRoleByName(String name);

}
