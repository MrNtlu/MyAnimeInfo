package com.mrntlu.myanimeinfo.service.repository;

import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeByID;
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.AnimeResponseBody;
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.AnimeGenreBody;
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.AnimeReviewsBody;
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.AnimeTopListBody;
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.CharacterResponseBody;
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.UserAnimelistResponseBody;
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.UserProfileResponseBody;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AnimeAPI {

    @GET("search/anime")
    Call<AnimeResponseBody> getAnimeBySearch(@Query("q") String q, @Query("page") int page);

    @GET("anime/{mal_id}")
    Call<GETAnimeByID> getAnimeByID(@Path("mal_id") int mal_id);

    @GET("anime/{mal_id}/characters_staff")
    Call<CharacterResponseBody> getCharacterByID(@Path("mal_id") int mal_id);

    @GET("genre/anime/{genreID}/{page}")
    Call<AnimeGenreBody> getGenreByID(@Path("genreID") int genreID,@Path("page") int page);

    @GET("anime/{mal_id}/reviews/{page}")
    Call<AnimeReviewsBody> getAnimeReviewsByID(@Path("mal_id") int mal_id, @Path("page") int page);

    @GET("top/anime/{page}/{subtype}")
    Call<AnimeTopListBody> getTopAnimes(@Path("page") int page, @Path("subtype") String subtype);

    @GET("user/{username}/profile")
    Call<UserProfileResponseBody> getUserProfile(@Path("username") String username);

    @GET("user/{username}/animelist")
    Call<UserAnimelistResponseBody> getUserAnimeList(@Path("username") String username);
}
