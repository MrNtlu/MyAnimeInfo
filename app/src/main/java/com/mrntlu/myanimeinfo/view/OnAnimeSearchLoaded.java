package com.mrntlu.myanimeinfo.view;

import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.AnimeResponseBody;

public interface OnAnimeSearchLoaded {

    void onAnimeSearchLoaded(AnimeResponseBody body);

    void onFailedToLoad();
}
