package com.revature.byteshare.follow;

import com.revature.byteshare.util.exceptions.DataNotFoundException;
import com.revature.byteshare.util.exceptions.InvalidInputException;
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
        List<Follow> follows = followRepository.findAllByFollowerUserId(userId);
        if (follows.isEmpty())
            throw new DataNotFoundException("User not following any users");
        else
            return follows;
    }

    public List<Follow> findAllFollowers(int userId) {
        List<Follow> follows = followRepository.findAllByFollowingUserId(userId);
        if (follows.isEmpty())
            throw new DataNotFoundException("User has no followers");
        else
            return follows;
    }

    public Follow findByFollowerAndFollowing(int followerId, int followingId) {
        return followRepository.findByFollowerUserIdAndFollowingUserId(followerId, followingId)
                .orElseThrow(() -> new DataNotFoundException("Current user is not following that user"));
    }

    public Follow createFollow(Follow follow) {
        if (follow.getFollower().getUserId() == follow.getFollowing().getUserId())
            throw new InvalidInputException("User cannot follow themself");
        else
            return followRepository.save(follow);
    }

    public boolean deleteFollow(Follow unfollow) {
        followRepository.delete(unfollow);
        return true;
    }
}
