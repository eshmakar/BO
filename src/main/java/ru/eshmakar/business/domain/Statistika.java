package ru.eshmakar.business.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
@Component
@Entity
@Table
public class Statistika {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String title;

    @Value("hi")
    private String link;
    private int counterOfSeen = 1;

    public Statistika() {
    }

    public Statistika(Long id, Date date, String title, String link, int counterOfSeen) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.link = link;
        this.counterOfSeen = counterOfSeen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public int getCounterOfSeen() {
        return counterOfSeen;
    }

    public void setCounterOfSeen(int counterOfSeen) {
        this.counterOfSeen = counterOfSeen;
    }

    @Override
    public String toString() {
        return "Statistika{" +
                "id=" + id +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", counterOfSeen=" + counterOfSeen +
                '}';
    }
}
