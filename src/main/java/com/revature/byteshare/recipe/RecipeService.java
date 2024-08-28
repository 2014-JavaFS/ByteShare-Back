package com.revature.byteshare.recipe;

import com.revature.byteshare.tags.TagService;
import com.revature.byteshare.user.User;
import com.revature.byteshare.user.UserService;
import com.revature.byteshare.util.exceptions.DataNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserService userService;
    private final TagService tagService;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, UserService userService, TagService tagService) {
        this.recipeRepository = recipeRepository;
        this.userService = userService;
        this.tagService = tagService;
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

    public Recipe findByTitle(String title) {
        Recipe recipe = recipeRepository.findByTitle(title);
        if (recipe == null){
            throw new DataNotFoundException("No recipe with the title of " + title);
        } else {
            return recipe;
        }
    }

    public List<Recipe> searchFor(String query){
        List<Recipe> recipes = recipeRepository.searchFor(query);
        List<Recipe> recipesByTag = new ArrayList<>();
        try {
            recipesByTag = tagService.findAllRecipesByTagName(query);
            recipes.addAll(recipesByTag);
        } catch(DataNotFoundException e){
            //No recipes were found with query as their tag so nothing needs to be done here
        }
        if (recipes.isEmpty()){
            throw new DataNotFoundException("No recipes were found with query " + query);
        } else {
            return recipes;
        }
    }
}
