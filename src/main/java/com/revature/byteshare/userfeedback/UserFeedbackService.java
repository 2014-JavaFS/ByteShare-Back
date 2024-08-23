package com.revature.byteshare.userfeedback;

import com.revature.byteshare.User.User;
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
    //todo:Define some business logic for "findall"
    public List<UserFeedback> findAll(){
        return userFeedbackRepository.findAll();
    }

    public List<UserFeedback> findAllByUserId(int userId) {
        return userFeedbackRepository.findAllById(Collections.singleton(userId));
    }

    public List<UserFeedback> findAllByPostId(int postId){
        return userFeedbackRepository.findAllByRecipeId(postId).orElseThrow();
    }
}
