package com.revature.byteshare.vote;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private User voter;
    private Recipe recipe;      // Post or Recipe?
    private boolean isUpvote;
}


