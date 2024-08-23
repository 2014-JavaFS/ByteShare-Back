package com.revature.byteshare.Vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/votes")
public class VoteController {

    private final VoteService voteService;

    @Autowired
    public VoteController (VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping
    public @ResponseBody List<Vote> getAllVotes() {
        return voteService.findAll();
    }

    @PostMapping
    public ResponseEntity<Vote> postNewVote(@RequestBody Vote vote) {
        return ResponseEntity.status(HttpStatus.CREATED).body(voteService.create(vote));
    }

    @GetMapping("/{id}")
    private ResponseEntity<Vote> getVoteById(@PathVariable int id) {
        return ResponseEntity.ok(voteService.findById(id));
    }

    @PutMapping
    private ResponseEntity<Boolean> putUpdateVote(@RequestBody Vote updatedVote) {
        return ResponseEntity.ok(voteService.update(updatedVote));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Boolean> deleteVote(@PathVariable int id) {
        return ResponseEntity.ok(voteService.delete(id));
    }

}
