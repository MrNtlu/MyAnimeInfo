package com.mrntlu.myanimeinfo.service.model.jsonbody;

public class GETAnimeCharacters {

    private String image_url;
    private String name;
    private String role;
    private int mal_id;

    public GETAnimeCharacters(String image_url, String name, String role,int mal_id) {
        this.image_url = image_url;
        this.name = name;
        this.role = role;
        this.mal_id=mal_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public int getMal_id() {
        return mal_id;
    }
}
