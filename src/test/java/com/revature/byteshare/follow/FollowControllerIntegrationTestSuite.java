package com.revature.byteshare.follow;


import com.revature.byteshare.user.User;
import com.revature.byteshare.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {FollowController.class})
@AutoConfigureMockMvc
@EnableWebMvc
public class FollowControllerIntegrationTestSuite {
    @MockBean
    private FollowRepository followRepository;

    @MockBean
    private FollowService followService;

    @MockBean
    private UserService userService;

    @Autowired
    private FollowController followController;

    @Autowired
    private MockMvc mockMvc;

    private static User defaultFollower = new User(1, "scooby@mystery.com", "sc00byd00by", "ScoobertDoobert", "Scooby", "Doo", "auth", User.userType.USER);
    private static String followerJSON = ("{\"user_id\":1,\"email\":\"scooby@mystery.com\",\"password\":\"sc00byd00by\",\"username\":\"ScoobertDoobert\",\"first_name\":\"Scooby\",\"last_name\":\"Doo\",\"auth_token\":\"auth\",\"user_type\":\"USER\"}");

    private static User defaultFollowing = new User(2, "shaggy@mystery.com", "z01nk5!", "Chef_Rogers", "Norville", "Rogers", "auth", User.userType.USER);
    private static String followingJSON = ("{\"user_id\":2,\"email\":\"shaggy@mystery.com\",\"password\":\"z01nk5!\",\"username\":\"Chef_Rogers\",\"first_name\":\"Norville\",\"last_name\":\"Rogers\",\"auth_token\":\"auth\",\"user_type\":\"USER\"}");

    private static Follow defaultFollow = new Follow(1, defaultFollower, defaultFollowing);
    private static String followJSON = "{\"follow_id\":1,\"follower\":" + followerJSON + ",\"following\":" + followingJSON + "}";

    @Test
    public void testGetFollowing() throws Exception {
        when(followService.findAllFollowing(defaultFollower.getUser_id()))
                .thenReturn(List.of(defaultFollow));

        String expectedResult = "[" + followJSON + "]";

        mockMvc.perform(MockMvcRequestBuilders.get("/follow/following")
                        .header("currentUserId", defaultFollower.getUser_id())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string(expectedResult));
    }

    @Test
    public void testGetFollowers() throws Exception {
        when(followService.findAllFollowers(defaultFollowing.getUser_id()))
                .thenReturn(List.of(defaultFollow));

        String expectedResult = "[" + followJSON + "]";

        mockMvc.perform(MockMvcRequestBuilders.get("/follow/followers")
                        .header("currentUserId", defaultFollowing.getUser_id())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string(expectedResult));
    }

    @Test
    public void testGetFollowingOfUser() throws Exception {
        when(followService.findAllFollowing(defaultFollower.getUser_id()))
                .thenReturn(List.of(defaultFollow));

        String expectedResult = "[" + followJSON + "]";

        mockMvc.perform(MockMvcRequestBuilders.get("/follow/following/user?userId="
                                + defaultFollower.getUser_id())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string(expectedResult));
    }

    @Test
    public void testGetFollowersOfUser() throws Exception {
        when(followService.findAllFollowers(defaultFollowing.getUser_id()))
                .thenReturn(List.of(defaultFollow));

        String expectedResult = "[" + followJSON + "]";

        mockMvc.perform(MockMvcRequestBuilders.get("/follow/followers/user?userId="
                                + defaultFollowing.getUser_id())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string(expectedResult));
    }

    @Test
    public void testPostFollow() throws Exception {
        when(followService.createFollow(any(Follow.class))).thenReturn(defaultFollow);
        when(userService.findByUserIdNumber(defaultFollower.getUser_id())).thenReturn(defaultFollower);
        when(userService.findByUserIdNumber(defaultFollowing.getUser_id())).thenReturn(defaultFollowing);

        String expectedResult = followJSON;

        mockMvc.perform(MockMvcRequestBuilders.post("/follow?followingId="
                                + defaultFollowing.getUser_id())
                        .header("currentUserId", defaultFollower.getUser_id())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value()))
                .andExpect(MockMvcResultMatchers.content().string(expectedResult));
    }

    @Test
    public void testDeleteFollow() throws Exception {
        when(followService.deleteFollow(defaultFollow))
                .thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/follow?followingId="
                                + defaultFollowing.getUser_id())
                        .header("currentUserId", defaultFollower.getUser_id())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.NO_CONTENT.value()));
    }
}
