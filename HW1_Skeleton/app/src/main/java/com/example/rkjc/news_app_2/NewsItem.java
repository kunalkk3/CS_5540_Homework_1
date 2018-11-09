package com.example.rkjc.news_app_2;

public class NewsItem {

    private String title;
    private String description;
    private String date;
    private String url;

    public NewsItem(String title,String description,String date, String url)
    {
        this.title=title;
        this.description=description;
        this.date=date;
        this.url=url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String title) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String title) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
