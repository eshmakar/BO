package ru.eshmakar.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.eshmakar.business.News;
import ru.eshmakar.business.domain.ContentNews;
import ru.eshmakar.business.domain.HotNews;
import ru.eshmakar.business.domain.LastNews;
import ru.eshmakar.business.domain.MainNews;
import ru.eshmakar.business.repo.HotNewsRepo;
import ru.eshmakar.business.repo.LastNewsRepo;
import ru.eshmakar.business.repo.MainNewsRepo;

import java.io.IOException;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    public News news;

    @Autowired
    private MainNewsRepo mainNewsRepo;

    @Autowired
    private HotNewsRepo hotNewsRepo;

    @Autowired
    private LastNewsRepo lastNewsRepo;

    @Autowired
    private ContentNews contentNews;


    @GetMapping("/")
    public String getMainPage(Model model) {
        mainNewsRepo.deleteAll();
        hotNewsRepo.deleteAll();
        lastNewsRepo.deleteAll();

        try {
            news.addMainNews();
            news.addHotNews();
            news.addLastNews();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Iterable<MainNews> mainNews;
        mainNews = mainNewsRepo.findAll();

        Iterable<HotNews> hotNews;
        hotNews = hotNewsRepo.findAll();

        Iterable<LastNews> lastNews;
        lastNews = lastNewsRepo.findAll();

        model.addAttribute("mainNews", mainNews);
        model.addAttribute("hotNews", hotNews);
        model.addAttribute("lastNews", lastNews);
        return "index";
    }

 /*   @GetMapping("{numbers}")
    public String getContentPage(Model model, @PathVariable String numbers) throws IOException {

        System.err.println("This is number: " + numbers);

        contentNews.setZagolovok(null);
        contentNews.setTelo(null);
        contentNews.setPhoto(null);
        contentNews.setId(null);

        try {
            news.getContent(numbers);
        } catch (Exception e) {
            System.err.println("Неправильный номер: " + numbers);
//            e.printStackTrace();
        }

        news.getComments(numbers);//добавил все комменты на файл

        model.addAttribute("content", contentNews);

        return "content";
    }*/


       @GetMapping(value = "/{numbers}")
    public String getContentPage(Model model, @PathVariable String numbers) throws IOException {

        System.err.println("This is number: " + numbers);

        contentNews.setZagolovok(null);
        contentNews.setTelo(null);
        contentNews.setCommentsCount(null);
        contentNews.setId(null);

        try {
            news.getContent(numbers);
        } catch (Exception e) {
            System.err.println("Неправильный номер: " + numbers);
//            e.printStackTrace();
        }

        news.getComments(numbers);//добавил все комменты на файл

        model.addAttribute("content", contentNews);
        model.addAttribute("number", numbers);

        return "content";
    }





    @GetMapping("top")
    public String populars(Model model) {
        List<LastNews> lasts = lastNewsRepo.getTop30ByOrderByCommentsDesc();
        model.addAttribute("tops", lasts);
        return "top";
    }

    @GetMapping("comments")
    public String getHtml(){
        return "comments";
    }
}
