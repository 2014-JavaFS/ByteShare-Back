package com.revature.byteshare.follow;

import com.revature.byteshare.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follow")
public class FollowController {
    private final FollowService followService;
    private final UserService userService;

    @Autowired
    public FollowController(FollowService followService, UserService userService) {
        this.followService = followService;
        this.userService = userService;
    }

    @GetMapping("/following")//might change args to allow seeing someone else's
    public ResponseEntity<List<Follow>> getFollowing(@RequestHeader int currentUserId) {
        return ResponseEntity.ok(followService.findAllFollowing(currentUserId));
    }

    @GetMapping("/followers")//might change args to allow seeing someone else's
    public ResponseEntity<List<Follow>> getFollowers(@RequestHeader int currentUserId) {
        return ResponseEntity.ok().body(followService.findAllFollowers(currentUserId));
    }

    @PostMapping
    public ResponseEntity<Follow> postFollow(@RequestHeader int currentUserId, @RequestParam int followingId) {
        Follow newFollow = new Follow();
        newFollow.setFollower(userService.findByUserIdNumber(currentUserId));
        newFollow.setFollowing(userService.findByUserIdNumber(followingId));

        return ResponseEntity.status(HttpStatus.CREATED).body(followService.createFollow(newFollow));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteFollow(@RequestHeader int currentUserId, @RequestParam int followingId) {
        followService.deleteFollow(currentUserId, followingId);
        return ResponseEntity.noContent().build();
    }
}
