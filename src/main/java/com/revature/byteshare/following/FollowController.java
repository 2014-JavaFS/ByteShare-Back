package com.revature.byteshare.following;

import com.revature.byteshare.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:5173"})
@RestController
@RequestMapping("/follow")//trying something
public class FollowController {
    private final FollowService followService;

    @Autowired
    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @GetMapping("ing")//trying something
    public ResponseEntity<List<Follow>> getFollowing(@RequestHeader User currentUser) {
        return ResponseEntity.ok(followService.findAllFollowing(currentUser.getUser_id()));
    }

    @GetMapping("ers")//trying something
    public ResponseEntity<List<Follow>> getFollowers(@RequestHeader User currentUser) {
        return ResponseEntity.ok(followService.findAllFollowers(currentUser.getUser_id()));
    }

    @PostMapping
    public ResponseEntity<Follow> postNewFollow(@RequestBody Follow follow) {
        return ResponseEntity.status(HttpStatus.CREATED).body(followService.createFollow(follow));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteFollow(@RequestHeader User currentUser, @RequestParam int followingId){
        followService.deleteFollow(currentUser.getUser_id(), followingId);
        return ResponseEntity.noContent().build();
    }
}
