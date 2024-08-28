package com.revature.byteshare.favorites;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.byteshare.favorites.dto.FavoriteResponseDTO;
import com.revature.byteshare.recipe.Recipe;
import com.revature.byteshare.recipe.RecipeService;
import com.revature.byteshare.user.User;
import com.revature.byteshare.user.UserService;
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

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


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

    @MockBean
    private UserService mockedUserService;

    @MockBean
    private RecipeService mockedRecipeService;

    @Mock
    private RestTemplate restTemplate;

    @Captor
    private ArgumentCaptor<Favorite> favoriteArgumentCaptor;

    @Captor
    private ArgumentCaptor<Recipe> recipeArgumentCaptor;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Autowired
    private ObjectMapper objectMapper;

    private static User tempUser = new User(1, "ja;dlfj;s@f;lajsfd.com", "%jafes;jsdfadsf", "adfsadf",
            "Test", "TEST",
            User.UserType.AUTHOR);
    private static Recipe tempRecipe = new Recipe(1, tempUser, Time.valueOf(LocalTime.now()),
                                        "This is a recipe", 10, 20);
    private static Favorite tempFavorite= new Favorite(1, tempUser, tempRecipe);

    private static String emptyReturnJSON = "[]";

    @Test
    public void testAddingToFavorites() throws Exception{
        //TESTING FOR ILLEGAL INPUT
        mockMvc.perform(MockMvcRequestBuilders.post("/favorite")
                .header("userID", -1)
                .header("recipeID",2)
        ).andExpect(MockMvcResultMatchers.status().is(400));

        mockMvc.perform(MockMvcRequestBuilders.post("/favorite")
                .header("userID", 2)
                .header("recipeID",-1)
        ).andExpect(MockMvcResultMatchers.status().is(400));

        when(mockedUserService.findById(1)).thenReturn(tempUser);
        when(mockedRecipeService.findById(1)).thenReturn(tempRecipe);
        when(mockedFavoriteService.create(tempFavorite)).thenReturn(tempFavorite);

        mockMvc.perform(MockMvcRequestBuilders.post("/favorite")
                .header("userID", 1)
                .header("recipeID",1)

        ).andExpect(MockMvcResultMatchers.status().is(201));
    }

    @Test
    public void testRemovingFromFavorites()throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.delete("/favorite")
                .header("userID", -1)
                .header("recipeID",2)
        ).andExpect(MockMvcResultMatchers.status().is(400));

        mockMvc.perform(MockMvcRequestBuilders.delete("/favorite")
                .header("userID", 2)
                .header("recipeID",-1)
        ).andExpect(MockMvcResultMatchers.status().is(400));



        when(mockedFavoriteService.removeFavorite(1,1)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/favorite")
                .header("userID", 1)
                .header("recipeID",1)
        ).andExpect(MockMvcResultMatchers.status().is(200));

        mockMvc.perform(MockMvcRequestBuilders.delete("/favorite")
                .header("userID", 2)
                .header("recipeID",2)
        ).andExpect(MockMvcResultMatchers.status().is(404));

    }

    @Test
    public void testGetUsersFavorites()throws Exception{

        List<FavoriteResponseDTO> tempList = new ArrayList<>();
        List<FavoriteResponseDTO> emptyList = new ArrayList<>();
        List<Favorite> tempFavoriteList = new ArrayList<>();

        Recipe forMocking = new Recipe();
        forMocking.setRecipeId(1);
        forMocking.setAuthor(tempUser);

        tempFavoriteList.add(tempFavorite);
        FavoriteResponseDTO tempDTO = new FavoriteResponseDTO(tempUser, forMocking);
        tempList.add(tempDTO);



        when(mockedRecipeService.findById(1)).thenReturn(forMocking);
        when(mockedFavoriteRepository.findAllByUserUserId(1)).thenReturn(tempFavoriteList);
        when(mockedFavoriteService.findAllWithID(1)).thenReturn(tempList);
        when(mockedFavoriteService.findAllWithID(2)).thenReturn(emptyList);

        mockMvc.perform(MockMvcRequestBuilders.get("/favorite")
                .header("userID", -1)
        ).andExpect(MockMvcResultMatchers.status().is(400));

        //Testing For User With Favorited Items
        mockMvc.perform(MockMvcRequestBuilders.get("/favorite")
                .header("userID", 1)
        ).andExpect(MockMvcResultMatchers.status().is(200));

        mockMvc.perform(MockMvcRequestBuilders.get("/favorite")
                .header("userID", 2)
        ).andExpect(MockMvcResultMatchers.status().is(406));

        //Testing For User who Doesn't Exist
        //mockMvc.perform(MockMvcRequestBuilders.get("/favorite")
        //        .header("userID", 2)
        //).andExpect(MockMvcResultMatchers.content().string(emptyReturnJSON));


    }
}
