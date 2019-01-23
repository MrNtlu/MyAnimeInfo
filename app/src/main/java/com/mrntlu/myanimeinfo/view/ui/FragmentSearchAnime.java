package com.mrntlu.myanimeinfo.view.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import com.mrntlu.myanimeinfo.R;
import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeSearch;
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.AnimeResponseBody;
import com.mrntlu.myanimeinfo.view.OnAnimeSearchLoaded;
import com.mrntlu.myanimeinfo.view.adapter.AnimeSearchAdapter;
import com.mrntlu.myanimeinfo.viewmodel.AnimeViewModel;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class FragmentSearchAnime extends Fragment implements OnAnimeSearchLoaded {

    private String searchViewText="";
    private AnimeViewModel viewModel;
    private List<GETAnimeSearch> animeSearchList=new ArrayList<>();
    private String TAG="testJSON";

    private WeakReference<AnimeSearchAdapter> adapterWeakReference;
    private WeakReference<ProgressBar> progressBarWeakReference;

    public FragmentSearchAnime() {
        // Required empty public constructor
    }

    public static FragmentSearchAnime newInstance() {
        return new FragmentSearchAnime();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search_anime, container, false);
        final RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
        SearchView searchView = v.findViewById(R.id.searchView);
        final ProgressBar progressBar = v.findViewById(R.id.progressBar);
        progressBarWeakReference=new WeakReference<>(progressBar);

        viewModel = ViewModelProviders.of(getActivity()).get(AnimeViewModel.class);
        final AnimeSearchAdapter adapter = new AnimeSearchAdapter(getContext(), animeSearchList);
        adapterWeakReference=new WeakReference<>(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    recyclerView.setVisibility(View.VISIBLE);

                } else if (searchViewText.length() <= 0) {
                    recyclerView.setVisibility(View.GONE);
                    if (animeSearchList.size() > 0) {
                        animeSearchList.clear();
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (s.length() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    viewModel.searchAnime(s, 1, FragmentSearchAnime.this);
                } else {
                    recyclerView.setVisibility(View.GONE);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchViewText = s;
                return false;
            }
        });

        return v;
    }

    @Override
    public void onAnimeSearchLoaded(AnimeResponseBody body) {
        if (FragmentSearchAnime.this.isVisible()  && FragmentSearchAnime.this.isAdded() && (FragmentSearchAnime.this.getView()!=null) && !FragmentSearchAnime.this.isRemoving()) {
            ProgressBar weakProgress=progressBarWeakReference.get();
            AnimeSearchAdapter weakAdapter=adapterWeakReference.get();

            if (weakProgress!=null && weakAdapter!=null){
                weakProgress.setVisibility(View.GONE);
                animeSearchList.clear();
                animeSearchList.addAll(body.getResults());
                weakAdapter.notifyDataSetChanged();
            }
        }
    }
}
