package com.revature.byteshare.tags;


import com.revature.byteshare.recipe.Recipe;
import com.revature.byteshare.recipe.RecipeService;
import com.revature.byteshare.tags.dtos.TagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;
    private final RecipeService recipeService;
    //TODO will need a recipe service reference here to facilitate find recipes by tag

    @Autowired
    public TagController(TagService tagService, RecipeService recipeService){
        this.tagService = tagService;
        this.recipeService = recipeService;
    }

    private TagDTO mapToDTO(Tag tag){
        return new TagDTO(
                tag.getRecipe().getId(),
                tag.getTag_name()
        );
    }
    @PostMapping
    public Tag createTag(@RequestBody TagDTO tagDTO){
        System.out.println("\nInitial Tag being created :"+ tagDTO);

        Recipe recipe = recipeService.findById(tagDTO.getRecipe_id());
        System.out.println("Recipe that was returned from recipeService :"+ recipe);

        Tag test = new Tag();
        test.setRecipe(recipe);
        test.setTag_name(tagDTO.getTag_name());

        System.out.println("Tag That was made from the returned recipe: "+test);

        Tag result = tagService.create(test);
        System.out.println("Tag to be sent back to the client :"+result);
        return result;
    }

    //TODO This is the method that will take a List of Ints from tagservice
    // and use it to make a list of recipes from recipe service. Will implement ASAP
    /*
    @GetMapping("/{tag_name}") // possibly use query param? not entirely sure when to use one over the other tbh
    public List<Recipe> getAllRecipesByTagName(@PathVariable String tag_name){
        return
    }
     */

    // TODO : FOR REVIEWER: do we actually just want this to return a list
    //  of Tag_names or do we want it returning the whole tag object?
    /**
     * This method is used when displaying a recipe. It will return all Tag
     * Names associated with this recipe id.
     * @param recipeId
     * @return A list of Tag Names (Strings)
     */
    @GetMapping("/{recipeId}")
    public List<String> getAllTagsByRecipeId(@PathVariable int recipeId){
        return tagService.findAllTagNamesByRecipeID(recipeId);
    }
    @GetMapping
    public List<Tag> getAllTags(){
        return tagService.findAllTags();
    }

    @PutMapping
    public  boolean updateTag(@RequestBody Tag tag){
        return tagService.update(tag);
    }
    @DeleteMapping("/{tagId}")
    public boolean deleteTag(@PathVariable int tagId){
        return tagService.delete(tagId);
    }

}
