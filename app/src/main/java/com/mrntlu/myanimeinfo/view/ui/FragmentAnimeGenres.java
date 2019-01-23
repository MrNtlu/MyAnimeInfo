package com.mrntlu.myanimeinfo.view.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import com.mrntlu.myanimeinfo.R;

public class FragmentAnimeGenres extends Fragment {

    public FragmentAnimeGenres() {
        // Required empty public constructor
    }

    public static FragmentAnimeGenres newInstance() {
        FragmentAnimeGenres fragment = new FragmentAnimeGenres();
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_anime_genre, container, false);
        GridLayout gridLayout = v.findViewById(R.id.gridLayout);
        int childCount= gridLayout.getChildCount();

        for (int i=0;i<childCount;i++){
            CardView cardView=(CardView) gridLayout.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppCompatActivity activity=(AppCompatActivity)view.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, FragmentAnimeGenreList.newInstance(finalI+1)).addToBackStack(null).commit();
                }
            });
        }

        return v;
    }

}
