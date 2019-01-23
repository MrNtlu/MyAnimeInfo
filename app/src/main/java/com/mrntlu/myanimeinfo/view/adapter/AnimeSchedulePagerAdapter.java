package com.mrntlu.myanimeinfo.view.adapter;

import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.AnimeScheduleBody;
import com.mrntlu.myanimeinfo.view.ui.FragmentScheduleByDate;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class AnimeSchedulePagerAdapter extends FragmentStatePagerAdapter {

    private AnimeScheduleBody animeScheduleBody;

    public AnimeSchedulePagerAdapter(@NonNull FragmentManager fm,AnimeScheduleBody animeScheduleBody) {
        super(fm);
        this.animeScheduleBody=animeScheduleBody;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment returnFragment;
        switch (position){
            case 0:
                returnFragment= FragmentScheduleByDate.newInstance(animeScheduleBody.getMonday());
                break;
            case 1:
                returnFragment=FragmentScheduleByDate.newInstance(animeScheduleBody.getTuesday());
                break;
            case 2:
                returnFragment=FragmentScheduleByDate.newInstance(animeScheduleBody.getWednesday());
                break;
            case 3:
                returnFragment=FragmentScheduleByDate.newInstance(animeScheduleBody.getThursday());
                break;
            case 4:
                returnFragment=FragmentScheduleByDate.newInstance(animeScheduleBody.getFriday());
                break;
            case 5:
                returnFragment=FragmentScheduleByDate.newInstance(animeScheduleBody.getSaturday());
                break;
            case 6:
                returnFragment=FragmentScheduleByDate.newInstance(animeScheduleBody.getSunday());
                break;
            default:
                return FragmentScheduleByDate.newInstance(animeScheduleBody.getMonday());
        }
        return returnFragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence title;
        switch (position){
            case 0:
                title="Monday";
                break;
            case 1:
                title="Tuesday";
                break;
            case 2:
                title="Wednesday";
                break;
            case 3:
                title="Thursday";
                break;
            case 4:
                title="Friday";
                break;
            case 5:
                title="Saturday";
                break;
            case 6:
                title="Sunday";
                break;
            default:
                return "Monday";
        }
        return title;
    }

    @Override
    public int getCount() {
        return 7;
    }
}
