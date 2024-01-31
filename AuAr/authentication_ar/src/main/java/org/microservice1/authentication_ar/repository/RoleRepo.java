package org.microservice1.authentication_ar.repository;

import org.microservice1.authentication_ar.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {

    Role findByName(String name);
}
