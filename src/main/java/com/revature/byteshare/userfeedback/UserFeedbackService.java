package com.revature.byteshare.userfeedback;

import com.revature.byteshare.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserFeedbackService {
    private final UserFeedbackRepository userFeedbackRepository;

    @Autowired
    public UserFeedbackService(UserFeedbackRepository userFeedbackRepository){
        this.userFeedbackRepository = userFeedbackRepository;
    }

    public List<UserFeedback> findAll(){
        return userFeedbackRepository.findAll();
    }

    public List<UserFeedback> findAllByUserId(int userId) {
        return userFeedbackRepository.findAllById(Collections.singleton(userId));
    }

    public List<UserFeedback> findAllByPostId(int recipeId){
        return userFeedbackRepository.findAllByRecipeId(recipeId).orElseThrow();
    }

    public UserFeedback createUserFeedback(int recipeId, int userId, UserFeedback userFeedback){
        return userFeedbackRepository.save(userId, recipeId, userFeedback.getRating(), userFeedback.getCommentText()).orElseThrow();
    }
}
