package com.revature.byteshare.follow;

import com.revature.byteshare.util.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowService {
    private final FollowRepository followRepository;

    @Autowired
    public FollowService(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    public List<Follow> findAllFollowing(int userId) {
        return followRepository.findAllByFollower(userId)
                .orElseThrow(() -> new DataNotFoundException("User not following any users"));
    }

    public List<Follow> findAllFollowers(int userId) {
        return followRepository.findAllByFollowing(userId)
                .orElseThrow(() -> new DataNotFoundException("User has no followers"));
    }

    public Follow createFollow(Follow follow) {
        return followRepository.save(follow);
    }

    public boolean deleteFollow(int followerId, int followingId) {
        Follow unfollow = followRepository.findByFollowerAndFollowing(followerId, followingId)
                .orElseThrow(() -> new DataNotFoundException("Follow record not found."));
        followRepository.delete(unfollow);
        return true;
    }
}
