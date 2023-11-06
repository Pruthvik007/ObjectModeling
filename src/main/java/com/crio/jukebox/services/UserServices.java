package com.crio.jukebox.services;

import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.UserRepository;

public class UserServices implements IUserServices {

    private UserRepository userRepository;

    public UserServices(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(String userName) {
        return userRepository.createOrUpdateUser(new User(userName));
    }

}
