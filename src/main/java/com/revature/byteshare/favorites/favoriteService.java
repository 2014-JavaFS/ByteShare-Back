package com.revature.byteshare.favorites;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class favoriteService {

    private final favoriteRepository favoriteRepository;

    favoriteService(favoriteRepository favoriteRepository){
        this.favoriteRepository=favoriteRepository;
    }

    public favorite create(favorite favorite){
        return favoriteRepository.save(favorite);
    }


    public List<favorite> findByRecipeID(int recipeID){
        return favoriteRepository.findByRecipeID(recipeID);
    }

    public List<favorite> findAll(){
        return favoriteRepository.findAll();
    }

    public List<favorite> findAllWithID(int userID){
        List<favorite> buisnessLogicList;
        buisnessLogicList=favoriteRepository.findByUserID(userID);
        return buisnessLogicList;
    }

    public boolean removeFavorite(int userID, int recipeID){

        List<favorite> temp =findAllWithID(userID);
        for(int i=0; i<temp.size();i++){
            if(temp.get(i).getRecipeToSave() == recipeID) {
                favoriteRepository.delete(temp.get(i));
                return true;
                }
        }

        return false;
    }
}
