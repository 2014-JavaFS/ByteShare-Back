package com.revature.byteshare.Vote;

import com.revature.byteshare.util.exceptions.DataNotFoundException;
import com.revature.byteshare.util.exceptions.InvalidInputException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public List<Vote> findAll() {
        List<Vote> votes = voteRepository.findAll();

        if(votes.isEmpty()) {
            throw new DataNotFoundException("No vote information found.");
        }
        return votes;
    }

    public Vote findById(int id) throws DataNotFoundException {
        Vote vote = voteRepository.findById(id).orElseThrow(() -> new DataNotFoundException("No votes found for ID: " + id));
        return vote;
    }

    public Vote create(Vote vote) {
        return voteRepository.save(vote);
    }

    @Transactional
    public boolean update(Vote vote) throws InvalidInputException {
        if (findById(vote.getVote_id()) == null) {
            throw new DataNotFoundException("Vote with ID not found, please check again.");
        }
        voteRepository.save(vote);
        return true;
    }

    @Transactional
    public boolean delete(int voteId) throws InvalidInputException {
        if (findById(voteId) == null) {
            throw new DataNotFoundException("Vote with ID not found, please check again.");
        }
        voteRepository.deleteById(voteId);
        return true;
    }

}
