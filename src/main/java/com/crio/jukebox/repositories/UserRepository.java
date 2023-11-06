package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.Map;
import com.crio.jukebox.entities.User;

public class UserRepository {
    private Map<Long, User> users;

    public UserRepository() {
        users = new HashMap<>();
    }

    public UserRepository(Map<Long, User> users) {
        this.users = users;
    }

    private Long generateUserId() {
        return users.size() + 1l;
    }

    public User createOrUpdateUser(User user) {
        if (user.getId() == null) {
            user.setId(generateUserId());
        }
        users.put(user.getId(), user);
        return user;
    }

    public User getUser(Long userId) {
        return users.getOrDefault(userId, null);
    }

    public void deleteUser(Long userId) {
        users.remove(userId);
    }
}
