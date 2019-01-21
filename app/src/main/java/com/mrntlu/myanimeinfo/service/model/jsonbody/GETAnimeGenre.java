package com.mrntlu.myanimeinfo.service.model.jsonbody;

public class GETAnimeGenre {

    private int mal_id;
    private String title;
    private String image_url;
    private String type;
    private double score;
    private int episodes;

    public GETAnimeGenre(int mal_id, String title, String image_url, String type, double score, int episodes) {
        this.mal_id = mal_id;
        this.title = title;
        this.image_url = image_url;
        this.type = type;
        this.score = score;
        this.episodes = episodes;
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

    public String getType() {
        return type;
    }

    public double getScore() {
        return score;
    }

    public int getEpisodes() {
        return episodes;
    }
}
