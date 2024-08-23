package com.revature.byteshare.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.byteshare.security.JwtGenerator;
import com.revature.byteshare.user.User;
import com.revature.byteshare.user.UserService;
import com.revature.byteshare.util.auth.AuthController;
import com.revature.byteshare.util.auth.AuthService;
import com.revature.byteshare.util.auth.LoginDto;
import com.revature.byteshare.util.auth.RegisterDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.revature.byteshare.util.auth.AuthResponseDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {AuthController.class})
@AutoConfigureMockMvc
@EnableWebMvc
public class AuthControllerIntegrationTestSuite {
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private AuthService authService;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private UserService userService;
    @MockBean
    private JwtGenerator jwtGenerator;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AuthController authController;
    private static final LoginDto loginDto = new LoginDto("test@example.com", "password123");
    private static final RegisterDto registerDto = new RegisterDto("test@example.com","password123","John","Doe","johndoe",User.UserType.USER);
    private static final User user = new User(registerDto.getEmail(), "password123", registerDto.getFirstName(), registerDto.getLastName(), registerDto.getUsername(), registerDto.getUserType());
    private static final String userJSON = "{\"userId\":1,\"email\":\"test@example.com\",\"password\":\"$2a$10$VLka9Q0Kq.ktavEVLgsIduTs3XaAxdn85NKldM70TmmGHyxyM8n4W\",\"username\":\"johndoe\",\"first_name\":\"John\",\"last_name\":\"Doe\",\"userType\":\"ADMIN\"}";
    private static final String registerDtoJson = "{\"email\":\"test@example.com\",\"password\":\"password123\",\"first_name\":\"John\",\"last_name\":\"Doe\",\"username\":\"johndoe\",\"userType\":\"ADMIN\"}";

//    @Test
//    public void test_register_new_user_with_valid_details() throws Exception {
//
//        //when(authService.register(registerDto)).thenReturn(null);
////        when(userService.createUser(user)).thenReturn(user);
////        when(userService.createUser(any(User.class))).thenReturn(user);
//        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(registerDtoJson))
//                .andExpect(MockMvcResultMatchers.status().is(200))
//                .andExpect(MockMvcResultMatchers.content().string(userJSON));
//
//    }

//    @Test
//    public void testLogin_validCredentials_returnsToken() throws Exception {
//        // Mock user service to return a valid user ID
//        String email = "test@example.com";
//        int userId = 1;
//        when(userService.lookupUserIdByEmail(email)).thenReturn(userId);
//
//        // Mock authentication manager to return a successful authentication
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, "password123");
//        Authentication mockAuthentication = mock(Authentication.class);
//        when(authenticationManager.authenticate(authenticationToken)).thenReturn(mockAuthentication);
//
//        // Mock JWT generator to return a token
//        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGVtYWlsLmNvbSIsInVzZXJJZCI6NCwiaWF0IjoxNzI0MzM5MTY0LCJleHAiOjE3MjQzMzkyMzR9.C-69La1wat1FH-QeFsDCy8_-2YwhpXdky9GSW7r3jNQ";
//        when(jwtGenerator.generateToken(mockAuthentication, userId)).thenReturn(token);
//
//        // Build login request DTO
//        LoginDto loginDto = new LoginDto(email, "password");
//        String loginJson = new ObjectMapper().writeValueAsString(loginDto);
//
//        // Perform login request
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(loginJson))
//                .andExpect(MockMvcResultMatchers.status().is(200))
//                .andReturn();
//
//        // Parse response
//        String responseJson = mvcResult.getResponse().getContentAsString();
//        AuthResponseDto responseDto = new ObjectMapper().readValue(responseJson, AuthResponseDto.class);
//
//        // Assert response
//
//    }

}
