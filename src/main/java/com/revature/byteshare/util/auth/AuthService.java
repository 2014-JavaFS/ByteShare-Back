package com.revature.byteshare.util.auth;

import com.revature.byteshare.user.User;
import com.revature.byteshare.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.NoSuchElementException;

@Service
public class AuthService {

    private final UserService userService;

    @Autowired
    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public User login(LoginDto loginDto) throws AuthenticationException {
        int id = userService.lookupUserIdByEmail(loginDto.getEmail());
        User user = userService.findById(id);
        if (user.getPassword().equals(loginDto.getPassword())) {
            return user;
        } else {
            throw new AuthenticationException();
        }
    }

    public User register(RegisterDto registerDto) {
        int id;
        try {
            id = userService.lookupUserIdByEmail(registerDto.getEmail());
            return userService.findByUserIdNumber(id);
        } catch(NoSuchElementException e) {
            return null;
        }
    }
}
