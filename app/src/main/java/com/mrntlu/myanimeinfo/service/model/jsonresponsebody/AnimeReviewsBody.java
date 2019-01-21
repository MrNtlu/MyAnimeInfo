package com.mrntlu.myanimeinfo.service.model.jsonresponsebody;

import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeReviewByID;

import java.util.List;

public class AnimeReviewsBody {

    private List<GETAnimeReviewByID> reviews;

    public AnimeReviewsBody(List<GETAnimeReviewByID> reviews) {
        this.reviews = reviews;
    }

    public List<GETAnimeReviewByID> getReviews() {
        return reviews;
    }
}
