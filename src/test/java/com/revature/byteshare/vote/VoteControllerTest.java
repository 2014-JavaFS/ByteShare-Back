package com.revature.byteshare.vote;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VoteService voteService;

    @Autowired
    private ObjectMapper objectMapper;

    private Vote vote1;
    private Vote vote2;

    @BeforeEach
    void setUp() {
        vote1 = new Vote();
        vote1.setVote_id(1);
        vote1.setUpvote(true);

        vote2 = new Vote();
        vote2.setVote_id(2);
        vote2.setUpvote(false);
    }

    @Test
    void getAllVotes_ShouldReturnListOfVotes() throws Exception {
        // Arrange
        List<Vote> votes = Arrays.asList(vote1, vote2);
        when(voteService.findAll()).thenReturn(votes);

        // Act & Assert
        mockMvc.perform(get("/votes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].vote_id").value(vote1.getVote_id()))
                .andExpect(jsonPath("$[1].vote_id").value(vote2.getVote_id()));
    }

    @Test
    void postNewVote_ShouldCreateAndReturnVote() throws Exception {

        when(voteService.create(any(Vote.class))).thenReturn(vote1);

        mockMvc.perform(post("/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vote1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.vote_id").value(vote1.getVote_id()));
    }
}
