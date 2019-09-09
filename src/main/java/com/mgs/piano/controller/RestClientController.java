package com.mgs.piano.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgs.piano.model.SearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Рест клиент, получает данные от сервера и преобразует их во внутренний формат
 */
@Component
public class RestClientController {

    private static final Logger logger = LoggerFactory.getLogger(RestClientController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.so.rest.path}")
    private String uriApi;

    /**Получаем данные от сервера и преобразуем их во внутренний формат
     * @param searchString строка для поиска
     * @param pageId номер страницы, должен быть больше нуля
     * @return ответ сервера
     */
    public SearchResponse getPosts(String searchString, long pageId) {
        //ask remute api for data
        SearchResponse result = new SearchResponse();
        Map<String, String> params = new HashMap<>();
        params.put("intitle", searchString);
        params.put("page", Long.toString(pageId));

        String restStringResponse = restTemplate.getForObject(uriApi, String.class, params);

        //transform response to our model
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            result = mapper.readValue(restStringResponse, SearchResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**Метод введен для удобства покрытия тестами
     * @param uriApi ссылка на АПИ
     */
    public void setUriApi(String uriApi) {
        this.uriApi = uriApi;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
