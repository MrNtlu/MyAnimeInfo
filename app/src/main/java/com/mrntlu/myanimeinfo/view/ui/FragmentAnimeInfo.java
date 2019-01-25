package com.mrntlu.myanimeinfo.view.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.tabs.TabLayout;
import com.mrntlu.myanimeinfo.R;
import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeByID;
import com.mrntlu.myanimeinfo.view.OnDataLoaded;
import com.mrntlu.myanimeinfo.view.adapter.AnimeInfoPagerAdapter;
import com.mrntlu.myanimeinfo.viewmodel.AnimeViewModel;
import java.lang.ref.WeakReference;

public class FragmentAnimeInfo extends Fragment implements OnDataLoaded {

    private int mal_id;
    private WeakReference<TextView> weakAnimeTitle,weakAnimeType,weakAnimeScore,weakAnimeEpisodes,weakAnimeRank,weakAnimeFavs,weakAnimeStatus,weakAnimePremiered;
    private WeakReference<ViewPager> viewPagerWeakReference;
    private WeakReference<TabLayout> tabLayoutWeakReference;
    private WeakReference<ConstraintLayout> constraintLayoutWeakReference;
    private WeakReference<ProgressBar> progressBarWeakReference;
    private WeakReference<ImageView> imageViewWeakReference;

    public FragmentAnimeInfo() {
        // Required empty public constructor
    }

    public static FragmentAnimeInfo newInstance(int mal_id) {
        FragmentAnimeInfo fragment = new FragmentAnimeInfo();
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
        View v = inflater.inflate(R.layout.fragment_anime_info, container, false);
        ViewPager viewPager= v.findViewById(R.id.viewPager);
        TabLayout tabLayout= v.findViewById(R.id.tabLayout);
        ConstraintLayout loadingScreen= v.findViewById(R.id.loadingScreen);
        ProgressBar animeImageProgress= v.findViewById(R.id.animeImageProgress);
        ImageView animeImage= v.findViewById(R.id.animeImage);
        TextView animeTitle= v.findViewById(R.id.animeTitle);
        TextView animeType= v.findViewById(R.id.animeType);
        TextView scoreText= v.findViewById(R.id.scoreText);
        TextView episodesText= v.findViewById(R.id.episodesText);
        TextView rankText= v.findViewById(R.id.rankText);
        TextView favsText= v.findViewById(R.id.favsText);
        TextView statusText= v.findViewById(R.id.statusText);
        TextView premieredText= v.findViewById(R.id.premieredText);

        viewPagerWeakReference=new WeakReference<>(viewPager);
        tabLayoutWeakReference=new WeakReference<>(tabLayout);
        constraintLayoutWeakReference=new WeakReference<>(loadingScreen);
        progressBarWeakReference=new WeakReference<>(animeImageProgress);
        imageViewWeakReference=new WeakReference<>(animeImage);
        weakAnimeTitle=new WeakReference<>(animeTitle);
        weakAnimeType=new WeakReference<>(animeType);
        weakAnimeScore=new WeakReference<>(scoreText);
        weakAnimeEpisodes=new WeakReference<>(episodesText);
        weakAnimeRank=new WeakReference<>(rankText);
        weakAnimeFavs=new WeakReference<>(favsText);
        weakAnimeStatus=new WeakReference<>(statusText);
        weakAnimePremiered=new WeakReference<>(premieredText);

        AnimeViewModel viewModel = ViewModelProviders.of(getActivity()).get(AnimeViewModel.class);
        viewModel.getAnimeByID(mal_id,this);
        return v;
    }

    @Override
    public void isDataLoaded(GETAnimeByID getAnimeByID) {
        if (FragmentAnimeInfo.this.isVisible() && FragmentAnimeInfo.this.isAdded() && (FragmentAnimeInfo.this.getView()!=null)) {
            ViewPager weakViewPager = viewPagerWeakReference.get();
            TabLayout weakTabLayout = tabLayoutWeakReference.get();
            ConstraintLayout weakConstraint = constraintLayoutWeakReference.get();
            final ImageView weakImageView = imageViewWeakReference.get();
            final ProgressBar weakProgress=progressBarWeakReference.get();

            TextView animeTitle=weakAnimeTitle.get();
            TextView animeType=weakAnimeType.get();
            TextView scoreText=weakAnimeScore.get();
            TextView episodesText=weakAnimeEpisodes.get();
            TextView rankText=weakAnimeRank.get();
            TextView favsText=weakAnimeFavs.get();
            TextView statusText=weakAnimeStatus.get();
            TextView premieredText=weakAnimePremiered.get();

            if (weakConstraint != null && weakTabLayout != null && weakViewPager != null && weakProgress!=null) {

                AnimeInfoPagerAdapter pagerAdapter = new AnimeInfoPagerAdapter(getChildFragmentManager(), getAnimeByID);
                weakViewPager.setAdapter(pagerAdapter);
                weakTabLayout.setupWithViewPager(weakViewPager);

                Glide.with(getContext()).load(getAnimeByID.getImage_url()).addListener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        weakImageView.setImageResource(R.drawable.ic_no_picture);
                        weakProgress.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        weakProgress.setVisibility(View.GONE);
                        return false;
                    }
                }).into(weakImageView);

                weakConstraint.setVisibility(View.GONE);

                if (animeTitle!=null) animeTitle.setText(getAnimeByID.getTitle());
                if (animeType!=null) animeType.setText(getAnimeByID.getType());
                if (scoreText!=null) scoreText.setText(String.valueOf(getAnimeByID.getScore()));
                if (episodesText!=null) episodesText.setText(String.valueOf(getAnimeByID.getEpisodes()));
                if (rankText!=null) rankText.setText(String.valueOf(getAnimeByID.getRank()));
                if (favsText!=null) favsText.setText(String.valueOf(getAnimeByID.getFavorites()));
                if (statusText!=null) statusText.setText(getAnimeByID.getStatus());
                String premiered="Premiered: " + getAnimeByID.getPremiered();
                if (premieredText!=null) premieredText.setText(premiered);
            }
        }
    }

    @Override
    public void onFailedToLoad() {
        Toast.makeText(getContext().getApplicationContext(), "Error! Too many requests.", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().popBackStackImmediate();
    }
}
