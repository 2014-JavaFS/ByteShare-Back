package com.revature.byteshare.user;

import com.revature.byteshare.util.exceptions.InvalidInputException;
import com.revature.byteshare.util.exceptions.UnauthorizedException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    private ResponseEntity<User> postCreateUser(@RequestBody User newUser) {
        User registerUser = userService.createUser(newUser);

        if (!isValidPassword(newUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(newUser);
        }

        if (registerUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(registerUser);
    }

    @GetMapping("/{userId}")
    private ResponseEntity<User> getUserById(@PathVariable int userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(userId));
    }

    @DeleteMapping("/{userId}")
    private ResponseEntity<Void> deleteUserById(@PathVariable int userId) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    private ResponseEntity<User> putUpdateUser(@Valid @RequestBody User updatedUser) {
        User updateUser = userService.updateUser(updatedUser);

        if (updatedUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

//    @PutMapping("/updatePrivliges")
//    private ResponseEntity<User> putUpdateUserTier(@RequestHeader User.userType userType, @RequestBody User updatedUser, @RequestHeader User.userType newType) throws AuthenticationException {
//        if (userType == User.userType.AUTHOR)
//    }

    @GetMapping
    private ResponseEntity<List<User>> getAllUsers(@RequestHeader String userType) {
        if (!userType.equals("ADMIN")) {
            throw new UnauthorizedException("You do not have the required permissions to complete this action.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    private boolean isValidPassword(String password) {
        String validPasswordRegex = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{10,30}";

        if (!password.matches(validPasswordRegex)) {
            throw new InvalidInputException("Your password must be between 10 and 30 characters and include at least one uppercase letter, one lowercase letter, and one special character. Please try again.");
        }

        else {
            return true;
        }
    }
}