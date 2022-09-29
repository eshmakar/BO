package ru.eshmakar.business.domain;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "main_news")
public class MainNews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String comments;
    private String link;
    private String numbersOfLinks;
    private String photo;

    public MainNews() {
    }

    public MainNews(String title, String comments, String link, String numbersOfLinks, String photo) {
        this.title = title;
        this.comments = comments;
        this.link = link;
        this.numbersOfLinks = numbersOfLinks;
        this.photo = photo;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
