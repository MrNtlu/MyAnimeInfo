package com.mrntlu.myanimeinfo.view.ui;


import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mrntlu.myanimeinfo.R;
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.UserProfileResponseBody;
import com.mrntlu.myanimeinfo.view.OnUserInfoLoaded;
import com.mrntlu.myanimeinfo.viewmodel.AnimeViewModel;

public class FragmentUserInfo extends Fragment implements OnUserInfoLoaded {

    private View v;
    private String username;
    private AnimeViewModel viewModel;
    private TextView usernameText,watchingText,droppedText,episodesWatchedText,daysWatchedText,planToWatchText,meanScoreText,completedText,onHoldText;
    private ProgressBar userImageProgress;
    private Button animelistButton,favoritesButton;
    private ImageView userImage;
    private ConstraintLayout loadingScreen;

    public FragmentUserInfo() {
        // Required empty public constructor
    }

    public static FragmentUserInfo newInstance(String username) {
        FragmentUserInfo fragment = new FragmentUserInfo();
        fragment.username=username;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_user_info, container, false);
        usernameText=v.findViewById(R.id.usernameText);
        watchingText=v.findViewById(R.id.watchingText);
        droppedText=v.findViewById(R.id.droppedText);
        episodesWatchedText=v.findViewById(R.id.episodesWatchedText);
        daysWatchedText=v.findViewById(R.id.daysWatchedText);
        planToWatchText=v.findViewById(R.id.planToWatchText);
        meanScoreText=v.findViewById(R.id.meanScoreText);
        completedText=v.findViewById(R.id.completedText);
        onHoldText=v.findViewById(R.id.onHoldText);
        userImageProgress=v.findViewById(R.id.userImageProgress);
        animelistButton=v.findViewById(R.id.animelistButton);
        favoritesButton=v.findViewById(R.id.favoritesButton);
        userImage=v.findViewById(R.id.userImage);
        loadingScreen=v.findViewById(R.id.loadingScreen);

        animelistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "AnimeList", Toast.LENGTH_SHORT).show();
            }
        });

        favoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Favorites", Toast.LENGTH_SHORT).show();
            }
        });

        viewModel= ViewModelProviders.of(getActivity()).get(AnimeViewModel.class);
        viewModel.getUserProfile(username,this);
        return v;
    }

    @Override
    public void onUserInfoLoaded(UserProfileResponseBody profileBody) {
        usernameText.setText(profileBody.getUsername());
        Glide.with(v).load(profileBody.getImage_url()).addListener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                userImageProgress.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                userImageProgress.setVisibility(View.GONE);
                return false;
            }
        }).into(userImage);
        UserProfileResponseBody.AnimeStats stats=profileBody.getAnime_stats();
        completedText.setText(String.valueOf(stats.getCompleted()));
        watchingText.setText(String.valueOf(stats.getWatching()));
        droppedText.setText(String.valueOf(stats.getDropped()));
        onHoldText.setText(String.valueOf(stats.getOn_hold()));
        episodesWatchedText.setText(String.valueOf(stats.getEpisodes_watched()));
        daysWatchedText.setText(String.valueOf(stats.getDays_watched()));
        planToWatchText.setText(String.valueOf(stats.getPlan_to_watch()));
        meanScoreText.setText(String.valueOf(stats.getMean_score()));
        loadingScreen.setVisibility(View.GONE);

        /*UserProfileResponseBody body=response.body();

                Log.d(TAG, "Username: "+body.getUsername());
                Log.d(TAG, "Image URL: "+body.getImage_url());
                Log.d(TAG, "Anime Stats: "+body.getAnime_stats().getCompleted()+" "+body.getAnime_stats().getDays_watched()+" "+body.getAnime_stats().getMean_score());

                for (GETUserFavs animes:body.getFavorites().getAnime()){
                    Log.d(TAG, "Animes: "+animes.getName()+" "+animes.getMal_id()+" "+animes.getImage_url());
                }
                for (GETUserFavs characters:body.getFavorites().getCharacters()){
                    Log.d(TAG, "Characters: "+characters.getName()+" "+characters.getMal_id()+" "+characters.getImage_url());
                }*/
    }

    @Override
    public void onUserNotFound() {
        //TODO Onusernotfound
        Toast.makeText(getContext(), "User Not Found", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().popBackStackImmediate();
    }
}
