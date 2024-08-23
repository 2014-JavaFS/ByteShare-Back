package com.revature.byteshare.usertests;

import com.revature.byteshare.user.User;
import com.revature.byteshare.user.UserRepository;
import com.revature.byteshare.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTestSuite {
    @Mock
    private UserRepository mockRepo;

    @InjectMocks
    private UserService sut;

    private static User defaultUser = new User(1, "test@mail.com", "IaMaK1NgThis@Passw0rd", "testuser028", "Arjun", "Ramsinghani", User.UserType.ADMIN);

    @Test
    public void testGetAllUsers() {
        List<User> users = new ArrayList<>(Arrays.asList(defaultUser));
        when(mockRepo.findAll()).thenReturn(users);

        List<User> result = sut.findAll();
        assertEquals(1, result.size());
        assertEquals(defaultUser, result.get(0));
    }

    @Test
    public void testGetUserById() {
        when(mockRepo.findById(defaultUser.getUserId())).thenReturn(Optional.ofNullable(defaultUser));

        User result = sut.findById(defaultUser.getUserId());
        assertEquals(defaultUser, result);
    }

    @Test
    public void testLookUpUserIdByEmail() {
        when(mockRepo.findByEmail(defaultUser.getEmail())).thenReturn(Optional.ofNullable(defaultUser));

        int id = sut.lookupUserIdByEmail(defaultUser.getEmail());
        assertEquals(defaultUser.getUserId(), id);
    }

    @Test
    public void testFindUserByIdNumber() {
        when(mockRepo.findById(defaultUser.getUserId())).thenReturn(Optional.ofNullable(defaultUser));

        User result = sut.findByUserIdNumber(defaultUser.getUserId());
        assertEquals(defaultUser, result);
    }

    @Test
    public void testCreateUser() {
        when(mockRepo.save(defaultUser)).thenReturn(defaultUser);

        User result = sut.createUser(defaultUser);
        assertEquals(defaultUser, result);
    }

    @Test
    public void testUpdateUser() {
        when(mockRepo.save(defaultUser)).thenReturn(defaultUser);

        assertTrue(sut.updateUser(defaultUser));
        verify(mockRepo, times(1)).save(defaultUser);
    }

    @Test
    public void testUpdateUserAccessLevel() {
        when(mockRepo.save(defaultUser)).thenReturn(defaultUser);

        assertTrue(sut.updateUserAccessLevel(defaultUser, User.UserType.AUTHOR));
        verify(mockRepo, times(1)).save(defaultUser);
    }

    @Test
    public void testDeleteUser() {
        assertTrue(sut.deleteUser(defaultUser.getUserId()));
        verify(mockRepo, times(1)).deleteById(defaultUser.getUserId());
    }
}
