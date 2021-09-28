package com.spring.rest.service.roles;

import com.spring.rest.dao.roles.RoleDao;
import com.spring.rest.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Override
    public void updateRole(Role role) {
        roleDao.updateRole(role);
    }

    @Override
    public Role getRoleById(long id) {
        return roleDao.getRoleById(id);
    }

    @Override
    public void deleteRole(Role role) {
        roleDao.deleteRole(role);
    }

    @Override
    public void deleteRoleById(long id) {
        roleDao.deleteRoleById(id);
    }

    public Set<Role> setRoleByName(String name, String[] rolesName) {
        Set<Role> roleSet = new HashSet<>();
        if (rolesName != null) {
            for (String roleName : rolesName) {
                roleSet.add(roleDao.getRoleByName(roleName));
            }
        }
        return roleSet;
    }
}
