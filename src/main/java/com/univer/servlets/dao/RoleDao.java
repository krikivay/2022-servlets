package com.univer.servlets.dao;

import com.univer.servlets.entity.Role;

import java.util.List;

public interface RoleDao {
    void create(Role role);
    void update(Role role);
    void remove(Role role);
    List<Role> findAll();
    Role findByName(String name);
    Role findById(Long id);
}
