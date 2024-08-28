package com.revature.byteshare.favorites.dto;

import com.revature.byteshare.recipe.Recipe;
import com.revature.byteshare.user.User;

import java.sql.Timestamp;

public class FavoriteResponseDTO {


    private int userId;
    private int recipeId;
    private String name;
    private String author;
    private String content;
    private Timestamp datePosted;


    public FavoriteResponseDTO(){}
    public FavoriteResponseDTO(User user, Recipe recipe){
            this.userId=user.getUserId();
            this.recipeId=recipe.getRecipeId();
            this.author=recipe.getAuthor().getUsername();
            this.name=user.getUsername();
            this.content=recipe.getContent();
            this.datePosted=recipe.getDate();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Timestamp datePosted) {
        this.datePosted = datePosted;
    }
}
