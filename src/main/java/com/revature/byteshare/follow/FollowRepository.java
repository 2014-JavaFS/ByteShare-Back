package com.revature.byteshare.follow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {
    List<Follow> findAllByFollowerUserId(int userId);

    List<Follow> findAllByFollowingUserId(int userId);

    /**
     * Name of class attribute followed by UserId from the User object
     * @param follower
     * @param following
     * @return
     */
    Optional<Follow> findByFollowerUserIdAndFollowingUserId(int follower, int following);
}
