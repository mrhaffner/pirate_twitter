package com.piritter.api.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.piritter.api.model.User;

@DataJpaTest
public class UserRepositoryTests {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
        User user = new User("stevesie", "123456");
        entityManager.persistAndFlush(user);
        assertThat(user.getId()).isNotNull();
    }

    @Test
    public void testCreateUserWithWrongUsernamePattern() {
        User user = new User("stevesie!", "123456");
        boolean errorCaught = false;
        try {
            entityManager.persistAndFlush(user);
        } catch (ConstraintViolationException e) {
            errorCaught = true;
        }
        assertTrue(errorCaught);
    }

    @Test
    public void testCreateUserWithLongUsername() {
        User user = new User("stevesieadfasdfasdfasdfadsfasdfasdfasdfasdfasdfasdf",
                                 "123456");
        boolean errorCaught = false;
        try {
            entityManager.persistAndFlush(user);
        } catch (ConstraintViolationException e) {
            errorCaught = true;
        }
        assertTrue(errorCaught);
    }

    @Test
    public void testCreateUserWithShortUsername() {
        User user = new User("s",
                                 "123456");
        boolean errorCaught = false;
        try {
            entityManager.persistAndFlush(user);
        } catch (ConstraintViolationException e) {
            errorCaught = true;
        }
        assertTrue(errorCaught);
    }

    @Test 
    public void testFindByUsername() {
        User user = new User("stevesie", "123456");
        entityManager.persistAndFlush(user);
        User foundUser = userRepository.findByUsername("stevesie")
                                       .orElse(null);
        assertThat(foundUser).isNotNull();
        assert(foundUser.getUsername().equals("stevesie"));
    }

    @Test
    public void testExistsByUsername() {
        User user = new User("stevesie", "123456");
        entityManager.persistAndFlush(user);
        boolean foundUser = userRepository.existsByUsername("stevesie");
        assertTrue(foundUser);
        foundUser = userRepository.existsByUsername("bob");
        assertFalse(foundUser);
    }

    @Test void testFindAllUsernamesLike() {
        List<User> users = userRepository.findAllUsernamesLike("steve");
        assertEquals(users.size(), 0);
        User steve = new User("steve", "123456");
        User stevesie = new User("stevesie", "123456");
        User bobart = new User("bobart", "123456");
        entityManager.persistAndFlush(steve);
        entityManager.persistAndFlush(stevesie);
        entityManager.persistAndFlush(bobart);
        users = userRepository.findAllUsernamesLike("steve");
        assertEquals(users.size(), 2);
        assertThat(bobart).isNotIn(users);
    }
}
