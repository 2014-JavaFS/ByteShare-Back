package com.revature.byteshare.Vote;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VoteServiceTest {

    @Mock            // Indicates the object needing to be mocked
    private VoteRepository voteRepository;

    @InjectMocks    // Inject repository into service as a mocked object
    private VoteService voteService;


    @Test
    void findAll_ShouldReturnListOfVotes_WhenVotesExist() {
        // AAA
        // Arrange
        Vote vote1 = new Vote();
        Vote vote2 = new Vote();
        when(voteRepository.findAll()).thenReturn(Arrays.asList(vote1, vote2));

        // Act
        List<Vote> votes = voteService.findAll();

        // Assert
        assertNotNull(votes);
        assertEquals(2, votes.size());
        verify(voteRepository, times(1)).findAll();

    }
}
