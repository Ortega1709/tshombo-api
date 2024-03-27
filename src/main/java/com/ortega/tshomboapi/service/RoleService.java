package com.ortega.tshomboapi.service;

import com.ortega.tshomboapi.model.Role;
import com.ortega.tshomboapi.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRoleById(UUID id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }
}
