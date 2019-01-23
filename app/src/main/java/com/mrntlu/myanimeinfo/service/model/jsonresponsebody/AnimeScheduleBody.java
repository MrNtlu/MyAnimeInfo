package com.mrntlu.myanimeinfo.service.model.jsonresponsebody;

import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeGenre;

import java.util.List;

public class AnimeScheduleBody {

    private List<GETAnimeGenre> monday;
    private List<GETAnimeGenre> tuesday;
    private List<GETAnimeGenre> wednesday;
    private List<GETAnimeGenre> thursday;
    private List<GETAnimeGenre> friday;
    private List<GETAnimeGenre> saturday;
    private List<GETAnimeGenre> sunday;

    public AnimeScheduleBody(List<GETAnimeGenre> monday, List<GETAnimeGenre> tuesday, List<GETAnimeGenre> wednesday, List<GETAnimeGenre> thursday, List<GETAnimeGenre> friday, List<GETAnimeGenre> saturday, List<GETAnimeGenre> sunday) {
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }

    public List<GETAnimeGenre> getMonday() {
        return monday;
    }

    public List<GETAnimeGenre> getTuesday() {
        return tuesday;
    }

    public List<GETAnimeGenre> getWednesday() {
        return wednesday;
    }

    public List<GETAnimeGenre> getThursday() {
        return thursday;
    }

    public List<GETAnimeGenre> getFriday() {
        return friday;
    }

    public List<GETAnimeGenre> getSaturday() {
        return saturday;
    }

    public List<GETAnimeGenre> getSunday() {
        return sunday;
    }
}
