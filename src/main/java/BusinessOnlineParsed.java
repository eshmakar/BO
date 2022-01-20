import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ListIterator;

@Component
public class BusinessOnlineParsed {
    private static final String url = "https://m.business-gazeta.ru";
    private static final String userAgent = "Mozilla/5.0 (Linux; Android 4.4.2; Nexus 4 Build/KOT49H) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.114 Mobile Safari/537.36";

    public static Document document;
    {
        try {
            document = Jsoup.connect(url).userAgent(userAgent).get();
        } catch (IOException ignored) {
        }
    }


    public static void main(String[] args){
//        mainAndHotNews(document);
        lastNews(document);
    }

    private static void lastNews(Document document) {
        int count = 0;
        String timeLastNews = "time.last-news__time";
        String lastNews = "a.last-news__link";
        String lastNewsCommentsCount = "a.last-news__comments";

        ListIterator<Element> times = document.select(timeLastNews).listIterator();
        ListIterator<Element> lastNewsIterator = document.select(lastNews).listIterator();
        ListIterator<Element> lastComments = document.select(lastNewsCommentsCount).listIterator();

        while (times.hasNext() && lastNewsIterator.hasNext() && lastComments.hasNext()) {
            count++;
            String linkLastNews = document.selectXpath("/html/body/div[1]/section/ul[2]/li[" + count + "]/div/a[1]").toString();
//            System.out.print(times.next().text() + " ");
//            System.out.print(lastNewsIterator.next().text() + " - ");
//            System.out.print(lastComments.next().text() + " - ");
//            System.out.println(url + linkLastNews.substring(9, 21));
        }
    }

    public StringBuilder mainAndHotNews() {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        String glavnayaTema = "h2.article-news__title";
        String comments = "div.article-news__comments";
        String linkGlavnaya = document.selectXpath("/html/body/div[1]/article/div/p/a").toString();


//        System.out.println("Главные темы");


        sb.append(document.selectFirst(glavnayaTema).text())
                .append(" - ")
                .append(document.selectFirst(comments).text())
                .append(" - ")
                .append(url + linkGlavnaya.substring(9, 24))
                .append("\n");


//        System.out.println("Главные темы");
//        System.out.print(document.selectFirst(glavnayaTema).text());
//        System.out.print(" - " + document.selectFirst(comments).text());
//        System.out.println(" - " + url + linkGlavnaya.substring(9, 24)); //ссылка на статью


        String hotNews = "a.hot-news__title";
        String commentsHotNews = "a.hot-news__comments";

        ListIterator<Element> elementListIterator = document.select(hotNews).listIterator();
        ListIterator<Element> commentsIterator = document.select(commentsHotNews).listIterator();

        while (elementListIterator.hasNext() && commentsIterator.hasNext()) {
            count++;
            String linkHotNews = document.selectXpath("/html/body/div[1]/section/ul[1]/li[" + count + "]/div/div/a[1]").toString();
            sb.append(elementListIterator.next().text());
            sb.append(" - ").append(commentsIterator.next().text());
            sb.append(" - " + url).append(linkHotNews.substring(9, 21)).append("\n");


//            System.out.print(elementListIterator.next().text());
//            System.out.print(" - " + commentsIterator.next().text());
//            System.out.println(" - " + url + linkHotNews.substring(9, 21));
        }
        sb.append("\n");
        return sb;
    }
}
