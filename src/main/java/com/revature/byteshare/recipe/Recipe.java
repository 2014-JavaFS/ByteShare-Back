package com.revature.byteshare.recipe;

import com.revature.byteshare.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipeId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @Column(name = "timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp date;

    private String title;

    // TODO: Spring annotations for these fields
    private String content; //either blob or text in SQL
    private int prepTime; // in minutes
    private int cookTime; // in minutes

    public Recipe(int id, User defaultUser, Time now, String thisIsARecipe, int prepTime, int cookTime) {
    }
}

