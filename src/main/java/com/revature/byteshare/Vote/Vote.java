package com.revature.byteshare.Vote;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue
    private int vote_id;
    private User voter;
    private Post post;      // Post or Recipe?
    private boolean isUpvote;
}


