package com.piritter.api.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.piritter.api.model.ERole;
import com.piritter.api.model.Role;

@DataJpaTest
public class RoleRepositoryTests {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void testFindByName() {
        Role foundRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                       .orElse(null);
        assertThat(foundRole).isNotNull();
        assertEquals(ERole.ROLE_ADMIN, foundRole.getName());
    }
}
