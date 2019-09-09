package com.mgs.piano.web;

import com.mgs.piano.controller.RestClientController;
import com.mgs.piano.model.Question;
import com.mgs.piano.model.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * Веб контроллер
 */
@Controller
public class StackOverController {

    @Autowired
    RestClientController restClientController;

    /**Маппинг для статической страницы с текстом задания
     * @param model модель MVC паттерна
     * @return имя вида из MVC паттерна для отображения модели
     */
    @GetMapping("/")
    public String root(Model model){
        return "index";
    }

    /** обработка маппинга для основной страницы ч результатами поиска
     * @param searchString поисковая строка
     * @param pageId номер страницы в результатах поиска
     * @param model модель MVC паттерна
     * @return имя вида из MVC паттерна для отображения модели
     */
    @GetMapping("/result")
    public String resultsList(@RequestParam(value = "query", defaultValue = "java") String searchString
            , @RequestParam(value = "page", defaultValue = "1")
              @Min(value = 1L, message = "The value must be positive, not zero")long pageId
            , Model model){

        SearchResponse searchResponse = restClientController.getPosts(searchString, pageId);

        model.addAttribute("questionList", searchResponse.getQuestions());
        model.addAttribute("have_next_page", searchResponse.isHasMore());
        model.addAttribute("searchString", searchString);
        model.addAttribute("curentPage", pageId);

        return "result";
    }
}
