package ru.eshmakar.business;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eshmakar.business.domain.ContentNews;
import ru.eshmakar.business.domain.HotNews;
import ru.eshmakar.business.domain.LastNews;
import ru.eshmakar.business.domain.MainNews;
import ru.eshmakar.business.repo.HotNewsRepo;
import ru.eshmakar.business.repo.LastNewsRepo;
import ru.eshmakar.business.repo.MainNewsRepo;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

@Component
public class News {
    String url = "https://m.business-gazeta.ru";
    String userAgent = "Mozilla/5.0 (Linux; Android 4.4.2; Nexus 4 Build/KOT49H) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.114 Mobile Safari/537.36";
    String regexForNumber = "(.*\\/)(\\d+)";
    String regexFindPhoto = "(.*)(https://.*\\.jpg)(.*)";
    String replaceTo = "$2";

    @Autowired
    private MainNewsRepo mainNewsRepo;
    @Autowired
    private HotNewsRepo hotNewsRepo;
    @Autowired
    private MainNews mainNews;
    @Autowired
    private LastNewsRepo lastNewsRepo;
    @Autowired
    private ContentNews contentNews;


    public void addMainNews() throws IOException {
        Document document = Jsoup.connect(url).userAgent(userAgent).get();
        String glavnayaTema = "h2.article-news__title";
        String comments = "div.article-news__comments";
        String linkGlavnaya = document.selectXpath("/html/body/div[1]/article/div/p/a").toString();
        String selectPhoto = "a.article-news__image";

        mainNews.setPhoto(document.selectFirst(selectPhoto).toString().replaceFirst(regexFindPhoto, replaceTo));
        mainNews.setTitle(document.selectFirst(glavnayaTema).text());
        mainNews.setComments(document.selectFirst(comments).text());
        mainNews.setLink(url + linkGlavnaya.substring(9, 24));
        mainNews.setNumbersOfLinks(mainNews.getLink().replaceFirst(regexForNumber, replaceTo));
        mainNewsRepo.save(mainNews);
    }


    public void addHotNews() throws IOException {
        Document document = Jsoup.connect(url).userAgent(userAgent).get();
        String hotNewsTitle = "a.hot-news__title";
        String commentsHotNews = "a.hot-news__comments";
        String selectPhoto = "img.hot-news__img";

        int count = 0;

        ListIterator<Element> elementListIterator = document.select(hotNewsTitle).listIterator();
        ListIterator<Element> commentsIterator = document.select(commentsHotNews).listIterator();
        ListIterator<Element> photosIterator = document.select(selectPhoto).listIterator();

        while (elementListIterator.hasNext() && commentsIterator.hasNext() && photosIterator.hasNext()) {
            HotNews hotNews = new HotNews();
            count++;
            String linkHotNews = document.selectXpath("/html/body/div[1]/section/ul[1]/li[" + count + "]/div/div/a[1]").toString();


            hotNews.setPhoto(photosIterator.next().toString().replaceFirst(regexFindPhoto, replaceTo));

            hotNews.setTitle(elementListIterator.next().text());
            hotNews.setComments(commentsIterator.next().text());
            hotNews.setLink(url + linkHotNews.substring(9, 21));
            hotNews.setNumbersOfLinks(hotNews.getLink().replaceFirst(regexForNumber, replaceTo));

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
            lastNews.setNumbersOfLinks(lastNews.getLink().replaceFirst(regexForNumber, replaceTo));
            lastNewsRepo.save(lastNews);
        }
    }

    public void getContent(String u) {
        String urlContent = "https://m.business-gazeta.ru/news/" + u;
        List<String> telo = new LinkedList<>();
        String zagol = "h1.article__h1";

        Document doc = null;
        try {
            doc = Jsoup.connect(urlContent).get();
        } catch (IOException ignored) {
        }
        contentNews.setZagolovok(doc.select(zagol).text());

        String toPass1 = "Подписывайтесь и читайте";
        String toPass2 = "Регистрируясь, вы соглашаетесь";


        Elements ps = doc.select("p");
        for (Element raw : ps) {
            String text = raw.text();
            if (text.contains("Фото:")) {
                contentNews.setPhoto(raw.toString().replaceFirst(regexFindPhoto, replaceTo));
            } else if (text.startsWith(toPass1) || text.startsWith(toPass2)) continue;
            else telo.add(text);
        }
        contentNews.setTelo(telo);
    }

    public void addTopNewsByComments(){

    }
}
