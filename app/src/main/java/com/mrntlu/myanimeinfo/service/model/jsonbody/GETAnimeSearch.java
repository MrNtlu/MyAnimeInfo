package com.mrntlu.myanimeinfo.service.model.jsonbody;

public class GETAnimeSearch {

    private int mal_id;
    private String title;
    private String image_url;
    private double score;
    private int episodes;
    private String type;
    private boolean airing;

    public GETAnimeSearch(int mal_id, String title, String image_url, double score, int episodes, String type, boolean airing) {
        this.mal_id = mal_id;
        this.title = title;
        this.image_url = image_url;
        this.score = score;
        this.episodes = episodes;
        this.type = type;
        this.airing = airing;
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

    public int getEpisodes() {
        return episodes;
    }

    public String getType() {
        return type;
    }

    public boolean isAiring() {
        return airing;
    }
}
