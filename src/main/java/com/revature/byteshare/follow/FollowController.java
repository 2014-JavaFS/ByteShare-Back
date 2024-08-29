package com.revature.byteshare.follow;

import com.revature.byteshare.user.UserService;
import com.revature.byteshare.util.exceptions.DataNotFoundException;
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
        try {
            //Looks up the Users Follower List, If he Has Followed The Current User Return 406
            //If The User Hasn't Followed Anyone Yet Will Throw DataNotFoundException
            //Otherwise Should Return 201
            List<Follow> checking = followService.findAllFollowers(currentUserId);
            for(int i=0;i<checking.size();i++) {
                if (checking.get(i).getFollowing().getUserId() == followingId)
                    return ResponseEntity.status(406).body(null);
            }

                newFollow.setFollower(userService.findByUserIdNumber(currentUserId));
                newFollow.setFollowing(userService.findByUserIdNumber(followingId));

                return ResponseEntity.status(HttpStatus.CREATED).body(followService.createFollow(newFollow));

            }catch(DataNotFoundException e){
            //Occurs On User Making A Fist Follow
            newFollow.setFollower(userService.findByUserIdNumber(currentUserId));
            newFollow.setFollowing(userService.findByUserIdNumber(followingId));
            return ResponseEntity.status(HttpStatus.CREATED).body(followService.createFollow(newFollow));

        }
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteFollow(@RequestHeader int currentUserId, @RequestParam int followingId) {
        Follow toUnfollow = followService.findByFollowerAndFollowing(currentUserId, followingId);
        followService.deleteFollow(toUnfollow);
        return ResponseEntity.noContent().build();
    }
}
