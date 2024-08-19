package com.revature.byteshare.Vote;


import com.revature.byteshare.util.exceptions.DataNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @Test
    void findAll_ShouldThrowDataNotFoundException_WhenNoVotesExist() {

        // Arrange
        when(voteRepository.findAll()).thenReturn(List.of());

        // Act
        DataNotFoundException exception = assertThrows(DataNotFoundException.class, () -> voteService.findAll());

        // Assert
        assertEquals("No vote information found.", exception.getMessage());
        verify(voteRepository, times(1)).findAll();

    }

    @Test
    void findById_ShouldReturnVote_WhenVoteExists() {

        // Arrange
        Vote vote = new Vote();
        when(voteRepository.findById(anyInt())).thenReturn(Optional.of(vote));

        // Act
        Vote result = voteService.findById(1);

        // Assert
        assertNotNull(result);
        verify(voteRepository, times(1)).findById(1);
    }

    @Test
    void findById_ShouldThrowDataNotFoundException_WhenVoteDoesNotExist() {

        // Arrange
        when(voteRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act
        DataNotFoundException exception = assertThrows(DataNotFoundException.class, () -> voteService.findById(1));

        // Assert
        assertEquals("No votes found for ID: 1", exception.getMessage());
        verify(voteRepository, times(1)).findById(1);
    }

    @Test
    void create_ShouldSaveAndReturnVote() {

        // Arrange
        Vote vote = new Vote();
        when(voteRepository.save(any(Vote.class))).thenReturn(vote);

        // Act
        Vote result = voteService.create(vote);

        // Assert
        assertNotNull(result);
        verify(voteRepository, times(1)).save(vote);
    }

    @Test
    void update_ShouldUpdateAndReturnTrue_WhenVoteExists() {

        // Arrange
        Vote vote = new Vote();
        vote.setVote_id(1);
        when(voteRepository.findById(anyInt())).thenReturn(Optional.of(vote));
        when(voteRepository.save(any(Vote.class))).thenReturn(vote);

        // Act
        boolean result = voteService.update(vote);

        // Assert
        assertTrue(result);
        verify(voteRepository, times(1)).findById(1);
        verify(voteRepository, times(1)).save(vote);
    }

    @Test
    void delete_ShouldDeleteVoteAndReturnTrue() {

        // Arrange
        int voteId = 1;
        Vote vote = new Vote();
        vote.setVote_id(voteId);
        when(voteRepository.findById(voteId)).thenReturn(Optional.of(vote));

        // Act
        boolean result = voteService.delete(voteId);

        // Assert
        assertTrue(result);
        verify(voteRepository, times(1)).findById(voteId);
        verify(voteRepository, times(1)).deleteById(voteId);
    }

}
