package com.mrntlu.myanimeinfo.service.model.jsonresponsebody;

import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeCharacters;
import java.util.List;

public class CharacterInfoBody {

    private String name;
    private List<String> nicknames;
    private String about;
    private int member_favorites;
    private String image_url;
    private List<GETAnimeCharacters> animeography;

    public CharacterInfoBody(String name, List<String> nicknames, String about, int member_favorites, String image_url, List<GETAnimeCharacters> animeography) {
        this.name = name;
        this.nicknames = nicknames;
        this.about = about;
        this.member_favorites = member_favorites;
        this.image_url = image_url;
        this.animeography = animeography;
    }

    public String getName() {
        return name;
    }

    public List<String> getNicknames() {
        return nicknames;
    }

    public String getAbout() {
        return about;
    }

    public int getMember_favorites() {
        return member_favorites;
    }

    public String getImage_url() {
        return image_url;
    }

    public List<GETAnimeCharacters> getAnimeography() {
        return animeography;
    }
}
