package com.revature.byteshare.userfeedback;

import com.revature.byteshare.recipe.Recipe;
import com.revature.byteshare.recipe.RecipeRepository;
import com.revature.byteshare.user.User;
import com.revature.byteshare.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserFeedbackService {
    private final UserFeedbackRepository userFeedbackRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    @Autowired
    public UserFeedbackService(UserFeedbackRepository userFeedbackRepository, UserRepository userRepository, RecipeRepository recipeRepository){
        this.userFeedbackRepository = userFeedbackRepository;
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
    }

    public List<UserFeedback> findAll(){
        return userFeedbackRepository.findAll();
    }

    public List<UserFeedback> findAllByUserId(int userId) {
        return userFeedbackRepository.findAllById(Collections.singleton(userId));
    }

    public List<UserFeedback> findAllByPostId(int recipeId){
        return userFeedbackRepository.findAllByRecipeRecipeId(recipeId).orElseThrow();
    }

    public UserFeedback createUserFeedback(int recipeId, int userId, UserFeedback userFeedback){
        User user = userRepository.findByUserId(userId).orElseThrow();
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow();
        UserFeedback newUserFeedback = new UserFeedback(user, recipe, userFeedback.getRating(), userFeedback.getCommentText());
        return userFeedbackRepository.save(newUserFeedback);
    }
}
