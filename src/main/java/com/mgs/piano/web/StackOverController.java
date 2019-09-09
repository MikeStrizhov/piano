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

@Controller
public class StackOverController {

    @Autowired
    RestClientController restClientController;

    @GetMapping("/")
    public String root(Model model){
        return "index";
    }

    @GetMapping("/result")
    public String testRu1nList(@RequestParam(value = "query", defaultValue = "java") String searchString
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

    @RequestMapping("/error1")
    public void handleRequest() {
        throw new RuntimeException("test exception");
    }
}
