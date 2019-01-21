package com.mrntlu.myanimeinfo.service.model.jsonbody;

public class GETAnimeCharacters {

    private String image_url;
    private String name;
    private String role;

    public GETAnimeCharacters(String image_url, String name, String role) {
        this.image_url = image_url;
        this.name = name;
        this.role = role;
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
}
