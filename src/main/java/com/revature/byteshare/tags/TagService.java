package com.revature.byteshare.tags;

import com.revature.byteshare.recipe.Recipe;
import com.revature.byteshare.util.exceptions.DataNotFoundException;
import com.revature.byteshare.util.exceptions.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    private final TagRepository tagsRepository;

    @Autowired
    public TagService(TagRepository tagsRepository){
            this.tagsRepository = tagsRepository;
    }

    public List<String> findAllTagNamesByRecipe(Recipe recipe){
        return tagsRepository.findAllTagNamesByRecipe(recipe).orElseThrow(()->new DataNotFoundException("This recipe has no tags"));
    }
    public List<Recipe> findAllRecipeByTagName(String tag_name){
        return tagsRepository.findAllRecipesByTags(tag_name).orElseThrow(()->new DataNotFoundException("No recipes were found by that tag name"));
    }
    public List<Tag> findAllTags(){
        List<Tag> list = tagsRepository.findAll();
        if(list.isEmpty()){
            throw new DataNotFoundException("No Tags Exist");
        }
        return list;
    }

    public Tag create(Tag tagToMake){
        if(findTagById(tagToMake.getTag_id()).isPresent()){
            throw new InvalidInputException("This Tag Already Exists");
        }
        return tagsRepository.save(tagToMake);
    }
    public boolean delete(int tagId){
        if(findTagById(tagId).isEmpty()){
            throw new DataNotFoundException("This Tag does not exist, so it cannot be deleted");
        }
        tagsRepository.deleteById(tagId);
        return true;
    }
    public boolean update(Tag tagToUpdate){
        if(findTagById(tagToUpdate.getTag_id()).isEmpty()){
            throw new DataNotFoundException("This Tag does not exist, so it cannot be updated");
        }
        tagsRepository.save(tagToUpdate);
        return true;
    }
    public Optional<Tag> findTagById(int tag_id){
        return tagsRepository.findById(tag_id);
    }
}
