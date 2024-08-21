package com.revature.byteshare.follow;


import com.revature.byteshare.user.User;
import com.revature.byteshare.util.exceptions.DataNotFoundException;
import com.revature.byteshare.util.exceptions.InvalidInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FollowServiceUnitTestSuite {

    @Mock
    private FollowRepository mockFollowRepository;

    @InjectMocks
    private FollowService followService;

    private static User defaultFollower = new User(1, "scooby@mystery.com", "sc00byd00by", "ScoobertDoobert", "Scooby", "Doo", "auth", User.userType.USER);
    private static User defaultFollowing = new User(2, "shaggy@mystery.com", "z01nk5!", "Chef_Rogers", "Norville", "Rogers", "auth", User.userType.USER);
    private static Follow validFollow = new Follow(1, defaultFollower, defaultFollowing);

    @Test
    public void testSuccessFindAllFollowing() {
        //Arrange
        List<Follow> follows = new ArrayList<>(Arrays.asList(validFollow));
        when(mockFollowRepository.findAllByFollower(1))
                .thenReturn(follows);

        //Act
        List<Follow> result = followService.findAllFollowing(1);

        //Assert
        assertEquals(1, result.size());
        assertEquals(validFollow, result.get(0));
    }

    @Test
    public void testFailedFindAllFollowing() {
        when(mockFollowRepository.findAllByFollower(anyInt()))
                .thenReturn(List.of());

        assertThrows(DataNotFoundException.class,
                () -> followService.findAllFollowing(1));
        verify(mockFollowRepository, times(1)).findAllByFollower(1);
    }

    @Test
    public void testSuccessFindAllFollowers() {
        //Arrange
        List<Follow> follows = new ArrayList<>(Arrays.asList(validFollow));
        when(mockFollowRepository.findAllByFollowing(2))
                .thenReturn(follows);

        //Act
        List<Follow> result = followService.findAllFollowers(2);

        //Assert
        assertEquals(1, result.size());
        assertEquals(validFollow, result.get(0));
    }

    @Test
    public void testFailedFindAllFollowers() {
        when(mockFollowRepository.findAllByFollowing(anyInt()))
                .thenReturn(List.of());

        assertThrows(DataNotFoundException.class,
                () -> followService.findAllFollowers(1));
        verify(mockFollowRepository, times(1)).findAllByFollowing(1);
    }

    @Test
    public void testExistentFindByFollowerAndFollowing() {
        //Arrange
        when(mockFollowRepository.findByFollowerAndFollowing(1, 2))
                .thenReturn(Optional.of(validFollow));

        //Act
        Follow result = followService.findByFollowerAndFollowing(1, 2);

        //Assert
        assertNotNull(result);
        verify(mockFollowRepository, times(1)).findByFollowerAndFollowing(1, 2);
    }

    @Test
    public void testNonexistentFindByFollowerAndFollowing() {
        when(mockFollowRepository.findByFollowerAndFollowing(anyInt(), anyInt()))
                .thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class,
                () -> followService.findByFollowerAndFollowing(2, 1));
        verify(mockFollowRepository, times(1)).findByFollowerAndFollowing(2, 1);
    }

    @Test
    public void testValidCreateFollow() {
        //Arrange
        when(mockFollowRepository.save(validFollow))
                .thenReturn(validFollow);

        //Act
        Follow returnedFollow = followService.createFollow(validFollow);

        //Assert
        assertEquals(validFollow, returnedFollow);
        verify(mockFollowRepository, times(1)).save(validFollow);
    }

    @Test
    public void testInvalidCreateFollow() {
        Follow invalidFollow = new Follow(2, defaultFollower, defaultFollower);

        Assertions.assertThrows(InvalidInputException.class,
                () -> followService.createFollow(invalidFollow));
    }

    @Test
    public void testDeleteFollow() {
        boolean actual = followService.deleteFollow(validFollow);
        assertTrue(actual);
        verify(mockFollowRepository, times(1)).delete(validFollow);
    }

}
