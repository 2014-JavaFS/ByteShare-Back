package com.revature.byteshare.tags;

import com.revature.byteshare.recipe.Recipe;
import com.revature.byteshare.recipe.RecipeService;
import com.revature.byteshare.tags.dtos.TagDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {TagController.class})
@AutoConfigureMockMvc
@EnableWebMvc
public class TagControllerIntegrationTestSuite {

    @MockBean
    private TagRepository tagRepository;
    @MockBean
    private TagService tagService;
    @MockBean
    private RecipeService recipeService;
    //@MockBean
    //private RecipeRepository recipeRepository;

    @Autowired
    private TagController tagController;
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private static Recipe defaultRecipe = new Recipe();

    private static Tag validTag0 = new Tag(1, defaultRecipe,"dummyTagOne");
    private static TagDTO validTagDTO = new TagDTO(1,"dummyTagOne");
    private static String tagJson = "{\"tag_id\":1,\"recipe\":{\"id\":0},\"tag_name\":\"dummyTagOne\"}";
    private static String tagDTOJson = "{\"recipe_id\":1,\"tag_name\":\"dummyTagOne\"}";


    @Test
    public void testPostTag() throws Exception {
        when(tagService.create(any(Tag.class))).thenReturn(validTag0);
        when(recipeService.findById(validTagDTO.getRecipe_id())).thenReturn(defaultRecipe);

        mockMvc.perform(MockMvcRequestBuilders.post("/tags")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tagDTOJson))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string(tagJson));
    }

    @Test
    public void testGetFindAllTags() throws Exception {
        when(tagService.findAllTags()).thenReturn(List.of(validTag0));
        String expectedResult ="["+tagJson+"]";

        mockMvc.perform(MockMvcRequestBuilders.get("/tags"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string(expectedResult));
    }
    //TODO will finish this test when I get back
    /*
    @Test
    public void testFindAllTagsByRecipeId()throws Exception{
        when(tagService.create(validTag0)).thenReturn(validTag0);
        when(tagService.create(validTag1)).thenReturn(validTag1);
        when(tagService.create(validTag2)).thenReturn(validTag2);
        when(tagService.create(validTag3)).thenReturn(validTag3);

        when(tagService.findAllTagNamesByRecipeID(1)).thenReturn({validTag0, validTag1});
        String expectedResult ="["+tagJson+"]";

        mockMvc.perform(MockMvcRequestBuilders.get("/tags"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string(expectedResult));

    }*/

    @Test
    public void testUpdateTag() throws Exception {
        when(tagService.update(validTag0)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.put("/tags")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tagJson))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string(tagJson));
    }

    @Test
    public void testDeleteTag() throws Exception {
        when(tagService.delete(1)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/tags/1"))
                .andExpect(MockMvcResultMatchers.status().is(204));
    }
}
