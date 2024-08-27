package com.revature.byteshare.favorites;

import com.revature.byteshare.favorites.dto.FavoriteResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    @Autowired
    FavoriteService(FavoriteRepository favoriteRepository){
        this.favoriteRepository=favoriteRepository;
    }

    public Favorite create(Favorite favorite){
        return favoriteRepository.save(favorite);
    }


    public List<Favorite> findByRecipeID(int recipeID){
        //return favoriteRepository.findByRecipeID(recipeID);

        return null;
    }

    public List<Favorite> findAll(){
        return favoriteRepository.findAll();
    }
    //For getting Git Commit To Actually Post to GitHub For Merge

    public List<FavoriteResponseDTO> findAllWithID(int userID){


        List<Favorite> buisnessLogicList=favoriteRepository.findAllByUserUserId(userID);
        List<FavoriteResponseDTO> returnList = new ArrayList<>();
        for(int i=0;i<buisnessLogicList.size();i++){
            FavoriteResponseDTO tempDTO = new FavoriteResponseDTO(
                    buisnessLogicList.get(i).getUser(),
                    buisnessLogicList.get(i).getRecipeToSave()
                    );
            returnList.add(tempDTO);
        }
        return returnList;
    }

    public boolean removeFavorite(int userID, int recipeID){

        List<Favorite> temp =favoriteRepository.findAllByUserUserId(userID);
        for(int i=0; i<temp.size();i++){
            if(temp.get(i).getRecipeToSave().getRecipeId() == recipeID) {
                favoriteRepository.delete(temp.get(i));
                return true;
            }
        }

        return false;
    }
}
