package com.mrntlu.myanimeinfo.view;

import com.mrntlu.myanimeinfo.service.model.jsonbody.GETUserAnimelist;
import java.util.List;

public interface OnAnimeListLoaded {

    void onAnimelistLoaded(List<GETUserAnimelist> userAnimelist);

}
