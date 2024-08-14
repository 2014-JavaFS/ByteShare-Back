package com.revature.byteshare.Tags;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;

    @Autowired
    public TagController(TagRepository tagRepository){
        this.tagService = new TagService(tagRepository);
    }

    @GetMapping
    public List<Tag> getAllTags(){
        return tagService.findAllTags();
    }
    @GetMapping("/{recipeId")
    public List<Tag> getAllTagsByRecipeId(@PathVariable int recipeId){
        return tagService.findAllByRecipeID(recipeId);
    }
    @PostMapping
    public Tag createTag(Tag tag){
        return tagService.create(tag);
    }
    @PutMapping
    public boolean updateTag(Tag tag){
        return tagService.update(tag);
    }
    @DeleteMapping("/tagId")
    public boolean deleteTag(@PathVariable int tagId){
        return tagService.delete(tagId);
    }

}
