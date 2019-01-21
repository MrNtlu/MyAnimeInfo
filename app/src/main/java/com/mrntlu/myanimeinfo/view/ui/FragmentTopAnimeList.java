package com.mrntlu.myanimeinfo.view.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import com.mrntlu.myanimeinfo.R;
import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeTopList;
import com.mrntlu.myanimeinfo.view.OnToplistLoaded;
import com.mrntlu.myanimeinfo.view.adapter.AnimeToplistAdapter;
import com.mrntlu.myanimeinfo.viewmodel.AnimeViewModel;

import java.util.List;

public class FragmentTopAnimeList extends Fragment implements OnToplistLoaded {

    private View v;
    private RecyclerView toplistRV;
    private Spinner spinner;
    private AnimeViewModel viewModel;
    private AnimeToplistAdapter adapter;
    private ProgressBar progressBar;

    public FragmentTopAnimeList() {
        // Required empty public constructor
    }

    public static FragmentTopAnimeList newInstance() {
        FragmentTopAnimeList fragment = new FragmentTopAnimeList();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_top_anime_list, container, false);
        toplistRV=v.findViewById(R.id.toplistRV);
        spinner=v.findViewById(R.id.spinner);
        progressBar=v.findViewById(R.id.toplistProgress);
        viewModel= ViewModelProviders.of(getActivity()).get(AnimeViewModel.class);

        final String[] subtypes = {"All","Airing","Upcoming","TV","Movie", "OVA","Special"};
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item, subtypes);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String subType;
                if (i==0){
                    subType="";
                }else{
                    subType=subtypes[i].toLowerCase();
                }
                progressBar.setVisibility(View.VISIBLE);
                viewModel.getTopAnimes(1,subType,FragmentTopAnimeList.this);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        return v;
    }

    @Override
    public void onToplistLoaded(List<GETAnimeTopList> topList) {
        adapter=new AnimeToplistAdapter(getContext(),topList);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
        toplistRV.setLayoutManager(gridLayoutManager);
        toplistRV.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);

    }
}
