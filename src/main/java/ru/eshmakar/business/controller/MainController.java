package ru.eshmakar.business.controller;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import ru.eshmakar.business.repo.StatistikaRepo;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.List;

@EnableScheduling
@Controller
public class MainController {
    File file = new File("home/BO");

    {
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            if (mkdirs)
                System.out.println("Will create folder home/BO");
            else System.err.println("Not will create folder home/BO");
        }

    }

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
    @Autowired
    private StatistikaRepo statistikaRepo;

    @Scheduled(fixedDelay = 60_000 * 60 * 24) //каждый день очищает диск Z и удаляет старые записи
    public void cleanDirectoryZEveryDay() {
        try {
            FileUtils.cleanDirectory(new File("home/BO"));
        } catch (Exception ignored) {
        }

        statistikaRepo.findAll().forEach(e -> {
            if ((Duration.between(e.getDate().toInstant(), new Date().toInstant()).toDays() > 7)) {
                statistikaRepo.delete(e);
                System.out.println("Удалил старый запись, проверка была проведена через 7 дней");
            }
        });
    }

    @Scheduled(fixedDelay = 60_000 * 5) //каждые 5 минут обновляет главную страницу
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

    @CrossOrigin
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

    @CrossOrigin
    @GetMapping(value = "/{numbers}")
    public String getContentPage(Model model, @PathVariable String numbers) {
        if (!numbers.equals("favicon.ico")) {
            contentNews.setZagolovok(null);
            contentNews.setTelo(null);
            contentNews.setCommentsCount(null);
            contentNews.setId(null);

            try {
                news.getContent(numbers); //news_552764
            } catch (Exception e) {
                System.err.println("Неправильный номер: " + numbers);
            }

            String rawNumbers = numbers.replaceAll("(.*_)(\\d+)", "$2");
            try {
                news.getComments(numbers, Integer.parseInt(rawNumbers));
            } catch (Exception ignored) {
            }

            model.addAttribute("content", contentNews);
            model.addAttribute("number", numbers);
            model.addAttribute("rawNumbers", rawNumbers);
        }
        return "content";
    }


    @CrossOrigin
    @GetMapping("/top")
    public String populars(Model model) {
        List<LastNews> lasts = lastNewsRepo.getTop30ByOrderByCommentsDesc();
        model.addAttribute("tops", lasts);
        return "top";
    }


    @CrossOrigin
    @GetMapping("/com_{numbers}")
    public String getHtml(@PathVariable String numbers) {
        return "com_" + numbers;
    }

    @CrossOrigin
    @GetMapping("/info")
    public String info(Model model) {
        model.addAttribute("count", countOfReloadPages);
        return "info";
    }

    @CrossOrigin
    @GetMapping("/last")
    public String lastSeenPages(Model model) {
        model.addAttribute("stat", statistikaRepo.getTop300ByOrderByDateDesc());
        return "last";
    }

    @CrossOrigin
    @GetMapping("/robots.txt")
    public String getRobots() {
        return "robots";
    }
    

}
