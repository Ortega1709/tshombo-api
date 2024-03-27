package com.ortega.tshomboapi.service;

import com.ortega.tshomboapi.model.Role;

import java.util.List;
import java.util.UUID;

public interface IRoleService {

    public List<Role> getAllRoles();
    public Role saveRole(Role role);
    public void deleteRoleById(UUID id);
    public Role updateRole(Role role);
}
