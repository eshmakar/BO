import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Arrays;

public class GettingAndViewContent {
    public static void main(String[] args) throws IOException {
        String ur = "https://m.business-gazeta.ru/news/530491";
        Document doc = Jsoup.connect(ur).get();
        StringBuilder sb = new StringBuilder();
        String bodyContent = doc.select("div.articleBody").text();
        int length = bodyContent.length();

        int start = 0;
        int stop = 130;

        String[] split = bodyContent.split(" ");
        Arrays.stream(split).map(e-> e + " ").forEachOrdered(System.out::print);



  /*      for (; stop <= length; stop += 130) {
            String textToAddSb = (bodyContent.substring(start, stop)).strip();
//            String regex = "[\\.,?!;:-]";
            String regex = ".";
            sb.append("\n");
            if (textToAddSb.startsWith(regex)) {
                textToAddSb = textToAddSb.substring(2, textToAddSb.length());
                sb.append(regex);
            }
            sb.append(textToAddSb);
            start = stop;
        }*/


        System.out.println(sb);

    }
}
