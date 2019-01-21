package com.mrntlu.myanimeinfo.service.model.jsonbody;

public class GETUserAnimelist {

    private int mal_id;
    private String title;
    private String image_url;
    private double score;
    private int total_episodes;
    private int watched_episodes;
    private String type;
    private int watching_status;

    public GETUserAnimelist(int mal_id, String title, String image_url, double score, int total_episodes, int watched_episodes, String type, int watching_status) {
        this.mal_id = mal_id;
        this.title = title;
        this.image_url = image_url;
        this.score = score;
        this.total_episodes = total_episodes;
        this.watched_episodes = watched_episodes;
        this.type = type;
        this.watching_status = watching_status;
    }

    public int getWatching_status() {
        return watching_status;
    }

    public int getMal_id() {
        return mal_id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage_url() {
        return image_url;
    }

    public double getScore() {
        return score;
    }

    public int getTotal_episodes() {
        return total_episodes;
    }

    public int getWatched_episodes() {
        return watched_episodes;
    }

    public String getType() {
        return type;
    }
}
