package com.spring.rest.dao.roles;


import com.spring.rest.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select r from Role r").getResultList();
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void updateRole(Role role) {
        entityManager.merge(role);
    }

    @Override
    public Role getRoleById(long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public void deleteRole(Role role) {
        entityManager.remove(role);
    }

    @Override
    public void deleteRoleById(long id) {
        entityManager.remove(entityManager.find(Role.class, id));
    }

    @Override
    public Role getRoleByName(String name) {
        return entityManager.createQuery("From Role u where u.name =: name", Role.class)
                .setParameter("name", name)
                .getSingleResult();

    }
}
