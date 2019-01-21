package com.mrntlu.myanimeinfo.service.model.jsonresponsebody;

import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeSearch;

import java.util.List;

public class AnimeResponseBody {

    private List<GETAnimeSearch> results;

    public AnimeResponseBody(List<GETAnimeSearch> results) {
        this.results = results;
    }

    public List<GETAnimeSearch> getResults() {
        return results;
    }
}
