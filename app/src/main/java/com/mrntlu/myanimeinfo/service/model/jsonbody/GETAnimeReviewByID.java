package com.mrntlu.myanimeinfo.service.model.jsonbody;

public class GETAnimeReviewByID {
    private Reviewer reviewer;
    private String content;

    public GETAnimeReviewByID(Reviewer reviewer, String content) {
        this.reviewer = reviewer;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public Reviewer getReviewer() {
        return reviewer;
    }

    public class Reviewer{
        private String image_url;
        private String username;
        private Scores scores;

        public Reviewer(String image_url, String username, Scores scores) {
            this.image_url = image_url;
            this.username = username;
            this.scores = scores;
        }

        public String getImage_url() {
            return image_url;
        }

        public String getUsername() {
            return username;
        }

        public Scores getScores() {
            return scores;
        }

        public class Scores{
            private int overall;
            private int story;
            private int animation;
            private int sound;
            private int character;
            private int enjoyment;

            public Scores(int overall, int story, int animation, int sound, int character, int enjoyment) {
                this.overall = overall;
                this.story = story;
                this.animation = animation;
                this.sound = sound;
                this.character = character;
                this.enjoyment = enjoyment;
            }

            public int getOverall() {
                return overall;
            }

            public int getStory() {
                return story;
            }

            public int getAnimation() {
                return animation;
            }

            public int getSound() {
                return sound;
            }

            public int getCharacter() {
                return character;
            }

            public int getEnjoyment() {
                return enjoyment;
            }
        }
    }
}
