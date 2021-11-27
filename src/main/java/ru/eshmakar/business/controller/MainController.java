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
import ru.eshmakar.business.repo.ContentNewsRepo;
import ru.eshmakar.business.repo.HotNewsRepo;
import ru.eshmakar.business.repo.LastNewsRepo;
import ru.eshmakar.business.repo.MainNewsRepo;

import java.io.IOException;

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
    public String getMainPage(Model model) throws IOException {
        mainNewsRepo.deleteAll();
        hotNewsRepo.deleteAll();
        lastNewsRepo.deleteAll();

        news.addMainNews();
        news.addHotNews();
        news.addLastNews();

        Iterable<MainNews> mainNews;
        mainNews = mainNewsRepo.findAll();

        Iterable<HotNews> hotNews;
        hotNews=hotNewsRepo.findAll();

        Iterable<LastNews> lastNews;
        lastNews = lastNewsRepo.findAll();

        model.addAttribute("mainNews", mainNews);
        model.addAttribute("hotNews", hotNews);
        model.addAttribute("lastNews", lastNews);
        return "index";
    }

  /*  @GetMapping("/{url}")
    public String getContentPage(Model model, @PathVariable String url) throws IOException {
        news.getContent(url);
        model.addAttribute("contentFromUrl", contentNewsRepo.findByUrl(url));
        return "content";
    }*/

    @GetMapping("/{u}")
    public String getContentPage(Model model, @PathVariable String u) throws IOException {
        System.out.println(u);
        news.getContent(u);
        System.out.println(contentNews.getId());

        model.addAttribute("contentFromUrl", contentNews);
        model.addAttribute("telo", contentNews.getTelo());
        return "content";
    }
}
