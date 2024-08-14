package com.revature.byteshare.Vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


}
