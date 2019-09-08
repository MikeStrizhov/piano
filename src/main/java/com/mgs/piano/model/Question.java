package com.mgs.piano.model;

import java.util.Objects;

public class Question {
    private String date;
    private String title;
    private String owner;
    private boolean is_answered;
    private String link;

    public Question(){}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean isIs_answered() {
        return is_answered;
    }

    public void setIs_answered(boolean is_answered) {
        this.is_answered = is_answered;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Question{" +
                "date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", owner='" + owner + '\'' +
                ", is_answered=" + is_answered +
                ", link='" + link + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return is_answered == question.is_answered &&
                Objects.equals(date, question.date) &&
                Objects.equals(title, question.title) &&
                Objects.equals(owner, question.owner) &&
                Objects.equals(link, question.link);
    }

    @Override
    public int hashCode() {

        return Objects.hash(date, title, owner, is_answered, link);
    }
}
