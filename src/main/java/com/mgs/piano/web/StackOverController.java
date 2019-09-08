package com.mgs.piano.web;

import com.mgs.piano.controller.RestClientController;
import com.mgs.piano.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            , @RequestParam(value = "page", defaultValue = "0") long pageId, Model model){
//        String searchString = "java";
        model.addAttribute("searchString", searchString);
        List<Question> questionList = restClientController.getPosts(searchString);
        model.addAttribute("questionList", questionList);
        return "result";
    }

}
