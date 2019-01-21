package com.mrntlu.myanimeinfo.view.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mrntlu.myanimeinfo.R;

public class InnerFragmentAnimeInfo extends Fragment {

    private View v;
    private String synopsis;
    private String background;

    public InnerFragmentAnimeInfo() {
        // Required empty public constructor
    }

    public static InnerFragmentAnimeInfo newInstance(String synopsis,String background) {
        InnerFragmentAnimeInfo fragment = new InnerFragmentAnimeInfo();
        fragment.synopsis=synopsis;
        fragment.background=background;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_inner_anime_info, container, false);
        TextView synopsisText=v.findViewById(R.id.synopsisText);
        TextView backgroundText=v.findViewById(R.id.backgroundText);
        synopsisText.setText(synopsis);
        backgroundText.setText(background);

        if (background==null){
            backgroundText.setText("No Background Info.");
        }

        return v;
    }
}
