package com.mrntlu.myanimeinfo.service.model.jsonresponsebody;

import com.mrntlu.myanimeinfo.service.model.jsonbody.GETUserAnimelist;

import java.util.List;

public class UserAnimelistResponseBody {
    private List<GETUserAnimelist> anime;

    public UserAnimelistResponseBody(List<GETUserAnimelist> anime) {
        this.anime = anime;
    }

    public List<GETUserAnimelist> getAnime() {
        return anime;
    }
}
