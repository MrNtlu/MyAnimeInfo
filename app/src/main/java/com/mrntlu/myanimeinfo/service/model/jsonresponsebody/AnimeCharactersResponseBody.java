package com.mrntlu.myanimeinfo.service.model.jsonresponsebody;

import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeCharacters;

import java.util.List;

public class AnimeCharactersResponseBody {

    private List<GETAnimeCharacters> characters;

    public AnimeCharactersResponseBody(List<GETAnimeCharacters> characters) {
        this.characters = characters;
    }

    public List<GETAnimeCharacters> getCharacters() {
        return characters;
    }
}
