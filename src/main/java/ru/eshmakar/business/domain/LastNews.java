package ru.eshmakar.business.domain;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "last_news")
public class LastNews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String time;
    private String title;
    private Integer comments;
    private String link;
    private String numbersOfLinks;


    public LastNews() {
    }

    public LastNews(String time, String title, Integer comments, String link, String numbersOfLinks) {
        this.time = time;
        this.title = title;
        this.comments = comments;
        this.link = link;
        this.numbersOfLinks = numbersOfLinks;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNumbersOfLinks() {
        return numbersOfLinks;
    }

    public void setNumbersOfLinks(String numbersOfLinks) {
        this.numbersOfLinks = numbersOfLinks;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }
}
