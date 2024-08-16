package com.revature.byteshare.favorites;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class favoriteService {

    private final favoriteRepository favoriteRepository;

    @Autowired
    favoriteService(favoriteRepository favoriteRepository){
        this.favoriteRepository=favoriteRepository;
    }

    public favorite create(favorite favorite){
        return favoriteRepository.save(favorite);
    }


    public List<favorite> findByRecipeID(int recipeID){
        //return favoriteRepository.findByRecipeID(recipeID);

        return null;
    }

    public List<favorite> findAll(){
        return favoriteRepository.findAll();
    }

    public List<favorite> findAllWithID(int userID){

        List<favorite> temp =findAll();
        List<favorite> buisnessLogicList = new ArrayList<>();
        for(int i=0; i<temp.size();i++){
            if(temp.get(i).getAccountAssociatedID() == userID) {
                buisnessLogicList.add(temp.get(i));
            }
        }
        //buisnessLogicList=favoriteRepository.findByUserID(userID);
        return buisnessLogicList;
    }

    public boolean removeFavorite(int userID, int recipeID){

        List<favorite> temp =findAll();
        for(int i=0; i<temp.size();i++){
            if(temp.get(i).getRecipeToSave() == recipeID) {
                favoriteRepository.delete(temp.get(i));
                return true;
                }
        }

        return false;
    }
}
