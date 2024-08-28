package com.revature.byteshare.favorites;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;


@SpringBootTest
@AutoConfigureMockMvc
public class FavoriteControllerTesting {
    //For getting Git Commit To Actually Post to GitHub For Merge

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FavoriteService mockedFavoriteService;

    @MockBean
    private FavoriteRepository mockedFavoriteRepository;

    @Mock
    private RestTemplate restTemplate;

    @Captor
    private ArgumentCaptor<Favorite> favoriteArgumentCaptor;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddingToFavorites() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/favorite")
                .header("userID", 1)
                .header("recipeID",1)

        ).andExpect(MockMvcResultMatchers.status().is(201));
    }

    @Test
    public void testRemovingFromFavorites()throws Exception{
        //TEST-1 User Shouldn't have that Recipie Favorited
        mockMvc.perform(MockMvcRequestBuilders.delete("/favorite")
                .header("userID", 1)
                .header("recipeID",1)
        ).andExpect(MockMvcResultMatchers.status().is(200));

    }

    @Test
    public void testGetUsersFavorites()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/favorite")
                .header("userID", 1)
        ).andExpect(MockMvcResultMatchers.status().is(200));

    }
}
