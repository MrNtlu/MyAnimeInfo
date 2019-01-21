package com.mrntlu.myanimeinfo.view;

import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeCharacters;

import java.util.List;

public interface OnCharactersLoaded {

    void onCharactersLoaded(List<GETAnimeCharacters> getAnimeCharacters);

}
