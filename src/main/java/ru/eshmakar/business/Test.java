package ru.eshmakar.business;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws IOException {
        String url = "https://m.business-gazeta.ru";
        String userAgent = "Mozilla/5.0 (Linux; Android 4.4.2; Nexus 4 Build/KOT49H) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.114 Mobile Safari/537.36";

        Document document = Jsoup.connect(url).userAgent(userAgent).get();
        String selectPhoto = "img.hot-news__img";

        Elements select = document.select(selectPhoto);
        System.out.println(select.eq(0));

    }
}
