package com.mrntlu.myanimeinfo.view;

import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeTopList;
import java.util.List;

public interface OnToplistLoaded {

    void onToplistLoaded(List<GETAnimeTopList> topList);

    void onFailedToLoad();
}
