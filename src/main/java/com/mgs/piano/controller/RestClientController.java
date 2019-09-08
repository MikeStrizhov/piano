package com.mgs.piano.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgs.piano.model.Question;
import com.mgs.piano.model.SearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RestClientController {

    private static final Logger logger = LoggerFactory.getLogger(RestClientController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.so.rest.path}")
    private String uriApi;

    public List<Question> getPosts(String searchString) {
        List<Question> result = new ArrayList<>();
        Map<String, String> params = new HashMap<>();
        params.put("intitle", searchString);

        String restStringResponse = restTemplate.getForObject(uriApi, String.class, params);
        logger.info(restStringResponse);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            SearchResponse searchResponse = mapper.readValue(restStringResponse, SearchResponse.class);
            result = searchResponse.getQuestions();
            logger.info(searchResponse.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getUriApi() {
        return uriApi;
    }

    public void setUriApi(String uriApi) {
        this.uriApi = uriApi;
    }
}
