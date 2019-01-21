package com.mrntlu.myanimeinfo.view;

import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeReviewByID;

import java.util.List;

public interface OnReviewsLoaded {

    void onReviewsLoaded(List<GETAnimeReviewByID> getAnimeReviewByID);
}
