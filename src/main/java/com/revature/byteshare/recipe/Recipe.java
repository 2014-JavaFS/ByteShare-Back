package com.revature.byteshare.recipe;

import com.revature.byteshare.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipeId;

    @ManyToOne
    @JoinColumn(name = "author")
    //TODO: Get User implementation once it is available, empty file currently
    private User user;

    @Column(name = "date_posted", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp datePosted;

    //TODO: Spring annotations for content in Recipe.java; double check this later
    @Column(name = "content")
    private String content;

    //TODO: double check these annotations
    @Column(name = "prep_time")
    private int prepTime;

    //TODO: double check these annotations
    @Column(name = "cook_time")
    private int cookTime;
}