package com.mrntlu.myanimeinfo.view.ui;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.mrntlu.myanimeinfo.R;
import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeSearch;
import com.mrntlu.myanimeinfo.view.adapter.AnimeSearchAdapter;
import com.mrntlu.myanimeinfo.viewmodel.AnimeViewModel;

import java.util.ArrayList;
import java.util.List;


public class FragmentSearchAnime extends Fragment {

    private View v;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private String searchViewText="";
    private AnimeViewModel viewModel;
    private ProgressBar progressBar;
    private List<GETAnimeSearch> animeSearchList=new ArrayList<>();
    private AnimeSearchAdapter adapter;

    public FragmentSearchAnime() {
        // Required empty public constructor
    }

    public static FragmentSearchAnime newInstance() {
        FragmentSearchAnime fragment = new FragmentSearchAnime();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_search_anime, container, false);
        recyclerView=v.findViewById(R.id.recyclerView);
        searchView=v.findViewById(R.id.searchView);
        progressBar=v.findViewById(R.id.progressBar);

        viewModel= ViewModelProviders.of(getActivity()).get(AnimeViewModel.class);
        adapter=new AnimeSearchAdapter(getContext(),animeSearchList);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    recyclerView.setVisibility(View.VISIBLE);
                }
                else if (searchViewText.length()<=0){
                    recyclerView.setVisibility(View.GONE);
                    if(animeSearchList.size()>0){
                        animeSearchList.clear();
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (s.length()>0){
                    recyclerView.setVisibility(View.VISIBLE);
                    viewModel.searchAnime(s,1,progressBar,animeSearchList,adapter);
                }
                else{
                    recyclerView.setVisibility(View.GONE);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchViewText=s;
                return false;
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(v!=null) {
            final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}
