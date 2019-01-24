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
                    //TODO error check
                    onCharacterInfoLoaded.onFailedToLoad();
                    Log.d(TAG, "onResponse: "+response.code()+" "+response.message()+" "+call.request());
                    return;
                }

                onCharacterInfoLoaded.onCharacterInfoLoaded(response.body());
            }

            @Override
            public void onFailure(Call<CharacterInfoBody> call, Throwable t) {
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
            public void onResponse(Call<AnimeScheduleBody> call, Response<AnimeScheduleBody> response) {
                if (!response.isSuccessful()){
                    //TODO error check
                    Log.d(TAG, "onResponse: "+response.code()+" "+response.message()+" "+call.request());
                    return;
                }

                onScheduleLoaded.onScheduleLoaded(response.body());
            }

            @Override
            public void onFailure(Call<AnimeScheduleBody> call, Throwable t) {
                //TODO
                Log.d(TAG, "onFailure: "+t.getMessage()+" "+
                        call.request().toString());
            }
        });
    }

    public void getUserAnimelist(String username, String argument,final OnAnimeListLoaded onAnimeListLoaded){
        Call<UserAnimelistResponseBody> call=animeAPI.getUserAnimeList(username,argument);

        call.enqueue(new Callback<UserAnimelistResponseBody>() {
            @Override
            public void onResponse(Call<UserAnimelistResponseBody> call, Response<UserAnimelistResponseBody> response) {
                if (!response.isSuccessful()){
                    //TODO error check
                    onAnimeListLoaded.onFailedToLoad();
                    return;
                }

                UserAnimelistResponseBody body=response.body();
                onAnimeListLoaded.onAnimelistLoaded(body.getAnime());
            }

            @Override
            public void onFailure(Call<UserAnimelistResponseBody> call, Throwable t) {
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
            public void onResponse(Call<UserProfileResponseBody> call, Response<UserProfileResponseBody> response) {
                if (!response.isSuccessful()){
                    //TODO SendBack
                    onUserInfoLoaded.onUserNotFound();
                    return;
                }
                onUserInfoLoaded.onUserInfoLoaded(response.body());
            }

            @Override
            public void onFailure(Call<UserProfileResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage()+" "+
                        call.request().toString());
                onUserInfoLoaded.onUserNotFound();
            }
        });
    }

    public void getTopAnimes(int page, String subtype, final OnToplistLoaded onToplistLoaded){
        Call<AnimeTopListBody> call=animeAPI.getTopAnimes(page, subtype);

        call.enqueue(new Callback<AnimeTopListBody>() {
            @Override
            public void onResponse(Call<AnimeTopListBody> call, Response<AnimeTopListBody> response) {
                if (!response.isSuccessful()){
                    //TODO Error
                    return;
                }

                AnimeTopListBody body=response.body();
                onToplistLoaded.onToplistLoaded(body.getTop());
            }

            @Override
            public void onFailure(Call<AnimeTopListBody> call, Throwable t) {
                //TODO Toast
                Log.d(TAG, "onFailure: "+t.getMessage()+" "+
                        call.request().toString());
            }
        });
    }

    public void getAnimeReviews(int mal_id, int page, final OnReviewsLoaded onReviewsLoaded){
        Call<AnimeReviewsBody> call=animeAPI.getAnimeReviewsByID(mal_id,page);

        call.enqueue(new Callback<AnimeReviewsBody>() {
            @Override
            public void onResponse(Call<AnimeReviewsBody> call, Response<AnimeReviewsBody> response) {
                if (!response.isSuccessful()){
                    //TODO Error
                    return;
                }

                AnimeReviewsBody body=response.body();
                onReviewsLoaded.onReviewsLoaded(body.getReviews());
            }

            @Override
            public void onFailure(Call<AnimeReviewsBody> call, Throwable t) {
                //TODO Toast
                Log.d(TAG, "onFailure: "+t.getMessage()+" "+
                        call.request().toString());
            }
        });
    }

    public void getAnimeByGenre(int genreID, int page, final OnGenreListLoaded onGenreListLoaded){
        Call<AnimeGenreBody> call=animeAPI.getGenreByID(genreID,page);

        call.enqueue(new Callback<AnimeGenreBody>() {
            @Override
            public void onResponse(@NonNull Call<AnimeGenreBody> call, @NonNull Response<AnimeGenreBody> response) {
                if (!response.isSuccessful()){
                    //TODO Too many requests!!!
                    Log.d(TAG, response.message()+" "+call.request());
                    return;
                }
                Log.d(TAG, "onResponse: success");
                AnimeGenreBody body=response.body();
                onGenreListLoaded.onGenreListLoaded(body.getAnime());
            }

            @Override
            public void onFailure(Call<AnimeGenreBody> call, Throwable t) {
                //TODO Toast
                Log.d(TAG, "onFailure: "+t.getMessage()+" "+
                        call.request().toString());
            }
        });
    }

    public void getAnimeByID(final int mal_id, final OnDataLoaded onDataLoaded){
        Call<GETAnimeByID> call=animeAPI.getAnimeByID(mal_id);

        call.enqueue(new Callback<GETAnimeByID>() {
            @Override
            public void onResponse(Call<GETAnimeByID> call, Response<GETAnimeByID> response) {
                if (!response.isSuccessful()){
                    return;
                }
                onDataLoaded.isDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<GETAnimeByID> call, Throwable t) {
                //TODO Toast
                Log.d(TAG, "onFailure: "+t.getMessage()+" "+
                        call.request().toString());
            }
        });
    }

    public void getAnimeCharacters(int mal_id, final OnCharactersLoaded onCharactersLoaded){
        Call<AnimeCharactersResponseBody> call=animeAPI.getCharacterByID(mal_id);

        call.enqueue(new Callback<AnimeCharactersResponseBody>() {
            @Override
            public void onResponse(Call<AnimeCharactersResponseBody> call, Response<AnimeCharactersResponseBody> response) {
                if (!response.isSuccessful()){
                    return;
                }

                AnimeCharactersResponseBody body=response.body();
                onCharactersLoaded.onCharactersLoaded(body.getCharacters());
            }

            @Override
            public void onFailure(Call<AnimeCharactersResponseBody> call, Throwable t) {
                //TODO Toast
                Log.d(TAG, "onFailure: "+t.getMessage()+" "+
                        call.request().toString());
            }
        });
    }

    public void searchAnime(String query, int page, final OnAnimeSearchLoaded onAnimeSearchLoaded){
        Call<AnimeResponseBody> call=animeAPI.getAnimeBySearch(query,page);

        call.enqueue(new Callback<AnimeResponseBody>() {
            @Override
            public void onResponse(Call<AnimeResponseBody> call, Response<AnimeResponseBody> response) {
                if (!response.isSuccessful()){
                    return;
                }
                onAnimeSearchLoaded.onAnimeSearchLoaded(response.body());
            }

            @Override
            public void onFailure(Call<AnimeResponseBody> call, Throwable t) {

            }
        });
    }
}
