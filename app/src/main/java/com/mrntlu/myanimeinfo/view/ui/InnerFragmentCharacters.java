package com.mrntlu.myanimeinfo.view.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.mrntlu.myanimeinfo.R;
import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeCharacters;
import com.mrntlu.myanimeinfo.view.OnCharactersLoaded;
import com.mrntlu.myanimeinfo.view.adapter.InnerAnimeCharactersAdapter;
import com.mrntlu.myanimeinfo.viewmodel.AnimeViewModel;

import java.util.List;

public class InnerFragmentCharacters extends Fragment implements OnCharactersLoaded {

    private View v;
    private RecyclerView charactersRV;
    private int mal_id;
    private AnimeViewModel viewModel;
    private InnerAnimeCharactersAdapter adapter;
    private ProgressBar progressBar;

    public InnerFragmentCharacters() {
        // Required empty public constructor
    }

    public static InnerFragmentCharacters newInstance(int mal_id) {
        InnerFragmentCharacters fragment = new InnerFragmentCharacters();
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
        v= inflater.inflate(R.layout.fragment_inner_characters, container, false);
        charactersRV=v.findViewById(R.id.charactersRV);
        progressBar=v.findViewById(R.id.characterProgressbar);
        progressBar.setVisibility(View.VISIBLE);
        viewModel= ViewModelProviders.of(getActivity()).get(AnimeViewModel.class);
        viewModel.getAnimeCharacters(mal_id,this);

        return v;
    }


    @Override
    public void onCharactersLoaded(List<GETAnimeCharacters> getAnimeCharacters) {
        adapter=new InnerAnimeCharactersAdapter(getContext(),getAnimeCharacters);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        charactersRV.setLayoutManager(linearLayoutManager);
        charactersRV.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
    }
}
