package com.mrntlu.myanimeinfo.view.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

import java.lang.ref.WeakReference;
import java.util.List;

public class FragmentTopAnimeList extends Fragment implements OnToplistLoaded {

    private static final String TAG = "testJSON";
    private AnimeViewModel viewModel;

    private WeakReference<ProgressBar> progressBarWeakReference;
    private WeakReference<RecyclerView> recyclerViewWeakReference;

    public FragmentTopAnimeList() {
        // Required empty public constructor
    }

    public static FragmentTopAnimeList newInstance() {
        return new FragmentTopAnimeList();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_top_anime_list, container, false);
        Spinner spinner = v.findViewById(R.id.spinner);
        final RecyclerView toplistRV= v.findViewById(R.id.toplistRV);
        final ProgressBar progressBar= v.findViewById(R.id.toplistProgress);
        progressBarWeakReference=new WeakReference<>(progressBar);
        recyclerViewWeakReference=new WeakReference<>(toplistRV);

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
        if (FragmentTopAnimeList.this.isVisible() && FragmentTopAnimeList.this.isAdded() && (FragmentTopAnimeList.this.getView()!=null) && !FragmentTopAnimeList.this.isRemoving()) {
            RecyclerView weakRV=recyclerViewWeakReference.get();
            if (weakRV!=null) {
                ProgressBar weakProgress = progressBarWeakReference.get();
                if (weakProgress != null) weakProgress.setVisibility(View.GONE);
                AnimeToplistAdapter adapter = new AnimeToplistAdapter(getContext(), topList);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                weakRV.setLayoutManager(gridLayoutManager);
                weakRV.setAdapter(adapter);
            }
        }
    }
}
