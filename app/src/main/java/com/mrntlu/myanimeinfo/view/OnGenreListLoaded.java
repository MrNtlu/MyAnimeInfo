package com.mrntlu.myanimeinfo.view;

import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeGenre;
import java.util.List;

public interface OnGenreListLoaded {

    void onGenreListLoaded(List<GETAnimeGenre> animeGenres);
}
