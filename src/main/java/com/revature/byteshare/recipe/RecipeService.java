package com.revature.byteshare.recipe;

import com.revature.byteshare.util.exceptions.DataNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class RecipeService {
    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> findAll() {
        List<Recipe> recipes = recipeRepository.findAll();
        if (recipes.isEmpty()){
            throw new DataNotFoundException("No recipe information available");
        } else {
            return recipes;
        }
    }

    public Recipe create(Recipe recipe) {
        recipe.setDatePosted(Timestamp.from(Instant.now()));
        return recipeRepository.save(recipe);
    }

    public Recipe findById(int recipeNumber) {
        return recipeRepository.findById(recipeNumber)
                .orElseThrow(() -> new DataNotFoundException("No recipe with the ID of " + recipeNumber));
    }

    @Transactional
    public boolean update(Recipe recipeToUpdate) {
        Recipe foundRecipe = recipeRepository.findById(recipeToUpdate.getRecipeId())
                .orElseThrow(() -> new DataNotFoundException("No recipe with the ID of " + recipeToUpdate.getRecipeId()));
        if (foundRecipe == null) {
            throw new DataNotFoundException("Order with that ID is not in the database, please check again");
        }
        return true;
    }

    public boolean delete(Recipe recipe) {
        recipeRepository.delete(recipe);
        return true;
    }

    public List<Recipe> findAllById(int userId) {
        List<Recipe> recipes = recipeRepository.findAllByAuthorUserId(userId);
        if (recipes.isEmpty()){
            throw new DataNotFoundException("No recipes with that userId was found");
        } else {
            return recipes;
        }
    }

    public Recipe findByTitle(String title) {
        Recipe recipe = recipeRepository.findByTitle(title);
        if (recipe == null){
            throw new DataNotFoundException("No recipe with the title of " + title);
        } else {
            return recipe;
        }
    }

    public Recipe findByAuthorId(int authorId) {
        Recipe recipe = recipeRepository.findByAuthorUserId(authorId);
        if (recipe == null){
            throw new DataNotFoundException("No recipe with the authorId of " + authorId);
        } else {
            return recipe;
        }
    }

    public Recipe findByAuthor(String author) {
        Recipe recipe = recipeRepository.findByAuthor(author);
        if (recipe == null){
            throw new DataNotFoundException("No recipe with the author of " + author);
        } else {
            return recipe;
        }
    }
}
