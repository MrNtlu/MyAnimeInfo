package com.mrntlu.myanimeinfo.viewmodel;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.mrntlu.myanimeinfo.R;
import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeByID;
import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeGenre;
import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeSearch;
import com.mrntlu.myanimeinfo.service.model.jsonbody.GETUserAnimelist;
import com.mrntlu.myanimeinfo.service.model.jsonbody.GETUserFavs;
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.AnimeGenreBody;
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.AnimeResponseBody;
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.AnimeReviewsBody;
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.AnimeTopListBody;
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.CharacterResponseBody;
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.UserAnimelistResponseBody;
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.UserProfileResponseBody;
import com.mrntlu.myanimeinfo.service.repository.AnimeAPI;
import com.mrntlu.myanimeinfo.view.OnCharactersLoaded;
import com.mrntlu.myanimeinfo.view.OnDataLoaded;
import com.mrntlu.myanimeinfo.view.OnGenreListLoaded;
import com.mrntlu.myanimeinfo.view.OnReviewsLoaded;
import com.mrntlu.myanimeinfo.view.OnToplistLoaded;
import com.mrntlu.myanimeinfo.view.OnUserInfoLoaded;
import com.mrntlu.myanimeinfo.view.adapter.AnimeSearchAdapter;
import java.util.List;
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

    public void getUserAnimelist(String username){
        Call<UserAnimelistResponseBody> call=animeAPI.getUserAnimeList(username);

        call.enqueue(new Callback<UserAnimelistResponseBody>() {
            @Override
            public void onResponse(Call<UserAnimelistResponseBody> call, Response<UserAnimelistResponseBody> response) {
                if (!response.isSuccessful()){
                    Log.d(TAG, "onResponse: "+response.code()+" "+response.message()+" "+call.request());
                    return;
                }
                Log.d(TAG, "onResponse: "+response.message()+" "+call.request());

                UserAnimelistResponseBody body=response.body();

                for (GETUserAnimelist animelist:body.getAnime()){
                    Log.d(TAG, "Anime Title: "+animelist.getTitle());
                    Log.d(TAG, "Anime Mal_ID: "+animelist.getMal_id());
                    Log.d(TAG, "Anime Score: "+animelist.getScore());
                    Log.d(TAG, "Anime Watched: "+animelist.getWatched_episodes());
                    Log.d(TAG, "Anime Total: "+animelist.getTotal_episodes());
                    int status=animelist.getWatching_status();
                    String mStatus="";
                    if (status==1) mStatus="Watching";
                    else if (status==2)mStatus="Completed";
                    else if (status==3)mStatus="On-Hold";
                    else if (status==4)mStatus="Dropped";
                    else if (status==6)mStatus="Plan to Watch";
                    Log.d(TAG, "Watching Status: "+mStatus);
                }
            }

            @Override
            public void onFailure(Call<UserAnimelistResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage()+" "+
                        call.request().toString());
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
                    Log.d(TAG, "onResponseNotSuccessful: "+response.code()+" "+response.message()+" "+call.request());
                    return;
                }
                onUserInfoLoaded.onUserInfoLoaded(response.body());
            }

            @Override
            public void onFailure(Call<UserProfileResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage()+" "+
                        call.request().toString());
            }
        });
    }

    public void getTopAnimes(int page, String subtype, final OnToplistLoaded onToplistLoaded){
        Call<AnimeTopListBody> call=animeAPI.getTopAnimes(page, subtype);

        call.enqueue(new Callback<AnimeTopListBody>() {
            @Override
            public void onResponse(Call<AnimeTopListBody> call, Response<AnimeTopListBody> response) {
                if (!response.isSuccessful()){
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
            public void onResponse(Call<AnimeGenreBody> call, Response<AnimeGenreBody> response) {
                if (!response.isSuccessful()){
                    return;
                }

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
        Call<CharacterResponseBody> call=animeAPI.getCharacterByID(mal_id);

        call.enqueue(new Callback<CharacterResponseBody>() {
            @Override
            public void onResponse(Call<CharacterResponseBody> call, Response<CharacterResponseBody> response) {
                if (!response.isSuccessful()){
                    return;
                }

                CharacterResponseBody body=response.body();
                onCharactersLoaded.onCharactersLoaded(body.getCharacters());
            }

            @Override
            public void onFailure(Call<CharacterResponseBody> call, Throwable t) {
                //TODO Toast
                Log.d(TAG, "onFailure: "+t.getMessage()+" "+
                        call.request().toString());
            }
        });
    }

    public void searchAnime(String query, int page, final ProgressBar progressBar, final List<GETAnimeSearch> animeSearchList, final AnimeSearchAdapter adapter){
        Call<AnimeResponseBody> call=animeAPI.getAnimeBySearch(query,page);
        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<AnimeResponseBody>() {
            @Override
            public void onResponse(Call<AnimeResponseBody> call, Response<AnimeResponseBody> response) {
                if (!response.isSuccessful()){
                    return;
                }
                progressBar.setVisibility(View.GONE);

                AnimeResponseBody animeSearches =response.body();
                animeSearchList.clear();
                animeSearchList.addAll(animeSearches.getResults());

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<AnimeResponseBody> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplication().getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
