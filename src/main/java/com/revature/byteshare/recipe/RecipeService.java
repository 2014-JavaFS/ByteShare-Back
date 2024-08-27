package com.revature.byteshare.recipe;

import com.revature.byteshare.user.User;
import com.revature.byteshare.user.UserService;
import com.revature.byteshare.util.exceptions.DataNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    private RecipeRepository recipeRepository;
    private UserService userService;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, UserService userService) {
        this.recipeRepository = recipeRepository;
        this.userService = userService;
    }

    public List<Recipe> findAll() {
        List<Recipe> recipes = recipeRepository.findAll();
        if (recipes.isEmpty()){
            throw new DataNotFoundException("No recipe information available");
        } else {
            return recipes;
        }
    }

    public Recipe create(RecipeDto recipeDto) {
        User author = userService.findById(recipeDto.getAuthor());
        Recipe recipe = new Recipe();
        recipe.setAuthor(author);
        recipe.setTitle(recipeDto.getTitle());
        recipe.setContent(recipeDto.getContent());
        recipe.setCookTime(recipeDto.getCookTime());
        recipe.setPrepTime(recipeDto.getPrepTime());
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
}
