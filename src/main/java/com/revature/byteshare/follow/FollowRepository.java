package com.revature.byteshare.follow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {
    Optional<List<Follow>> findAllByFollower(int userId);

    Optional<List<Follow>> findAllByFollowing(int userId);

    Optional<Follow> findByFollowerAndFollowing(int follower, int following);
}
