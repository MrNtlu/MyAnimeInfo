package com.mrntlu.myanimeinfo.view.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.mrntlu.myanimeinfo.R;
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.AnimeScheduleBody;
import com.mrntlu.myanimeinfo.view.OnScheduleLoaded;
import com.mrntlu.myanimeinfo.view.adapter.AnimeSchedulePagerAdapter;
import com.mrntlu.myanimeinfo.viewmodel.AnimeViewModel;

import java.lang.ref.WeakReference;
import java.util.Calendar;

public class FragmentAnimeSchedule extends Fragment implements OnScheduleLoaded {

    private WeakReference<ViewPager> viewPagerWeakReference;
    private WeakReference<TabLayout> tabLayoutWeakReference;
    private WeakReference<ConstraintLayout> constraintLayoutWeakReference;

    public FragmentAnimeSchedule() {
        // Required empty public constructor
    }
    public static FragmentAnimeSchedule newInstance() {
        return new FragmentAnimeSchedule();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_anime_schedule, container, false);
        final ViewPager viewPager= v.findViewById(R.id.scheduleViewPager);
        final TabLayout tabLayout= v.findViewById(R.id.scheduleTabLayout);
        final ConstraintLayout loadingScreen= v.findViewById(R.id.loadingScreen);

        viewPagerWeakReference=new WeakReference<>(viewPager);
        tabLayoutWeakReference=new WeakReference<>(tabLayout);
        constraintLayoutWeakReference=new WeakReference<>(loadingScreen);

        AnimeViewModel viewModel = ViewModelProviders.of(getActivity()).get(AnimeViewModel.class);
        viewModel.getAnimeSchedule(this);

        return v;
    }


    @Override
    public void onScheduleLoaded(AnimeScheduleBody body) {
        if(FragmentAnimeSchedule.this.isVisible()) {

            ViewPager weakViewPager=viewPagerWeakReference.get();
            TabLayout weakTabLayout=tabLayoutWeakReference.get();
            ConstraintLayout weakConstraint=constraintLayoutWeakReference.get();

            if (weakConstraint!=null && weakTabLayout!=null && weakViewPager!=null) {
                AnimeSchedulePagerAdapter pagerAdapter = new AnimeSchedulePagerAdapter(getChildFragmentManager(), body);
                weakViewPager.setAdapter(pagerAdapter);
                weakTabLayout.setupWithViewPager(weakViewPager);
                weakConstraint.setVisibility(View.GONE);

                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_WEEK);
                int index = 0;
                switch (day) {
                    case Calendar.SUNDAY:
                        index = 6;
                        break;
                    case Calendar.MONDAY:
                        index = 0;
                        break;
                    case Calendar.TUESDAY:
                        index = 1;
                        break;
                    case Calendar.WEDNESDAY:
                        index = 2;
                        break;
                    case Calendar.THURSDAY:
                        index = 3;
                        break;
                    case Calendar.FRIDAY:
                        index = 4;
                        break;
                    case Calendar.SATURDAY:
                        index = 5;
                        break;
                }

                TabLayout.Tab tab = weakTabLayout.getTabAt(index);
                if (tab != null) {
                    tab.select();
                }
            }
        }
    }

    @Override
    public void onFailedToLoad() {
        Toast.makeText(getContext().getApplicationContext(), "Error! Too many requests.", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().popBackStackImmediate();
    }
}
