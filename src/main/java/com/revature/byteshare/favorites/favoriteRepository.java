package com.revature.byteshare.favorites;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface favoriteRepository extends JpaRepository<favorite, Integer> {

    //@Query(value ="SELECT f from favorite f where f.account_associatedid=?1", nativeQuery = true)
    //List<favorite> findByUserID(int userID);
    //@Query(value ="SELECT f from favorite f where f.recipe_to_save=?1", nativeQuery = true)
    //List<favorite> findByRecipeID(int recipeID);
}
