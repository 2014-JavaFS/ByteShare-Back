package com.revature.byteshare.Vote;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VoteRepositoryTest {

    @Autowired
    private VoteRepository voteRepository;

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

        voteRepository.save(vote1);
        voteRepository.save(vote2);
    }

    @Test
    void findAll_ShouldReturnAllVotes() {
        List<Vote> votes = voteRepository.findAll();
        assertThat(votes).hasSize(2);
        assertThat(votes).contains(vote1, vote2);
    }

    @Test
    void findById_ShouldReturnVote() {
        Optional<Vote> foundVote = voteRepository.findById(vote1.getVote_id());
        assertThat(foundVote).isPresent();
        assertThat(foundVote.get()).isEqualTo(vote1);
    }

    @Test
    void save_ShouldCreateNewVote() {
        Vote vote3 = new Vote();
        vote3.setVote_id(3);
        vote3.setUpvote(true);

        Vote savedVote = voteRepository.save(vote3);
        assertThat(savedVote).isNotNull();
        assertThat(savedVote.getVote_id()).isEqualTo(3);
    }

    @Test
    void deleteById_ShouldDeleteVote() {
        voteRepository.deleteById(vote1.getVote_id());
        Optional<Vote> deletedVote = voteRepository.findById(vote1.getVote_id());
        assertThat(deletedVote).isNotPresent();
    }


}
