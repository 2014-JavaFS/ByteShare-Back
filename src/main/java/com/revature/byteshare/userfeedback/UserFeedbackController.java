package com.revature.byteshare.userfeedback;

import com.revature.byteshare.user.User;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.revature.byteshare.userfeedback.UserFeedback;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class UserFeedbackController {
    private final UserFeedbackService userFeedbackService;

    @Autowired
    public UserFeedbackController(UserFeedbackService userFeedbackService){
        this.userFeedbackService = userFeedbackService;
    }

    @GetMapping
    private ResponseEntity<List<UserFeedback>> getAllUserFeedback(){
        return ResponseEntity.status(HttpStatus.OK).body(userFeedbackService.findAll());
    }

    @GetMapping("/user/{userId}")
    private ResponseEntity<List<UserFeedback>> getFeedbackByUserId(@PathParam("userId") int userId){
        return ResponseEntity.status(HttpStatus.OK).body(userFeedbackService.findAllByUserId(userId));
    }

    @GetMapping("/myFeedbackHistory")
    private ResponseEntity<List<UserFeedback>> getFeedbackFromCurrentUser(@RequestHeader int userId){
        return ResponseEntity.status(HttpStatus.OK).body(userFeedbackService.findAllByUserId(userId));
    }

    @GetMapping("/post/{recipeId}")
    private ResponseEntity<List<UserFeedback>> getFeedbackByPostId(@PathParam("recipeId") int postId){
        return ResponseEntity.status(HttpStatus.OK).body(userFeedbackService.findAllByPostId(postId));
    }

    @PostMapping("/{recipeId}")
    private ResponseEntity<UserFeedback>  postUserFeedback(@PathParam("recipeId") int recipeId,
                                                           @RequestHeader int userId,
                                                           @RequestBody UserFeedback userFeedback){
        UserFeedback postedFeedback = userFeedbackService.createUserFeedback(recipeId, userId, userFeedback);
        if(postedFeedback != null) return ResponseEntity.status(HttpStatus.OK).body(postedFeedback);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
