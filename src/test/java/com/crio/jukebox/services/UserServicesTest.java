package com.crio.jukebox.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("UserServicesTest")
@ExtendWith(MockitoExtension.class)
public class UserServicesTest {

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private UserServices userServices;

    @DisplayName("Must Create A User")
    @Test
    public void addUserTest(){
        User expectedUser = new User(1l, "name1");
        when(userRepositoryMock.createOrUpdateUser(any(User.class))).thenReturn(expectedUser);
        User actualUser = userServices.createUser("name1");
        Assertions.assertEquals(expectedUser, actualUser);
    }
}
