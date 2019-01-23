package com.mrntlu.myanimeinfo.view.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mrntlu.myanimeinfo.R;
import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeGenre;
import com.mrntlu.myanimeinfo.view.adapter.AnimeGenreListAdapter;
import java.util.List;

public class FragmentScheduleByDate extends Fragment {

    private List<GETAnimeGenre> animeByDate;

    public FragmentScheduleByDate() {
        // Required empty public constructor
    }

    public static FragmentScheduleByDate newInstance(List<GETAnimeGenre> animeByDate) {
        FragmentScheduleByDate fragment = new FragmentScheduleByDate();
        fragment.animeByDate=animeByDate;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_anime_genre_list, container, false);
        RecyclerView scheduleRV = v.findViewById(R.id.animeGenreRV);

        AnimeGenreListAdapter adapter = new AnimeGenreListAdapter(getContext(), animeByDate);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
        scheduleRV.setLayoutManager(gridLayoutManager);
        scheduleRV.setAdapter(adapter);

        return v;
    }

}
