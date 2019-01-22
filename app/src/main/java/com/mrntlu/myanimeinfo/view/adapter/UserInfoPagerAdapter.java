package com.mrntlu.myanimeinfo.view.adapter;

import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.UserProfileResponseBody;
import com.mrntlu.myanimeinfo.view.ui.InnerFragmentUserFavs;
import com.mrntlu.myanimeinfo.view.ui.InnerFragmentUserStats;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class UserInfoPagerAdapter extends FragmentStatePagerAdapter {

    private UserProfileResponseBody responseBody;

    public UserInfoPagerAdapter(@NonNull FragmentManager fm, UserProfileResponseBody responseBody) {
        super(fm);
        this.responseBody = responseBody;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment returnFragment;
        switch (position){
            case 0:
                returnFragment= InnerFragmentUserStats.newInstance(responseBody);
                break;
            case 1:
                returnFragment= InnerFragmentUserFavs.newInstance(responseBody.getFavorites(),0);
                break;
            case 2:
                returnFragment= InnerFragmentUserFavs.newInstance(responseBody.getFavorites(),1);
                break;
            default:
                return InnerFragmentUserStats.newInstance(responseBody);
        }
        return returnFragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence title;
        switch (position){
            case 0:
                title="ANIME STATS";
                break;
            case 1:
                title="FAV ANIMES";
                break;
            case 2:
                title="FAV Characters";
                break;
            default:
                return "FAV ANIMES";
        }
        return title;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
