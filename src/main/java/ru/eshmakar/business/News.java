package ru.eshmakar.business;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eshmakar.business.domain.HotNews;
import ru.eshmakar.business.domain.LastNews;
import ru.eshmakar.business.domain.MainNews;
import ru.eshmakar.business.repo.HotNewsRepo;
import ru.eshmakar.business.repo.LastNewsRepo;
import ru.eshmakar.business.repo.MainNewsRepo;

import java.io.IOException;
import java.util.ListIterator;

@Component
public class News {
    String url = "https://m.business-gazeta.ru";
    String userAgent = "Mozilla/5.0 (Linux; Android 4.4.2; Nexus 4 Build/KOT49H) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.114 Mobile Safari/537.36";
    @Autowired
    private MainNewsRepo mainNewsRepo;
    @Autowired
    private HotNewsRepo hotNewsRepo;
    @Autowired
    private MainNews mainNews;
    @Autowired
    private LastNewsRepo lastNewsRepo;

    
    public void addMainNews() throws IOException {
        Document document = Jsoup.connect(url).userAgent(userAgent).get();
        String glavnayaTema = "h2.article-news__title";
        String comments = "div.article-news__comments";
        String linkGlavnaya = document.selectXpath("/html/body/div[1]/article/div/p/a").toString();

        mainNews.setTitle(document.selectFirst(glavnayaTema).text());
        mainNews.setComments(document.selectFirst(comments).text());
        mainNews.setLink(url + linkGlavnaya.substring(9, 24));

        mainNewsRepo.save(mainNews);
    }
    public void addHotNews() throws IOException {
        Document document = Jsoup.connect(url).userAgent(userAgent).get();
        String hotNewsTitle = "a.hot-news__title";
        String commentsHotNews = "a.hot-news__comments";
        int count = 0;

        ListIterator<Element> elementListIterator = document.select(hotNewsTitle).listIterator();
        ListIterator<Element> commentsIterator = document.select(commentsHotNews).listIterator();

        while (elementListIterator.hasNext() && commentsIterator.hasNext()) {
            HotNews hotNews = new HotNews();
            count++;
            String linkHotNews = document.selectXpath("/html/body/div[1]/section/ul[1]/li[" + count + "]/div/div/a[1]").toString();


            hotNews.setTitle(elementListIterator.next().text());
            hotNews.setComments(commentsIterator.next().text());
            hotNews.setLink(url+linkHotNews.substring(9, 21));

//
//            hotNews.();
////            mainNews.add(" - ");
//            mainNews.add();
////            mainNews.add(" - ");
//            mainNews.add();
//            mainNews.add();
////            mainNews.add("\n");
//

//            System.out.print(elementListIterator.next().text());
//            System.out.print(" - " + commentsIterator.next().text());
//            System.out.println(" - " + url + linkHotNews.substring(9, 21));

            hotNewsRepo.save(hotNews);
        }

    }

    public void addLastNews() throws IOException {
        Document document = Jsoup.connect(url).userAgent(userAgent).get();
        int count = 0;
        String time = "time.last-news__time";
        String title = "a.last-news__link";
        String comment = "a.last-news__comments";

        ListIterator<Element> times = document.select(time).listIterator();
        ListIterator<Element> titles = document.select(title).listIterator();
        ListIterator<Element> comments = document.select(comment).listIterator();

        while (times.hasNext() && titles.hasNext() && comments.hasNext()) {
            LastNews lastNews = new LastNews();
            count++;
            String links = document.selectXpath("/html/body/div[1]/section/ul[2]/li[" + count + "]/div/a[1]").toString();


            lastNews.setTime(times.next().text());
            lastNews.setTitle(titles.next().text());
            lastNews.setComments(comments.next().text());
            lastNews.setLink(url + links.substring(9, 21));
            lastNewsRepo.save(lastNews);

//
//            System.out.print(times.next().text() + " ");
//            System.out.print(links.next().text() + " - ");
//            System.out.print(comments.next().text() + " - ");
//            System.out.println(url + linkLastNews.substring(9, 21));
        }
        
    }
}
