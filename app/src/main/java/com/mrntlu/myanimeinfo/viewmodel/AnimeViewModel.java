package com.mrntlu.myanimeinfo.viewmodel;

import android.app.Application;
import android.util.Log;
import com.mrntlu.myanimeinfo.R;
import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeByID;
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.AnimeGenreBody;
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.AnimeResponseBody;
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.AnimeReviewsBody;
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.AnimeScheduleBody;
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.AnimeTopListBody;
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.AnimeCharactersResponseBody;
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.CharacterInfoBody;
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.UserAnimelistResponseBody;
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.UserProfileResponseBody;
import com.mrntlu.myanimeinfo.service.repository.AnimeAPI;
import com.mrntlu.myanimeinfo.view.OnAnimeListLoaded;
import com.mrntlu.myanimeinfo.view.OnAnimeSearchLoaded;
import com.mrntlu.myanimeinfo.view.OnCharacterInfoLoaded;
import com.mrntlu.myanimeinfo.view.OnCharactersLoaded;
import com.mrntlu.myanimeinfo.view.OnDataLoaded;
import com.mrntlu.myanimeinfo.view.OnGenreListLoaded;
import com.mrntlu.myanimeinfo.view.OnReviewsLoaded;
import com.mrntlu.myanimeinfo.view.OnScheduleLoaded;
import com.mrntlu.myanimeinfo.view.OnToplistLoaded;
import com.mrntlu.myanimeinfo.view.OnUserInfoLoaded;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnimeViewModel extends AndroidViewModel {

    private AnimeAPI animeAPI;
    private final String TAG="testJSON";

    public AnimeViewModel(@NonNull Application application) {
        super(application);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(getApplication().getApplicationContext().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        animeAPI=retrofit.create(AnimeAPI.class);
    }

    public void getCharacterByID(int characterID, final OnCharacterInfoLoaded onCharacterInfoLoaded){
        Call<CharacterInfoBody> call=animeAPI.getCharacterInfoByID(characterID);

        call.enqueue(new Callback<CharacterInfoBody>() {
            @Override
            public void onResponse(@NonNull Call<CharacterInfoBody> call, @NonNull Response<CharacterInfoBody> response) {
                if (!response.isSuccessful()){
                    onCharacterInfoLoaded.onFailedToLoad();
                    return;
                }

                onCharacterInfoLoaded.onCharacterInfoLoaded(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<CharacterInfoBody> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage()+" "+
                        call.request().toString());
                onCharacterInfoLoaded.onFailedToLoad();
            }
        });
    }

    public void getAnimeSchedule(final OnScheduleLoaded onScheduleLoaded){
        Call<AnimeScheduleBody> call=animeAPI.getAnimeSchedule();

        call.enqueue(new Callback<AnimeScheduleBody>() {
            @Override
            public void onResponse(@NonNull Call<AnimeScheduleBody> call, @NonNull Response<AnimeScheduleBody> response) {
                if (!response.isSuccessful()){
                    onScheduleLoaded.onFailedToLoad();
                    return;
                }

                onScheduleLoaded.onScheduleLoaded(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<AnimeScheduleBody> call, @NonNull Throwable t) {
                onScheduleLoaded.onFailedToLoad();
            }
        });
    }

    public void getUserAnimelist(String username, String argument,final OnAnimeListLoaded onAnimeListLoaded){
        Call<UserAnimelistResponseBody> call=animeAPI.getUserAnimeList(username,argument);

        call.enqueue(new Callback<UserAnimelistResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<UserAnimelistResponseBody> call, @NonNull Response<UserAnimelistResponseBody> response) {
                if (!response.isSuccessful()){
                    onAnimeListLoaded.onFailedToLoad();
                    return;
                }

                UserAnimelistResponseBody body=response.body();
                onAnimeListLoaded.onAnimelistLoaded(body.getAnime());
            }

            @Override
            public void onFailure(@NonNull Call<UserAnimelistResponseBody> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage()+" "+
                        call.request().toString());
                onAnimeListLoaded.onFailedToLoad();
            }
        });
    }

    public void getUserProfile(String username, final OnUserInfoLoaded onUserInfoLoaded){
        Call<UserProfileResponseBody> call=animeAPI.getUserProfile(username);

        call.enqueue(new Callback<UserProfileResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<UserProfileResponseBody> call, @NonNull Response<UserProfileResponseBody> response) {
                if (!response.isSuccessful()){
                    onUserInfoLoaded.onUserNotFound();
                    return;
                }
                onUserInfoLoaded.onUserInfoLoaded(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<UserProfileResponseBody> call, @NonNull Throwable t) {
                onUserInfoLoaded.onUserNotFound();
            }
        });
    }

    public void getTopAnimes(int page, String subtype, final OnToplistLoaded onToplistLoaded){
        Call<AnimeTopListBody> call=animeAPI.getTopAnimes(page, subtype);

        call.enqueue(new Callback<AnimeTopListBody>() {
            @Override
            public void onResponse(@NonNull Call<AnimeTopListBody> call, @NonNull Response<AnimeTopListBody> response) {
                if (!response.isSuccessful()){
                    onToplistLoaded.onFailedToLoad();
                    return;
                }

                AnimeTopListBody body=response.body();
                onToplistLoaded.onToplistLoaded(body.getTop());
            }

            @Override
            public void onFailure(@NonNull Call<AnimeTopListBody> call, @NonNull Throwable t) {
                onToplistLoaded.onFailedToLoad();
            }
        });
    }

    public void getAnimeReviews(int mal_id, int page, final OnReviewsLoaded onReviewsLoaded){
        Call<AnimeReviewsBody> call=animeAPI.getAnimeReviewsByID(mal_id,page);

        call.enqueue(new Callback<AnimeReviewsBody>() {
            @Override
            public void onResponse(@NonNull Call<AnimeReviewsBody> call, @NonNull Response<AnimeReviewsBody> response) {
                if (!response.isSuccessful()){
                    onReviewsLoaded.onFailedToLoad();
                    return;
                }

                AnimeReviewsBody body=response.body();
                onReviewsLoaded.onReviewsLoaded(body.getReviews());
            }

            @Override
            public void onFailure(@NonNull Call<AnimeReviewsBody> call, @NonNull Throwable t) {
                onReviewsLoaded.onFailedToLoad();
            }
        });
    }

    public void getAnimeByGenre(int genreID, int page, final OnGenreListLoaded onGenreListLoaded){
        Call<AnimeGenreBody> call=animeAPI.getGenreByID(genreID,page);

        call.enqueue(new Callback<AnimeGenreBody>() {
            @Override
            public void onResponse(@NonNull Call<AnimeGenreBody> call, @NonNull Response<AnimeGenreBody> response) {
                if (!response.isSuccessful()){
                    onGenreListLoaded.onFailedToLoad();
                    return;
                }

                AnimeGenreBody body=response.body();
                onGenreListLoaded.onGenreListLoaded(body.getAnime());
            }

            @Override
            public void onFailure(@NonNull Call<AnimeGenreBody> call, @NonNull Throwable t) {
                onGenreListLoaded.onFailedToLoad();
            }
        });
    }

    public void getAnimeByID(final int mal_id, final OnDataLoaded onDataLoaded){
        Call<GETAnimeByID> call=animeAPI.getAnimeByID(mal_id);

        call.enqueue(new Callback<GETAnimeByID>() {
            @Override
            public void onResponse(@NonNull Call<GETAnimeByID> call, @NonNull Response<GETAnimeByID> response) {
                if (!response.isSuccessful()){
                    onDataLoaded.onFailedToLoad();
                    return;
                }
                onDataLoaded.isDataLoaded(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<GETAnimeByID> call, @NonNull Throwable t) {
                onDataLoaded.onFailedToLoad();
            }
        });
    }

    public void getAnimeCharacters(int mal_id, final OnCharactersLoaded onCharactersLoaded){
        Call<AnimeCharactersResponseBody> call=animeAPI.getCharacterByID(mal_id);

        call.enqueue(new Callback<AnimeCharactersResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<AnimeCharactersResponseBody> call, @NonNull Response<AnimeCharactersResponseBody> response) {
                if (!response.isSuccessful()){
                    onCharactersLoaded.onFailedToLoad();
                    return;
                }

                AnimeCharactersResponseBody body=response.body();
                onCharactersLoaded.onCharactersLoaded(body.getCharacters());
            }

            @Override
            public void onFailure(@NonNull Call<AnimeCharactersResponseBody> call, @NonNull Throwable t) {
                onCharactersLoaded.onFailedToLoad();
            }
        });
    }

    public void searchAnime(String query, int page, final OnAnimeSearchLoaded onAnimeSearchLoaded){
        Call<AnimeResponseBody> call=animeAPI.getAnimeBySearch(query,page);

        call.enqueue(new Callback<AnimeResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<AnimeResponseBody> call, @NonNull Response<AnimeResponseBody> response) {
                if (!response.isSuccessful()){
                    onAnimeSearchLoaded.onFailedToLoad();
                    return;
                }
                onAnimeSearchLoaded.onAnimeSearchLoaded(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<AnimeResponseBody> call, @NonNull Throwable t) {
                onAnimeSearchLoaded.onFailedToLoad();
            }
        });
    }
}
