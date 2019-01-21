package com.mrntlu.myanimeinfo.service.model.jsonbody;

public class GETUserFavs {
    private int mal_id;
    private String image_url;
    private String name;

    public GETUserFavs(int mal_id, String image_url, String name) {
        this.mal_id = mal_id;
        this.image_url = image_url;
        this.name = name;
    }

    public int getMal_id() {
        return mal_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getName() {
        return name;
    }
}
