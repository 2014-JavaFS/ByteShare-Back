package com.revature.byteshare.Vote;

import com.revature.byteshare.userfeedback.UserFeedback;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.revature.byteshare.user.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vote_id;

    @ManyToOne
    private User voter;

    @ManyToOne
    private UserFeedback userFeedback;

    private boolean isUpvote;
}


