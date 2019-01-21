package com.mrntlu.myanimeinfo.view.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.mrntlu.myanimeinfo.R;
import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeGenre;
import com.mrntlu.myanimeinfo.view.OnGenreListLoaded;
import com.mrntlu.myanimeinfo.view.adapter.AnimeGenreListAdapter;
import com.mrntlu.myanimeinfo.viewmodel.AnimeViewModel;
import java.util.List;

public class FragmentAnimeGenreList extends Fragment implements OnGenreListLoaded {

    private View v;
    private RecyclerView animeGenreRV;
    private ProgressBar genreProgress;
    private AnimeViewModel viewModel;
    private int genreID;
    private AnimeGenreListAdapter adapter;

    public FragmentAnimeGenreList() {
        // Required empty public constructor
    }

    public static FragmentAnimeGenreList newInstance(int genreID) {
        FragmentAnimeGenreList fragment = new FragmentAnimeGenreList();
        fragment.genreID=genreID;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_anime_genre_list, container, false);
        animeGenreRV=v.findViewById(R.id.animeGenreRV);
        genreProgress=v.findViewById(R.id.genreProgress);
        genreProgress.setVisibility(View.VISIBLE);
        viewModel= ViewModelProviders.of(getActivity()).get(AnimeViewModel.class);
        viewModel.getAnimeByGenre(genreID,1,this);

        return v;
    }

    @Override
    public void onGenreListLoaded(List<GETAnimeGenre> animeGenres) {
        adapter=new AnimeGenreListAdapter(getContext(),animeGenres);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
        animeGenreRV.setLayoutManager(gridLayoutManager);
        animeGenreRV.setAdapter(adapter);
        genreProgress.setVisibility(View.GONE);
    }
}
