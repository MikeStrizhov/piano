package com.mgs.piano.controller;

import com.mgs.piano.controller.utils.JsonString;
import com.mgs.piano.model.Question;
import com.mgs.piano.model.SearchResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RestClientControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(RestClientControllerTest.class);

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private RestClientController restClientController = new RestClientController();

    /**
     * Проверяем работу rest клиента
     */
    @Test
    public void getPosts() {

        //Setup test params
        String searchString = "java";

        Map<String, String> params = new HashMap<>();
        params.put("intitle", searchString);
        params.put("page", "1");

        String uri = "=http://api.stackexchange.com/2.2/search?page={page}&order=desc&sort=votes&intitle={intitle}&site=stackoverflow";
        restClientController.setUriApi(uri);

        //prepare mock
        Mockito
                .when(restTemplate.getForObject(uri, String.class, params))
                .thenReturn(JsonString.JSON);


        SearchResponse result = restClientController.getPosts(searchString,1);

        Assert.assertEquals("user4315", result.getQuestions().get(0).getOwner());
    }
}