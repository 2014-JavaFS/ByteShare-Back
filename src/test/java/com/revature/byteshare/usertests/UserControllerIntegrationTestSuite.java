package com.revature.byteshare.usertests;

import com.revature.byteshare.user.User;
import com.revature.byteshare.user.UserController;
import com.revature.byteshare.user.UserRepository;
import com.revature.byteshare.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTestSuite {
    @MockBean
    private UserRepository mockRepository;

    @MockBean
    private UserService mockService;

    @Autowired
    private UserController sut;

    @Autowired
    private MockMvc mockMvc;

    private static User defaultUser = new User(1, "test@mail.com", "IaMaK1NgThis@Passw0rd", "testuser028", "Arjun", "Ramsinghani", User.UserType.ADMIN);
    private static String userJSON = "{\"userId\":1,\"email\":\"test@mail.com\",\"password\":\"IaMaK1NgThis@Passw0rd\",\"username\":\"testuser028\",\"first_name\":\"Arjun\",\"last_name\":\"Ramsinghani\",\"userType\":\"ADMIN\"}";

    @Test
    public void testGetAllUsers() throws Exception {
        when(mockService.findAll()).thenReturn(List.of(defaultUser));
        String expectedResult = "[" + userJSON + "]";

        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                        .header("userType", "ADMIN"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string(expectedResult));
    }

    @Test
    public void testGetUserById() throws Exception {
        when(mockService.findById(1)).thenReturn(defaultUser);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string(userJSON));
    }

    @Test
    public void testCreateUser() throws Exception {
        when(mockService.createUser(defaultUser)).thenReturn(defaultUser);

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJSON))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andExpect(MockMvcResultMatchers.content().string(userJSON));
    }

    @Test
    public void testUpdateUser() throws Exception {
        when(mockService.updateUser(defaultUser)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.put("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string(userJSON));
    }

    @Test
    public void testUpdateUserAccessLevel() throws Exception {
        when(mockService.updateUserAccessLevel(defaultUser, User.UserType.AUTHOR)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.put("/users/updatePrivliges")
                        .header("userType", "ADMIN")
                        .header("newType", "AUTHOR")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string(userJSON));
    }

    @Test
    public void testDeleteUser() throws Exception {
        when(mockService.deleteUser(1)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/1"))
                .andExpect(MockMvcResultMatchers.status().is(204));
    }
}
