package com.revature.byteshare.follow;

import com.revature.byteshare.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/follow")
@CrossOrigin
public class FollowController {
    private final FollowService followService;
    private final UserService userService;

    @Autowired
    public FollowController(FollowService followService, UserService userService) {
        this.followService = followService;
        this.userService = userService;
    }

    @GetMapping("/following")
    public ResponseEntity<List<Follow>> getFollowing(@RequestHeader int currentUserId) {
        return ResponseEntity.ok(followService.findAllFollowing(currentUserId));
    }

    @GetMapping("/followers")
    public ResponseEntity<List<Follow>> getFollowers(@RequestHeader int currentUserId) {
        return ResponseEntity.ok().body(followService.findAllFollowers(currentUserId));
    }

    @GetMapping("/following/user")
    public ResponseEntity<List<Follow>> getFollowingOfUser(@RequestParam int userId) {
        return ResponseEntity.ok(followService.findAllFollowing(userId));
    }

    @GetMapping("/followers/user")
    public ResponseEntity<List<Follow>> getFollowersOfUser(@RequestParam int userId) {
        return ResponseEntity.ok(followService.findAllFollowers(userId));
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
        Follow toUnfollow = followService.findByFollowerAndFollowing(currentUserId, followingId);
        followService.deleteFollow(toUnfollow);
        return ResponseEntity.noContent().build();
    }
}
