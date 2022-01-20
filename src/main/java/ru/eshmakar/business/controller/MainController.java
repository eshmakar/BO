package ru.eshmakar.business.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
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

@EnableScheduling
@Controller
public class MainController {
    private static long countOfReloadPages;

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

    @Scheduled(fixedDelay=60_000*5) //5 мин
    public void doSomething() {
        if (mainNewsRepo != null) {
            mainNewsRepo.deleteAll();
            hotNewsRepo.deleteAll();
            lastNewsRepo.deleteAll();
        }
        try {
            news.addMainNews();
            news.addHotNews();
            news.addLastNews();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/")
    public String getMainPage(Model model) {
        countOfReloadPages++;
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

    @GetMapping(value = "/{numbers}")
    public String getContentPage(Model model, @PathVariable String numbers) throws IOException {
        if (!numbers.equals("favicon.ico")) {
            contentNews.setZagolovok(null);
            contentNews.setTelo(null);
            contentNews.setCommentsCount(null);
            contentNews.setId(null);

            try {
                news.getContent(numbers);
            } catch (Exception e) {
                System.err.println("Неправильный номер: " + numbers);
            }
            news.getComments(numbers);

            model.addAttribute("content", contentNews);
            model.addAttribute("number", numbers);
        }
        return "content";

    }


    @GetMapping("top")
    public String populars(Model model) {
        List<LastNews> lasts = lastNewsRepo.getTop30ByOrderByCommentsDesc();
        model.addAttribute("tops", lasts);
        return "top";
    }

    @GetMapping("comments")
    public String getHtml() {
        return "comments";
    }

    @GetMapping("info")
    public String info(Model model){
        model.addAttribute("count", countOfReloadPages);
        return "info";
    }
}
