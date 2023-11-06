package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.Map;
import com.crio.jukebox.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserRepositoryTest {
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        Map<Long, User> users = new HashMap<>();
        users.put(1l, new User(1l, "user1"));
        users.put(2l, new User(2l, "user2"));
        users.put(3l, new User(3l, "user3"));
        users.put(4l, new User(4l, "user4"));
        users.put(5l, new User(5l, "user5"));
        userRepository = new UserRepository(users);
    }

    @Test
    @DisplayName("Adding User Test")
    public void addUser(){
        User expectedUser = new User(6l, "user6");
        User actualUser = userRepository.createOrUpdateUser(new User("user6"));
        Assertions.assertEquals(expectedUser, actualUser);
    }
}
