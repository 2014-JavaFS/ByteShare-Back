package com.revature.byteshare.util.auth;
import com.revature.byteshare.security.JwtGenerator;
import com.revature.byteshare.user.User;
import com.revature.byteshare.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

/**
 * <h5>REST controller that handles authentication-related requests</h5>
 * <p>This controller maps all requests to <em>"/auth"</em>.
 * It uses AuthService to perform authentication logic.</p>
 */
@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtGenerator jwtGenerator;

    /**
     * <h5>Constructs a new AuthController with AuthService injected.</h5>
     * @param authService the AuthService to be used by this controller
     */
    @Autowired
    public AuthController(AuthService authService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
                          UserService userService, JwtGenerator jwtGenerator) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("register")
    public ResponseEntity<User> register(@RequestBody RegisterDto registerDto) {
        System.out.println("Hit");
        User user = authService.register(registerDto);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        User registeredUser = new User(registerDto.getEmail(), passwordEncoder.encode(registerDto.getPassword()), registerDto.getFirstName(), registerDto.getLastName(), registerDto.getUsername(),registerDto.getUserType());
        User newUser = userService.createUser(registeredUser);
        return ResponseEntity.status(HttpStatus.OK).body(newUser);
    }

    /**
     * <h5>Handles the login request.</h5>
     * <p>Calls the login method in AuthService</p>
     *
     * @param loginDto the form contianing login details
     */

    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        int id = userService.lookupUserIdByEmail(loginDto.getEmail());
        String token = jwtGenerator.generateToken(authentication, id);
        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);

    }

}
