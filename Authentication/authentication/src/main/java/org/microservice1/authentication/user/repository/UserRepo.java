package org.microservice1.authentication.user.repository;

import org.microservice1.authentication.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

   Optional <User> findByEmail(String email);
   Boolean existsByEmail(String email);

}