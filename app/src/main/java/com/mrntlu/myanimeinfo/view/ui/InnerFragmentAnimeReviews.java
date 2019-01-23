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
import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeReviewByID;
import com.mrntlu.myanimeinfo.view.OnReviewsLoaded;
import com.mrntlu.myanimeinfo.view.adapter.InnerAnimeReviewsAdapter;
import com.mrntlu.myanimeinfo.viewmodel.AnimeViewModel;

import java.lang.ref.WeakReference;
import java.util.List;

public class InnerFragmentAnimeReviews extends Fragment implements OnReviewsLoaded {

    private int mal_id;

    private WeakReference<ProgressBar> progressBarWeakReference;
    private WeakReference<RecyclerView> recyclerViewWeakReference;

    public InnerFragmentAnimeReviews() {
        // Required empty public constructor
    }


    public static InnerFragmentAnimeReviews newInstance(int mal_id) {
        InnerFragmentAnimeReviews fragment = new InnerFragmentAnimeReviews();
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
        View v = inflater.inflate(R.layout.fragment_inner_anime_reviews, container, false);
        RecyclerView animeReviewsRV= v.findViewById(R.id.animeReviewsRV);
        ProgressBar reviewProgress= v.findViewById(R.id.reviewProgress);
        reviewProgress.setVisibility(View.VISIBLE);

        progressBarWeakReference=new WeakReference<>(reviewProgress);
        recyclerViewWeakReference=new WeakReference<>(animeReviewsRV);

        AnimeViewModel viewModel = ViewModelProviders.of(getActivity()).get(AnimeViewModel.class);
        viewModel.getAnimeReviews(mal_id,1,this);

        return v;
    }

    @Override
    public void onReviewsLoaded(List<GETAnimeReviewByID> getAnimeReviewByID) {
        if (InnerFragmentAnimeReviews.this.isVisible()) {
            RecyclerView weakRV = recyclerViewWeakReference.get();
            ProgressBar weakProgress = progressBarWeakReference.get();

            if (weakProgress != null && weakRV != null) {
                InnerAnimeReviewsAdapter adapter = new InnerAnimeReviewsAdapter(getAnimeReviewByID, getContext());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                weakRV.setLayoutManager(linearLayoutManager);
                weakRV.setAdapter(adapter);
                weakProgress.setVisibility(View.GONE);
            }
        }
    }
}
