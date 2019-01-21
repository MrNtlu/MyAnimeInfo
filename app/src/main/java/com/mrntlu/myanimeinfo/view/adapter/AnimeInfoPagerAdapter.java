package com.mrntlu.myanimeinfo.view.adapter;

import android.util.Log;

import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeByID;
import com.mrntlu.myanimeinfo.view.ui.FragmentSearchAnime;
import com.mrntlu.myanimeinfo.view.ui.InnerFragmentAnimeInfo;
import com.mrntlu.myanimeinfo.view.ui.InnerFragmentAnimeReviews;
import com.mrntlu.myanimeinfo.view.ui.InnerFragmentCharacters;
import com.mrntlu.myanimeinfo.view.ui.InnerFragmentRelatedAnimes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class AnimeInfoPagerAdapter extends FragmentStatePagerAdapter {

    GETAnimeByID animeByID;

    public AnimeInfoPagerAdapter(@NonNull FragmentManager fm, GETAnimeByID animeByID) {
        super(fm);
        this.animeByID=animeByID;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment returnFragment;
        switch (position){
            case 0:
                returnFragment= InnerFragmentAnimeInfo.newInstance(animeByID.getSynopsis(),animeByID.getBackground());
                break;
            case 1:
                returnFragment= InnerFragmentRelatedAnimes.newInstance(animeByID.getRelated());
                break;
            case 2:
                returnFragment= InnerFragmentCharacters.newInstance(animeByID.getMal_id());
                break;
            case 3:
                returnFragment= InnerFragmentAnimeReviews.newInstance(animeByID.getMal_id());
                break;
            default:
                return FragmentSearchAnime.newInstance();
        }
        return returnFragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence title;
        switch (position){
            case 0:
                title="Info";
                break;
            case 1:
                title="Related Animes";
                break;
            case 2:
                title="Characters";
                break;
            case 3:
                title="Reviews";
                break;
            default:
                return "Info";
        }
        return title;
    }

    @Override
    public int getCount() {
        return 4;
    }
}