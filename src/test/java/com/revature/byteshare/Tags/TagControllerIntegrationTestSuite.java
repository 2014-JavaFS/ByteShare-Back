package com.revature.byteshare.Tags;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class TagControllerIntegrationTestSuite {

    @MockBean
    private TagRepository tagRepository;
    @MockBean
    private TagService tagService;
    @MockBean
    private TagController tagController;

    @Autowired
    private MockMvc mockMvc;

    private static Tag validTag0 = new Tag(1,1,"dummyTagOne");
    private static Tag validTag1 = new Tag(2,1,"dummyTagTwo");
    private static Tag validTag2 = new Tag(3,2,"dummyTagTwo");
    private static Tag validTag3 = new Tag(4,2,"dummyTagThree");

    private static String tagJson = "{\"tag_id\":1, \"recipe_id\":1,\"tag_name\":\"dummyTagOne\"}";

    //TODO ngl, this is the first test I have ever written, hope it actually works when i get to testing it
    // if you see this and have suggestions please lmk -Ethan
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
    public void testCreateTag() throws Exception {
        when(tagService.create(validTag0)).thenReturn(validTag0);

        mockMvc.perform(MockMvcRequestBuilders.post("/tags")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tagJson))
                .andExpect(MockMvcResultMatchers.status().is(200)) //TODO need to make sure this reflects the proper status
                .andExpect(MockMvcResultMatchers.content().string(tagJson));
    }

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
