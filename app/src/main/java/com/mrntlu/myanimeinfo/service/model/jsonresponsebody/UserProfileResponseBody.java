package com.mrntlu.myanimeinfo.service.model.jsonresponsebody;

import com.mrntlu.myanimeinfo.service.model.jsonbody.GETUserFavs;

import java.util.List;

public class UserProfileResponseBody {

    private String username;
    private String image_url;
    private AnimeStats anime_stats;
    private Favorites favorites;

    public UserProfileResponseBody(String username, String image_url, AnimeStats anime_stats, Favorites favorites) {
        this.username = username;
        this.image_url = image_url;
        this.anime_stats = anime_stats;
        this.favorites = favorites;
    }

    public String getUsername() {
        return username;
    }

    public String getImage_url() {
        return image_url;
    }

    public AnimeStats getAnime_stats() {
        return anime_stats;
    }

    public Favorites getFavorites() {
        return favorites;
    }

    public class Favorites{

        private List<GETUserFavs> anime;
        private List<GETUserFavs> characters;

        public Favorites(List<GETUserFavs> anime, List<GETUserFavs> characters) {
            this.anime = anime;
            this.characters = characters;
        }

        public List<GETUserFavs> getAnime() {
            return anime;
        }

        public List<GETUserFavs> getCharacters() {
            return characters;
        }
    }

    public class AnimeStats{
        private double days_watched;
        private double mean_score;
        private int watching;
        private int completed;
        private int on_hold;
        private int dropped;
        private int plan_to_watch;
        private int episodes_watched;

        public AnimeStats(double days_watched, double mean_score, int watching, int completed, int on_hold, int dropped, int plan_to_watch, int episodes_watched) {
            this.days_watched = days_watched;
            this.mean_score = mean_score;
            this.watching = watching;
            this.completed = completed;
            this.on_hold = on_hold;
            this.dropped = dropped;
            this.plan_to_watch = plan_to_watch;
            this.episodes_watched = episodes_watched;
        }

        public double getDays_watched() {
            return days_watched;
        }

        public double getMean_score() {
            return mean_score;
        }

        public int getWatching() {
            return watching;
        }

        public int getCompleted() {
            return completed;
        }

        public int getOn_hold() {
            return on_hold;
        }

        public int getDropped() {
            return dropped;
        }

        public int getPlan_to_watch() {
            return plan_to_watch;
        }

        public int getEpisodes_watched() {
            return episodes_watched;
        }
    }
}
