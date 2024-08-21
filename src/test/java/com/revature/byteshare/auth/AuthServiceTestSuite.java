package com.revature.byteshare.auth;

import com.revature.byteshare.user.User;
import com.revature.byteshare.user.UserService;
import com.revature.byteshare.util.auth.AuthService;
import com.revature.byteshare.util.auth.LoginDto;
import com.revature.byteshare.util.auth.RegisterDto;
import com.revature.byteshare.util.exceptions.UnauthorizedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.naming.AuthenticationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTestSuite {
    @Mock
    private UserService userService;
    @InjectMocks
    private AuthService authService;
    private static final RegisterDto registerDto = new RegisterDto("test@example.com","password123","John","Doe","johndoe",User.UserType.USER);
    private static final LoginDto loginDto = new LoginDto("test@example.com", "password123");
    private static final User user = new User("test@example.com", "password123", "John", "Doe", "johndoe", User.UserType.USER);
    @Test
    public void login_with_correct_email_and_password_returns_user() throws AuthenticationException {

        when(userService.lookupUserIdByEmail("test@example.com")).thenReturn(1);
        when(userService.findById(1)).thenReturn(user);

        User result = authService.login(loginDto);

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
    }
    @Test
    public void login_with_incorrect_password_throws_authentication_exception() throws AuthenticationException {

        when(userService.lookupUserIdByEmail("test@example.com")).thenReturn(1);
        when(userService.findById(1)).thenReturn(user);
        LoginDto wrongDto = new LoginDto("test@example.com","1234");
        assertThrows(AuthenticationException.class, () -> {
            authService.login(wrongDto);
        });
    }
    @Test
    public void register_with_existing_email_returns_user_object() {


        when(userService.lookupUserIdByEmail("test@example.com")).thenReturn(1);
        when(userService.findByUserIdNumber(1)).thenReturn(user);

        User result = authService.register(registerDto);

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
    }
}
