package com.example.news.demonews.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.example.news.demonews.model.News;

@Component
public class NewsService {

    private static List<News> newslist = new ArrayList<>();

    private static final String NEWS_URL = "https://newsapi.org/v2/top-headlines";
    private static final String API_KEY = "ccaf5d41cc5140c984818c344edcc14d";


    public List<News> getNews(String country, String category, String keyword){

        String query ;
        StringBuffer response = new StringBuffer();
        List<News> news;
        if ("".equals(keyword)){
            query = "?country="+country+"&category="+category+"&apiKey="+API_KEY;
        }
        else {
            query = "?q="+keyword+"country="+country+"&category="+category+"&apiKey="+API_KEY;
        }

        try {
            String final_url = NEWS_URL + query;
            URL url = new URL(final_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                response.append(output);
            }
            br.close();
            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        news =  parseJSON(response.toString(), category, country, keyword);
        return news;
    }

    private List<News> parseJSON(String data, String category, String country, String keyword){

        try{
            JSONParser parser = new JSONParser();
            JSONObject dataobj = (JSONObject)parser.parse(data);
            JSONArray articles = (JSONArray) dataobj.get("articles");

            for (Object article: articles) {

                JSONObject jsonobj = (JSONObject)article;
                String title = (String) jsonobj.get("title");
                String description = (String) jsonobj.get("description");
                String newsurl = (String) jsonobj.get("url");
                News news  = new News(country,category,keyword,title,description,newsurl);
                newslist.add(news);
                System.out.println(newslist);
            }

        } catch (ParseException e){
            e.printStackTrace();
        }

        return newslist;
    }
}