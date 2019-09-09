package com.mgs.piano.web;

import com.mgs.piano.PianoApplication;
import com.mgs.piano.controller.RestClientController;
import com.mgs.piano.model.Question;
import com.mgs.piano.model.SearchResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = StackOverController.class)
@ContextConfiguration(classes={PianoApplication.class})
public class StackOverControllerTest {

    @MockBean
    RestClientController restClientControllerMoked;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void verifiesHomePageLoads() throws Exception {
        //simple check
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void resultsList() throws Exception{

        //prepare mock
        SearchResponse searchResponse = new SearchResponse();
        List<Question> questionList = new ArrayList<>();
        Question question = new Question();
        question.setTitle("1234567890");
        questionList.add(question);
        searchResponse.setQuestions(questionList);

        given(restClientControllerMoked.getPosts("java",1)).willReturn(searchResponse);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/result"))
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(MockMvcResultMatchers.view().name("result"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //check if result contain value from mocked service
        String stringResult = result.getResponse().getContentAsString();
        Assert.assertTrue(stringResult.contains(question.getTitle()));
    }
}