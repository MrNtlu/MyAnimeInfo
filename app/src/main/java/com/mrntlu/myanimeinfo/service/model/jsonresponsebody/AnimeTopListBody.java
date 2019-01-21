package com.mrntlu.myanimeinfo.service.model.jsonresponsebody;

import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeTopList;

import java.util.List;

public class AnimeTopListBody {

    private List<GETAnimeTopList> top;

    public AnimeTopListBody(List<GETAnimeTopList> top) {
        this.top = top;
    }

    public List<GETAnimeTopList> getTop() {
        return top;
    }
}
