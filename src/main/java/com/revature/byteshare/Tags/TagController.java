package com.revature.byteshare.Tags;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;
    //TODO will need a recipe service reference here to facilitate find recipes by tag

    @Autowired
    public TagController(TagRepository tagRepository){
        this.tagService = new TagService(tagRepository);
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
    /*
     * This method is used when displaying a recipe. It will return all Tag
     * Names associated with this recipe id.
     * @param recipeId
     * @return A list of Tag Names (Strings)
     */
//    @GetMapping("/{recipeId}")
//    public List<String> getAllTagsByRecipeId(@PathVariable int recipeId){
//        return tagService.findAllTagNamesByRecipeID(recipeId);
//    }
    @GetMapping
    public List<Tag> getAllTags(){
        return tagService.findAllTags();
    }
    @PostMapping
    public Tag createTag(@RequestBody Tag tag){
        return tagService.create(tag);
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
