package com.mrntlu.myanimeinfo.view.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.mrntlu.myanimeinfo.R;
import com.mrntlu.myanimeinfo.service.model.jsonbody.GETUserAnimelist;
import com.mrntlu.myanimeinfo.view.OnAnimeListLoaded;
import com.mrntlu.myanimeinfo.view.adapter.UserAnimeListAdapter;
import com.mrntlu.myanimeinfo.viewmodel.AnimeViewModel;

import java.lang.ref.WeakReference;
import java.util.List;

public class FragmentUserAnimeList extends Fragment implements OnAnimeListLoaded {

    private String username;
    private AnimeViewModel viewModel;

    private WeakReference<ProgressBar> progressBarWeakReference;
    private WeakReference<RecyclerView> recyclerViewWeakReference;


    public FragmentUserAnimeList() {
        // Required empty public constructor
    }

    public static FragmentUserAnimeList newInstance(String username) {
        FragmentUserAnimeList fragment = new FragmentUserAnimeList();
        fragment.username=username;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user_anime_list, container, false);
        RecyclerView userAnimelistRV= v.findViewById(R.id.userAnimelistRV);
        final ProgressBar animelistProgress= v.findViewById(R.id.animelistProgress);

        progressBarWeakReference=new WeakReference<>(animelistProgress);
        recyclerViewWeakReference=new WeakReference<>(userAnimelistRV);

        Spinner animelistSpinner = v.findViewById(R.id.animelistSpinner);
        viewModel= ViewModelProviders.of(getActivity()).get(AnimeViewModel.class);

        final String[] subtypes = {"All","Watching","Completed","On Hold","Dropped", "Plan to Watch"};
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item, subtypes);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        animelistSpinner.setAdapter(spinnerArrayAdapter);

        animelistSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                animelistProgress.setVisibility(View.VISIBLE);
                viewModel.getUserAnimelist(username,subtypes[i].toLowerCase().replace(" ",""),FragmentUserAnimeList.this);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        return v;
    }

    @Override
    public void onAnimelistLoaded(List<GETUserAnimelist> userAnimelist) {
        if (FragmentUserAnimeList.this.isVisible() && FragmentUserAnimeList.this.isAdded() && (FragmentUserAnimeList.this.getView()!=null)) {
            ProgressBar weakProgress=progressBarWeakReference.get();
            RecyclerView weakRV=recyclerViewWeakReference.get();
            if (weakProgress!=null && weakRV!=null) {
                UserAnimeListAdapter adapter = new UserAnimeListAdapter(getContext(), userAnimelist);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                weakProgress.setVisibility(View.GONE);
                weakRV.setLayoutManager(linearLayoutManager);
                weakRV.setAdapter(adapter);
            }
        }
    }

    @Override
    public void onFailedToLoad() {
        if (FragmentUserAnimeList.this.isVisible() && FragmentUserAnimeList.this.isAdded() && (FragmentUserAnimeList.this.getView()!=null)) {
            ProgressBar weakProgress=progressBarWeakReference.get();
            if (weakProgress!=null) weakProgress.setVisibility(View.GONE);
            if (getContext().getApplicationContext()!=null)Toast.makeText(getContext().getApplicationContext(), "Too Many Requests", Toast.LENGTH_SHORT).show();
            if (getActivity().getSupportFragmentManager()!=null) getActivity().getSupportFragmentManager().popBackStackImmediate();
        }
    }
}
