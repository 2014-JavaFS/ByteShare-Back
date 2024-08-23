package com.revature.byteshare.tags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private final TagRepository tagsRepository;

    @Autowired
    public TagService(TagRepository tagsRepository){
            this.tagsRepository = tagsRepository;
    }
    // TODO: need to update the exception thrown once aspects are made
//    public List<String> findAllTagNamesByRecipeID(int id ){
//        return tagsRepository.findAllTagNamesByRecipeID(id).orElseThrow();
//    }
    // TODO: need to update the exception thrown once aspects are made
//    public List<Integer> findAllRecipeByTagName(String tag_name){
//        return tagsRepository.findAllRecipesByTags(tag_name).orElseThrow();
//    }
    public List<Tag> findAllTags(){
        return tagsRepository.findAll();
    }
    public Tag create(Tag tagToMake){
        return tagsRepository.save(tagToMake);
    }
    public boolean delete(int tagId){
        tagsRepository.deleteById(tagId);
        return true;
    }
    public boolean update(Tag tagTpUpdate){
        tagsRepository.save(tagTpUpdate);
        return true;
    }
}
