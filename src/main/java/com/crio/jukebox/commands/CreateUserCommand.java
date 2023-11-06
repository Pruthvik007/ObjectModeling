package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.services.UserServices;

public class CreateUserCommand implements ICommand {

    private final UserServices userServices;

    public CreateUserCommand(UserServices userServices) {
        this.userServices = userServices;
    }

    @Override
    public void execute(List<String> tokens) {
        String userName = tokens.get(1);
        User createdUser = userServices.createUser(userName);
        System.out.println(createdUser.getId() + " " + createdUser.getName());
    }
}
