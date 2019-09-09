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

    public SearchResponse getPosts(String searchString, long pageId) {
        SearchResponse result = new SearchResponse();
        Map<String, String> params = new HashMap<>();
        params.put("intitle", searchString);
        params.put("page", Long.toString(pageId));

        String restStringResponse = restTemplate.getForObject(uriApi, String.class, params);
        logger.info(restStringResponse);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            result = mapper.readValue(restStringResponse, SearchResponse.class);
            logger.info(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void setUriApi(String uriApi) {
        this.uriApi = uriApi;
    }
}
