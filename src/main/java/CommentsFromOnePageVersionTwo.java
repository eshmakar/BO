import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class CommentsFromOnePageVersionTwo {
        private static String url = "https://m.business-gazeta.ru/article/534342";

    private static Document document;

    public static void main(String[] args) throws IOException {
        document = Jsoup.connect(url).get();

        String vygodnyiKurs = "<header class=\"header\"> <div class=\"currency\"> <div class=\"currency__logo\"> <a class=\"currency__logo-link\" href=\"https://www\\.bankofkazan\\.ru/currency/\\?utm_source=business-online&amp;utm_medium=cpc&amp;utm_campaign=fix_cur\" target=\"_blank\" rel=\"noopener noreferrer\"> выгодный курс </a> </div> <div class=\"currency__exchange\"> <ul class=\"exchange\"> <li class=\"exchange__item\"> <span class=\"exchange__currency\">\\$</span> <span class=\"exchange__course\">\\d+\\.\\d+</span> </li> <li class=\"exchange__item\"> <span class=\"exchange__currency\">€</span> <span class=\"exchange__course\">\\d+\\.\\d+</span> </li> <li class=\"exchange__item\"> <span class=\"exchange__currency\"><span class=\"icon-oil\"><!-- --></span></span> <span class=\"exchange__course\">\\d+\\.\\d+</span> </li> </ul> </div> </div> </header>";


        String rawContent = document.toString()
                .replaceAll("\n", "")
                .replaceAll("(  *)", " ")
                .replaceAll(vygodnyiKurs, "")

                ;

        System.out.println(rawContent);




//        String comments = "div.comments-comment__header";
//        Elements select = document.select(comments);//выбор всех комментов
//        String glavnyiComment = "";
//        String findThis = "([А-Яа-яA-Za-z0-9]* )(\\d+ .*ря* \\d+:\\d+ )(.*)";
//        String replaceTo = "$3";
//
//
//        String findAddToBookmark = "<div class=\"popover__wrap\" alt=\"Добавить материал в закладки\"> <div class=\"popover__text\"> <div> Сохраняйте новости, статьи, комментарии чтобы прочитать их позже </div> </div> <div class=\"popover__buttons\"> <button class=\"button\" data-modal=\"#auth\" data-toggle=\"modal\">Войти</button> </div> </div>";
//        String findShowAllComments = "<div class=\"show_full\" title=\"Посмотреть весь комментарий\"> Читать далее </div><a href=\"#\" class=\"comments-comment__answer\" data-article-id=\"\\d+\" data-comment-id=\"\\d+\">Ответить</a>";
//        String findSsylkaNaComment = "<div class=\"comments-comment__etc\"> <div class=\"comments-comment__dots\"> <span class=\"icon-dots js-comment-dots\"></span> <div class=\"comments-comment__popover\"> <ul class=\"comments-comment__popover-list\"> <li class=\"comments-comment__popover-item\"><span data-clipboard-text=\"https://m\\.business-gazeta\\.ru/[a-z]*/\\d+#comment\\d+\" class=\"js-copy-comment-link comments-comment__to-comment\">ссылка на комментарий</span></li> </ul> </div> </div> </div>";
//        String znakZometki = "<div class=\"comments-comment__bookmarks\"> <div class=\"popover popover_favorite\"> <a class=\"icon-favorite anonymous \" href=\"#comment\\d+\" data-type=\"comment\" data-id=\"\\d+\"> <svg width=\"9\" height=\"10\" viewbox=\"0 0 9 10\" fill=\"none\" xmlns=\"http://www\\.w3\\.org/2000/svg\"> <path d=\"M0\\.5 0\\.5V9\\.11L4\\.24 6\\.83L4\\.5 6\\.67L4\\.76 6\\.83L8\\.5 9\\.11V0\\.5H0\\.5Z\" stroke=\"#[A-Z0-9]*\" /> </svg></a>  </div> </div>";
//
//        System.out.println(select.toString()
//                .replaceAll("\n","")
//                .replaceAll("(  *)"," ")
//                .replaceAll(findAddToBookmark, "")
//                .replaceAll(findShowAllComments, "")
//                .replaceAll(findSsylkaNaComment,"")
//                .replaceAll(znakZometki, "")
//        );


    }
}
