package com.revature.byteshare.tags;


import com.revature.byteshare.recipe.Recipe;
import com.revature.byteshare.recipe.RecipeService;
import com.revature.byteshare.tags.models.Tag;
import com.revature.byteshare.tags.models.TagDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;
    private final RecipeService recipeService;

    @Autowired
    public TagController(TagService tagService, RecipeService recipeService){
        this.tagService = tagService;
        this.recipeService = recipeService;
    }
    //have this here just in case, may not end needing this
    private TagDTO mapToDTO(Tag tag){
        return new TagDTO(
                tag.getRecipe().getRecipeId(),
                tag.getTag_name()
        );
    }

    @GetMapping("/findRecipes/{tag_name}")
    public List<Recipe> getAllRecipesByTagName(@PathVariable String tag_name){
        return tagService.findAllRecipesByTagName(tag_name);
    }

    /**
     * This method is used when displaying a recipe. It will return all Tag
     * Names associated with this recipe id.
     * @param recipeId
     * @return A list of Tag Names (Strings)
     */
    @GetMapping("/findTagNames/{recipeId}")
    public List<String> getAllTagNamesByRecipeId(@PathVariable int recipeId){
        Recipe recipe = recipeService.findById(recipeId);

        return tagService.findAllTagNamesByRecipe(recipe);
    }
    @PostMapping
    public Tag createTag(@RequestBody TagDTO tagDTO){

        Recipe recipe = recipeService.findById(tagDTO.getRecipe_id());

        Tag test = new Tag();
        test.setRecipe(recipe);
        test.setTag_name(tagDTO.getTag_name());

        return  tagService.create(test);
    }
    @GetMapping
    public List<Tag> getAllTags(){
        return tagService.findAllTags();
    }

    @PutMapping
    public  ResponseEntity<Tag> updateTag(@Valid @RequestBody Tag tag){
        tagService.update(tag);
        return ResponseEntity.ok(tag);
    }
    @DeleteMapping("/{tagId}")
    public ResponseEntity<Void> deleteTag(@PathVariable int tagId){
        tagService.delete(tagId);
        return ResponseEntity.noContent().build();
    }

}
