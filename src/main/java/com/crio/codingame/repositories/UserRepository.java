package com.crio.codingame.repositories;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.crio.codingame.entities.User;

public class UserRepository implements IUserRepository{

    private final Map<String,User> userMap;
    private Integer autoIncrement = 0;

    public UserRepository(){
        userMap = new HashMap<String,User>();
    }

    public UserRepository(Map<String, User> userMap) {
        this.userMap = userMap;
        this.autoIncrement = userMap.size();
    }

    @Override
    public User save(User entity) {
        if( entity.getId() == null ){
            autoIncrement++;
            User u = new User(Integer.toString(autoIncrement),entity.getName(),entity.getScore());
            userMap.put(u.getId(),u);
            return u;
        }
        userMap.put(entity.getId(),entity);
        return entity;
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Find all the list of User Present in the Repository
    // Tip:- Use Java Streams

    @Override
    public List<User> findAll() {
        return this.userMap.entrySet().stream().map(entry->entry.getValue()).collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        return this.userMap.containsKey(id);
    }

    @Override
    public void delete(User entity) {
        this.userMap.remove(entity.getId());
    }

    @Override
    public void deleteById(String id) {
        this.userMap.remove(id);
    }

    @Override
    public long count() {
        return this.userMap.size();
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Find the User Present in the Repository provided name
    // Tip:- Use Java Streams

    @Override
    public Optional<User> findByName(String name) {
    Entry<String, User> userEntry = this.userMap.entrySet().stream().filter(entry->entry.getValue().getName().equals(name)).findFirst().orElse(null);
    return userEntry==null ? Optional.empty() : Optional.ofNullable(userEntry==null ? null : userEntry.getValue());
    }
    
}
