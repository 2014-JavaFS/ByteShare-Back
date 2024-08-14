package com.revature.byteshare.Tags;

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
    public List<Tag> findAllTags(){
        return tagsRepository.findAll();
    }
    // TODO: need to update the exception thrown once aspects are made
    public List<Tag> findAllByRecipeID(int id ){
        return tagsRepository.findAllByRecipeID(id).orElseThrow();
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
