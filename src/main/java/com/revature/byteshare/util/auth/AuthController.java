package com.revature.byteshare.util.auth;

import com.revature.byteshare.user.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private PasswordEncoder passwordEncoder;
}
