package com.revature.byteshare.recipe;

import com.revature.byteshare.ingredient.models.Ingredient;
import com.revature.byteshare.user.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipeId;

    @Column(name = "name", columnDefinition = "VARCHAR(100)")
    private String name;

    @OneToMany(mappedBy = "recipe")
    private List<Ingredient> ingredients;

    @Column(name = "instructions", columnDefinition = "VARCHAR(5000)")
    private String instructions;

    @ManyToOne
    @JoinColumn(name = "author")
    //TODO: Get User implementation once it is available, empty file currently
    private User author;

    @Column(name = "date_posted", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp datePosted;

    //TODO: Spring annotations for content in Recipe.java; double check this later
    @Column(name = "content", columnDefinition = "BLOB")
    private String content;

    //TODO: double check these annotations
    @Column(name = "prep_time", columnDefinition = "INTEGER")
    private int prepTime;

    //TODO: double check these annotations
    @Column(name = "cook_time", columnDefinition = "INTEGER")
    private int cookTime;
}

