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
import java.util.Objects;

@Component
public class News {
    String url = "https://m.business-gazeta.ru";
    String userAgent = "Mozilla/5.0 (Linux; Android 4.4.2; Nexus 4 Build/KOT49H) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.114 Mobile Safari/537.36";
    String regexForNumber = "(.*\\/)(\\d+)";
    String regexFindPhoto = "(.*)(https://.*\\.jp.?g)(.*)";
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

        mainNews.setPhoto(Objects.requireNonNull(document.selectFirst(selectPhoto)).toString().replaceFirst(regexFindPhoto, replaceTo));
        mainNews.setTitle(Objects.requireNonNull(document.selectFirst(glavnayaTema)).text());
        mainNews.setComments(Objects.requireNonNull(document.selectFirst(comments)).text().substring(12));
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
            contentNews.setZagolovok(doc.select(zagol).text());
        } catch (IOException ignored) {
        }

        String toPass1 = "Подписывайтесь и читайте";
        String toPass2 = "Регистрируясь, вы соглашаетесь";

       /* int count = 0;
        Elements ps = doc.select("p");
        for (Element raw : ps) {
        count++;
            String text = raw.text();
            System.out.println(count);
            System.err.println(raw);
            System.out.println();
            if (raw.toString().contains("jpg") || raw.toString().contains("jpeg")) {
                contentNews.setPhoto(raw.toString().replaceFirst(regexFindPhoto, replaceTo));
            }

            if (text.startsWith(toPass1) || text.startsWith(toPass2)) continue;
            else telo.add(text);
        }
        contentNews.setTelo(telo);*/

        if (doc != null) {
            for (Element element : doc.select("p")) {
                if (element != null) {
                    if (element.toString().contains("jpg") || element.toString().contains("jpeg"))
                        telo.add(element.toString().replaceAll(regexFindPhoto, replaceTo));

                    String text2 = element.text();
                    if (text2.startsWith(toPass1) || text2.startsWith(toPass2)) continue;

                    telo.add(text2);
                }
            }
        }
        contentNews.setTelo(telo);
    }

    public void addTopNewsByComments() {

    }
}
