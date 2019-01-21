package com.mrntlu.myanimeinfo.service.model.jsonbody;

import java.util.List;

public class GETAnimeByID {

    private int mal_id;
    private String title;
    private String image_url;
    private double score;
    private int episodes;
    private String type;
    private boolean airing;
    private String status;

    private int rank;
    private int popularity;
    private int members;
    private int favorites;

    private String synopsis;
    private String background;
    private String premiered;

    private RelatedAnimes related;

    public GETAnimeByID(int mal_id, String title, String image_url,
                        double score, int episodes, String type, boolean airing, String status, int rank, int popularity, int members,
                        int favorites, String synopsis, String background, String premiered, RelatedAnimes related) {
        this.mal_id = mal_id;
        this.title = title;
        this.image_url = image_url;
        this.score = score;
        this.episodes = episodes;
        this.type = type;
        this.airing = airing;
        this.status = status;
        this.rank = rank;
        this.popularity = popularity;
        this.members = members;
        this.favorites = favorites;
        this.synopsis = synopsis;
        this.background = background;
        this.premiered = premiered;
        this.related = related;
    }

    public int getMal_id() {
        return mal_id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage_url() {
        return image_url;
    }

    public double getScore() {
        return score;
    }

    public int getEpisodes() {
        return episodes;
    }

    public String getType() {
        return type;
    }

    public boolean isAiring() {
        return airing;
    }

    public String getStatus() {
        return status;
    }

    public int getRank() {
        return rank;
    }

    public int getPopularity() {
        return popularity;
    }

    public int getMembers() {
        return members;
    }

    public int getFavorites() {
        return favorites;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getBackground() {
        return background;
    }

    public String getPremiered() {
        return premiered;
    }

    public RelatedAnimes getRelated() {
        return related;
    }

    public class RelatedAnimes{
        private List<Adaptations> Adaptation;
        private List<AnimeRelations> Side;
        private List<AnimeRelations> Other;
        private List<AnimeRelations> Prequel;
        private List<AnimeRelations> Sequel;

        public RelatedAnimes(List<Adaptations> adaptation, List<AnimeRelations> sideStory, List<AnimeRelations> other, List<AnimeRelations> prequel, List<AnimeRelations> sequel) {
            Adaptation = adaptation;
            Side = sideStory;
            Other = other;
            Prequel=prequel;
            Sequel = sequel;
        }

        public List<AnimeRelations> getPrequel() {
            return Prequel;
        }

        public List<AnimeRelations> getSide() {
            return Side;
        }

        public List<AnimeRelations> getOther() {
            return Other;
        }

        public List<AnimeRelations> getSequel() {
            return Sequel;
        }

        public List<Adaptations> getAdaptation() {
            return Adaptation;
        }

        public class Adaptations {
            private String name;
            private int mal_id;

            public Adaptations(String name, int mal_id) {
                this.name = name;
                this.mal_id = mal_id;
            }

            public String getName() {
                return name;
            }

            public int getMal_id() {
                return mal_id;
            }
        }

        public class AnimeRelations {
            private String name;
            private int mal_id;
            private String type;

            public AnimeRelations(String name, int mal_id, String type) {
                this.name = name;
                this.mal_id = mal_id;
                this.type = type;
            }

            public String getName() {
                return name;
            }

            public int getMal_id() {
                return mal_id;
            }

            public String getType() {
                return type;
            }
        }
    }
}
