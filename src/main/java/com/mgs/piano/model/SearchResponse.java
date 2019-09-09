package com.mgs.piano.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mgs.piano.model.jackson.CustomQuestionListDeserializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Класс модели данных, содержащий в себе ответ сервера
 */
public class SearchResponse {

    //Список найденных ответов
    private List<Question> questionsList = new ArrayList<>();

    //поле ответа, вероятно означающее, что имеется следующая страница поиска при пагинации
    @JsonProperty("has_more")
    private boolean hasMore;

    public SearchResponse(){}

    @JsonProperty("items")
    @JsonDeserialize(using = CustomQuestionListDeserializer.class)
    public List<Question> getQuestions() {
        return questionsList;
    }


    public void setQuestions(List<Question> questionsList) {
        this.questionsList = questionsList;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
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
                "hasMore=" + hasMore +
                ", questionsList=" + questionsList +
                '}';
    }
}