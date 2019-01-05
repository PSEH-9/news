package com.example.news.demonews.controller;

import com.example.news.demonews.model.News;
import com.example.news.demonews.service.NewsService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class NewsController {

    @Autowired
    private NewsService newsService;


    @GetMapping("/news/{country}/{category}")
    public JSONObject retrieveNews(@PathVariable String country, @PathVariable String category) {
        List<News> response_data = newsService.getNews(country, category, "");
        JSONObject obj = new JSONObject();
        obj.put("results", response_data);
        return obj;
    }

    @GetMapping("/news/{country}/{category}/{keyword}")
    public JSONObject retrieveNewsKeyword(@PathVariable String country, @PathVariable String category,
                                          @PathVariable String keyword) {
        List<News> response_data = newsService.getNews(country, category, keyword);
        JSONObject obj = new JSONObject();
        obj.put("results", response_data);
        return obj;
    }

}
