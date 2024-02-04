package org.microservice1.authentication.user;
import org.junit.jupiter.api.Test;
import org.microservice1.authentication.user.entity.Roles;
import org.microservice1.authentication.user.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserEntityMethods() {
        User user = User.builder()
                .id(1)
                .name("TestUser")
                .email("test@example.com")
                .password("testPassword")
                .roles(Roles.USER)
                .build();

        // Test getters
        assertEquals(1, user.getId());
        assertEquals("TestUser", user.getName());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("testPassword", user.getPassword());
        assertEquals(Roles.USER, user.getRoles());

        // Test setters
        user.setId(2);
        user.setName("UpdatedUser");
        user.setEmail("updated@example.com");
        user.setPassword("updatedPassword");
        user.setRoles(Roles.ADMIN);

        assertEquals(2, user.getId());
        assertEquals("UpdatedUser", user.getName());
        assertEquals("updated@example.com", user.getEmail());
        assertEquals("updatedPassword", user.getPassword());
        assertEquals(Roles.ADMIN, user.getRoles());

        // Test toString
        assertNotNull(user.toString());

        // Test equals and hashCode
        User sameUser = User.builder()
                .id(2)
                .name("UpdatedUser")
                .email("updated@example.com")
                .password("updatedPassword")
                .roles(Roles.ADMIN)
                .build();

        assertEquals(user, sameUser);
        assertEquals(user.hashCode(), sameUser.hashCode());

        // Test UserDetails methods
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority(Roles.ADMIN.name())));

        assertEquals("updatedPassword", user.getPassword());
        assertEquals("updated@example.com", user.getUsername());
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isCredentialsNonExpired());
        assertTrue(user.isEnabled());
    }
}
