package org.microservice1.authentication_ar.config;

import lombok.RequiredArgsConstructor;
import org.microservice1.authentication_ar.entity.AppUser;
import org.microservice1.authentication_ar.entity.Role;
import org.microservice1.authentication_ar.service.RoleService;
import org.microservice1.authentication_ar.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
@RequiredArgsConstructor
public class StartUpApp implements CommandLineRunner {
    private final UserService userService;

    private final RoleService roleService;
    @Override
    public void run(String... args) throws Exception {

        if (roleService.findAll().isEmpty()) {
            roleService.save(new Role(null, "admin"));
            roleService.save(new Role(null, "user"));
        }


        if (userService.findAll().isEmpty()) {

            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(roleService.findByName("admin"));

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(roleService.findByName("user"));



            userService.save(new AppUser(null, "Hazem Elseddik", "hazem@gmail.com", "123", adminRoles));

            userService.save(new AppUser(null, "Shrief Ali", "shrief@gmail.com", "123", userRoles));

            userService.save(new AppUser(null, "Ahmed Amer", "ahmed@gmail.com", "123", userRoles));
        }

    }
}
