package com.example.demo.News;

import com.example.demo.Commentary.Comment;
import com.example.demo.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
public class NewsController {
    @Autowired
    NewsService newsService;

    @GetMapping("news")
    public List<News> getNewsList() {
        return newsService.getNewsList();
    }

    @GetMapping("ask")
    public List<News> getNewsAsk() {
        return newsService.getNewsAsk();
    }

    @GetMapping("show")
    public List<News> getNewsShow() {
        return newsService.getNewsShow();
    }

    @GetMapping("newest")
    public List<News> getNewest() {
        return newsService.getNewest();
    }

    @GetMapping("news/{id}")
    public Optional<News> getNews(@PathVariable Long id) {
        return newsService.getNews(id);
    }

    @PostMapping("submit")
    public Long createNews(@RequestBody News news) {
        /*response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);*/
        return newsService.createNews(news);

    }

    @GetMapping("news/{id}/comments")
    public List<Comment> getComments(@PathVariable Long id) {
        return newsService.getComments(id);
    }

    @PutMapping("news/{id}/newcomment")
    public void addComment(@PathVariable("id") Long id, @RequestBody Comment comment) {
        newsService.newComment(id, comment);
    }

    @PutMapping("news/{id}/like")
    public void like(@PathVariable("id") Long id, @RequestBody User user) {
        newsService.like(id, user);
    }

    @GetMapping("news/user")
    public List<News> getNewsByUsername(@RequestParam String username) {
        return newsService.getNewsByUsername(username);
    }

}
