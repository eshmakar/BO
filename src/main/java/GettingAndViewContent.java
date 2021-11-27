import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class GettingAndViewContent {
    public static void main(String[] args) throws IOException {
        String ur = "https://m.business-gazeta.ru/news/531026";
        Document doc = Jsoup.connect(ur).get();
        String photo = null;

        String regex = "(.*<img src=\")(https://.*\\.jpg)(\" .*)";
        String replaceTo = "$2";

        String toPass1 = "Подписывайтесь и читайте";
        String toPass2 = "Регистрируясь, вы соглашаетесь";

        String zagol = "h1.article__h1";
        System.err.println(doc.select(zagol).text());


        Elements ps = doc.select("p");
        for (Element raw : ps) {
            String text = raw.text();
            if (text.contains("Фото: ")) {
                photo = raw.toString().replaceFirst(regex, replaceTo);
            } else if (text.startsWith(toPass1) || text.startsWith(toPass2)) continue;
            else System.out.println(text);
        }
    }
}
