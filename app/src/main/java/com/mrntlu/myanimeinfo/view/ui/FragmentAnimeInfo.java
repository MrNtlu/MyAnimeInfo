package com.mrntlu.myanimeinfo.view.ui;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.mrntlu.myanimeinfo.R;
import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeByID;
import com.mrntlu.myanimeinfo.view.OnDataLoaded;
import com.mrntlu.myanimeinfo.view.adapter.AnimeInfoPagerAdapter;
import com.mrntlu.myanimeinfo.viewmodel.AnimeViewModel;

public class FragmentAnimeInfo extends Fragment implements OnDataLoaded {

    private View v;
    private int mal_id;
    private AnimeViewModel viewModel;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ImageView animeImage;
    private ProgressBar animeImageProgress;
    private TextView animeTitle,animeType,scoreText,episodesText,rankText,favsText,statusText,premieredText;
    private AnimeInfoPagerAdapter pagerAdapter;


    public FragmentAnimeInfo() {
        // Required empty public constructor
    }

    public static FragmentAnimeInfo newInstance(int mal_id) {
        FragmentAnimeInfo fragment = new FragmentAnimeInfo();
        fragment.mal_id=mal_id;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_anime_info, container, false);
        viewPager=v.findViewById(R.id.viewPager);
        tabLayout=v.findViewById(R.id.tabLayout);
        animeImageProgress=v.findViewById(R.id.animeImageProgress);
        animeTitle=v.findViewById(R.id.animeTitle);
        animeType=v.findViewById(R.id.animeType);
        scoreText=v.findViewById(R.id.scoreText);
        episodesText=v.findViewById(R.id.episodesText);
        rankText=v.findViewById(R.id.rankText);
        favsText=v.findViewById(R.id.favsText);
        statusText=v.findViewById(R.id.statusText);
        animeImage=v.findViewById(R.id.animeImage);
        premieredText=v.findViewById(R.id.premieredText);

        viewModel= ViewModelProviders.of(getActivity()).get(AnimeViewModel.class);
        viewModel.getAnimeByID(mal_id,this);
        return v;
    }

    @Override
    public void isDataLoaded(GETAnimeByID getAnimeByID) {
        pagerAdapter=new AnimeInfoPagerAdapter(getChildFragmentManager(),getAnimeByID);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        Glide.with(getContext()).load(getAnimeByID.getImage_url()).into(animeImage);
        animeImageProgress.setVisibility(View.GONE);
        animeTitle.setText(getAnimeByID.getTitle());
        animeType.setText(getAnimeByID.getType());
        scoreText.setText(String.valueOf(getAnimeByID.getScore()));
        episodesText.setText(String.valueOf(getAnimeByID.getEpisodes()));
        rankText.setText(String.valueOf(getAnimeByID.getRank()));
        favsText.setText(String.valueOf(getAnimeByID.getFavorites()));
        statusText.setText(getAnimeByID.getStatus());
        premieredText.setText("Premiered: "+getAnimeByID.getPremiered());
    }
}
