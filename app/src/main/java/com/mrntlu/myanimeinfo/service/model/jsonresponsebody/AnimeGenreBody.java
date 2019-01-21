package com.mrntlu.myanimeinfo.service.model.jsonresponsebody;

import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeGenre;

import java.util.List;

public class AnimeGenreBody {

    private List<GETAnimeGenre> anime;

    public AnimeGenreBody(List<GETAnimeGenre> anime) {
        this.anime = anime;
    }

    public List<GETAnimeGenre> getAnime() {
        return anime;
    }
}
