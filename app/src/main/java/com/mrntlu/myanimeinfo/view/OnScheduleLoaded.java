package com.mrntlu.myanimeinfo.view;

import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.AnimeScheduleBody;

public interface OnScheduleLoaded {

    void onScheduleLoaded(AnimeScheduleBody body);

    void onFailedToLoad();
}
