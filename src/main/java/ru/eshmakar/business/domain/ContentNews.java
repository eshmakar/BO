package ru.eshmakar.business.domain;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
@Entity
@Table(name = "content")
public class ContentNews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String zagolovok;
    private String photo;

    @ElementCollection
    private List<String> telo;

    public ContentNews() {
    }

    public ContentNews(String zagolovok, String photo, List<String> telo) {
        this.zagolovok = zagolovok;
        this.photo = photo;
        this.telo = telo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZagolovok() {
        return zagolovok;
    }

    public void setZagolovok(String zagolovok) {
        this.zagolovok = zagolovok;
    }

    public List<String> getTelo() {
        return telo;
    }

    public void setTelo(List<String> telo) {
        this.telo = telo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
