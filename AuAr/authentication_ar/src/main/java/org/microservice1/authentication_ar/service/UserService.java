package org.microservice1.authentication_ar.service;

import lombok.RequiredArgsConstructor;
import org.microservice1.authentication_ar.entity.AppUser;
import org.microservice1.authentication_ar.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService  {

    private final UserRepo userRepo;



    public List<AppUser> findAll() {

        return userRepo.findAll();
    }

    public AppUser findById(Long id) {

        return userRepo.findById(id).orElse(null);
    }

    public AppUser save(AppUser entity) {
        return userRepo.save(entity);
    }

    public void delete(AppUser entity) {
        userRepo.delete(entity);
    }
}