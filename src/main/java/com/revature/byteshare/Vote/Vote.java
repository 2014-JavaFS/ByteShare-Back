package com.revature.byteshare.Vote;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.revature.byteshare.recipe.Recipe;
import com.revature.byteshare.user.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue
    private int vote_id;
    @ManyToOne
    private User voter;
    @ManyToOne
    private Recipe recipe;      // Post or Recipe?
    private boolean isUpvote;
}


