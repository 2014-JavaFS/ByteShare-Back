package com.revature.byteshare.userfeedback;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserFeedbackRepository extends JpaRepository<UserFeedback, Integer> {
    /**
     * Name of class attribute followed by RecipeId from the Recipe object
     * @param recipeId
     * @return
     */
    Optional<List<UserFeedback>> findAllByRecipeRecipeId(int recipeId);
}
