package com.mgs.piano.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mgs.piano.model.jackson.CustomQuestionListDeserializer;

import java.util.List;
import java.util.Objects;


public class SearchResponse {
    private List<Question> questionsList;

    public SearchResponse(){}

    @JsonProperty("items")
    @JsonDeserialize(using = CustomQuestionListDeserializer.class)
    public List<Question> getQuestions() {
        return questionsList;
    }

    public void setQuestions(List<Question> questionsList) {
        this.questionsList = questionsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchResponse that = (SearchResponse) o;
        return Objects.equals(questionsList, that.questionsList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(questionsList);
    }

    @Override
    public String toString() {
        return "SearchResponse{" +
                "questionsList=" + questionsList +
                '}';
    }
}