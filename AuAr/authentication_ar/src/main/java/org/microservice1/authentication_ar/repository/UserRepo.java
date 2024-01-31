package org.microservice1.authentication_ar.repository;

import org.microservice1.authentication_ar.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository <AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
}
