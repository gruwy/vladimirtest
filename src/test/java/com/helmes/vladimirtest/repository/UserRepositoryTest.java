package com.helmes.vladimirtest.repository;

import com.helmes.vladimirtest.entity.UserEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private UserEntity user;

    @BeforeEach
    public void setUp() {
        user = UserEntity.builder()
                .userName("TestUser")
                .agreedToTerms(true)
                .build();
        userRepository.save(user);
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void findNewUserByUserName_findsUserByUserName() {
        assertNotNull(userRepository.findByUserName("TestUser"));
    }

    @Test
    public void findNewUserByUserName_doesntFindUserByUserName() {
        assertNull(userRepository.findByUserName("Test_User"));
    }
}
