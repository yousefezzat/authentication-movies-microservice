package org.microservice1.authentication_ar.service;

import lombok.RequiredArgsConstructor;
import org.microservice1.authentication_ar.entity.AppUser;
import org.microservice1.authentication_ar.entity.Role;
import org.microservice1.authentication_ar.repository.RoleRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepo roleRepo;



    public List<Role> findAll() {

        return roleRepo.findAll();
    }

    public Role findById(Long id) {

        return roleRepo.findById(id).orElse(null);
    }
    public Role findByName(String name) {

        return roleRepo.findByName(name);
    }

    public Role save(Role role) {
        return roleRepo.save(role);
    }

    public void delete(Role role) {
        roleRepo.delete(role);
    }

}
