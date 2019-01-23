package com.mrntlu.myanimeinfo.view.ui;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.mrntlu.myanimeinfo.R;
import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeCharacters;
import com.mrntlu.myanimeinfo.view.OnCharactersLoaded;
import com.mrntlu.myanimeinfo.view.adapter.InnerAnimeCharactersAdapter;
import com.mrntlu.myanimeinfo.viewmodel.AnimeViewModel;

import java.lang.ref.WeakReference;
import java.util.List;

public class InnerFragmentCharacters extends Fragment implements OnCharactersLoaded {

    private int mal_id;

    private WeakReference<ProgressBar> progressBarWeakReference;
    private WeakReference<RecyclerView> recyclerViewWeakReference;

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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_inner_characters, container, false);
        RecyclerView charactersRV= v.findViewById(R.id.charactersRV);
        ProgressBar progressBar= v.findViewById(R.id.characterProgressbar);
        progressBar.setVisibility(View.VISIBLE);
        progressBarWeakReference=new WeakReference<>(progressBar);
        recyclerViewWeakReference=new WeakReference<>(charactersRV);

        AnimeViewModel viewModel = ViewModelProviders.of(getActivity()).get(AnimeViewModel.class);
        viewModel.getAnimeCharacters(mal_id,this);

        return v;
    }

    @Override
    public void onCharactersLoaded(List<GETAnimeCharacters> getAnimeCharacters) {
        if (InnerFragmentCharacters.this.isVisible() && InnerFragmentCharacters.this.isAdded()) {
            RecyclerView weakRV=recyclerViewWeakReference.get();
            ProgressBar weakProgress = progressBarWeakReference.get();
            if(weakProgress!=null && weakRV!=null) {
                InnerAnimeCharactersAdapter adapter = new InnerAnimeCharactersAdapter(getContext(), getAnimeCharacters);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                weakRV.setLayoutManager(linearLayoutManager);
                weakRV.setAdapter(adapter);
                weakProgress.setVisibility(View.GONE);
            }
        }
    }
}
