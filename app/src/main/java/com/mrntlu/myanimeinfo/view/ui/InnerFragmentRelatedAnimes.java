package com.mrntlu.myanimeinfo.view.ui;


import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mrntlu.myanimeinfo.R;
import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeByID;
import com.mrntlu.myanimeinfo.view.adapter.InnerAnimeRelationsAdapter;

public class InnerFragmentRelatedAnimes extends Fragment {

    private View v;
    private GETAnimeByID.RelatedAnimes relatedAnimes;
    private ConstraintLayout othersLayout,prequelsLayout,sequelsLayout;
    private RecyclerView othersRV,prequelsRV,sequelsRV;
    private ImageButton expandButton,prequelExpand,sequelExpand;
    private InnerAnimeRelationsAdapter adapter;

    public InnerFragmentRelatedAnimes() {
        // Required empty public constructor
    }

    public static InnerFragmentRelatedAnimes newInstance(GETAnimeByID.RelatedAnimes relatedAnimes) {
        InnerFragmentRelatedAnimes fragment = new InnerFragmentRelatedAnimes();
        fragment.relatedAnimes=relatedAnimes;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_inner_related_animes, container, false);
        othersLayout =v.findViewById(R.id.adaptationsLayout);
        prequelsLayout=v.findViewById(R.id.prequelsLayout);
        sequelsLayout=v.findViewById(R.id.sequelsLayout);
        othersRV =v.findViewById(R.id.adaptationsRV);
        prequelsRV=v.findViewById(R.id.prequelsRV);
        sequelsRV=v.findViewById(R.id.sequelsRV);
        expandButton=v.findViewById(R.id.expandButton);
        prequelExpand=v.findViewById(R.id.prequelExpand);
        sequelExpand=v.findViewById(R.id.sequelExpand);
        TextView noRelationText=v.findViewById(R.id.noRelationText);

        if (relatedAnimes.getOther()==null){
            othersLayout.setVisibility(View.GONE);
        }else{
            adapter=new InnerAnimeRelationsAdapter(getContext(),relatedAnimes,0);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            othersRV.setLayoutManager(linearLayoutManager);
            othersRV.setAdapter(adapter);
        }

        if (relatedAnimes.getPrequel()==null){
            prequelsLayout.setVisibility(View.GONE);
        }else{
            adapter=new InnerAnimeRelationsAdapter(getContext(),relatedAnimes,1);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            prequelsRV.setLayoutManager(linearLayoutManager);
            prequelsRV.setAdapter(adapter);
        }

        if (relatedAnimes.getSequel()==null){
            sequelsLayout.setVisibility(View.GONE);
        }else{
            adapter=new InnerAnimeRelationsAdapter(getContext(),relatedAnimes,2);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            sequelsRV.setLayoutManager(linearLayoutManager);
            sequelsRV.setAdapter(adapter);
        }

        if (relatedAnimes.getOther()==null && relatedAnimes.getPrequel()==null && relatedAnimes.getSequel()==null){
            noRelationText.setVisibility(View.VISIBLE);
        }

        expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (othersRV.getVisibility()==View.VISIBLE){
                    othersRV.setVisibility(View.GONE);
                    expandButton.setImageResource(android.R.drawable.arrow_down_float);
                }else if (othersRV.getVisibility()==View.GONE){
                    othersRV.setVisibility(View.VISIBLE);
                    expandButton.setImageResource(android.R.drawable.arrow_up_float);
                }
            }
        });

        prequelExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prequelsRV.getVisibility()==View.VISIBLE){
                    prequelsRV.setVisibility(View.GONE);
                    prequelExpand.setImageResource(android.R.drawable.arrow_down_float);
                }else if (prequelsRV.getVisibility()==View.GONE){
                    prequelsRV.setVisibility(View.VISIBLE);
                    prequelExpand.setImageResource(android.R.drawable.arrow_up_float);
                }
            }
        });

        sequelExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sequelsRV.getVisibility()==View.VISIBLE){
                    sequelsRV.setVisibility(View.GONE);
                    sequelExpand.setImageResource(android.R.drawable.arrow_down_float);
                }else if (sequelsRV.getVisibility()==View.GONE){
                    sequelsRV.setVisibility(View.VISIBLE);
                    sequelExpand.setImageResource(android.R.drawable.arrow_up_float);
                }
            }
        });

        /*
                if (animeByIDBody.getRelated().getAdaptation()!=null) {
                    for (GETAnimeByID.RelatedAnimes.Adaptations adaptations : animeByIDBody.getRelated().getAdaptation()) {
                        Log.d(TAG, "Adaptations: " + adaptations.getName() + " " + adaptations.getMal_id());
                    }
                }

                if (animeByIDBody.getRelated().getOther()!=null) {
                    for (GETAnimeByID.RelatedAnimes.AnimeRelations others : animeByIDBody.getRelated().getOther()) {
                        Log.d(TAG, "AnimeRelations: " + others.getName() + " " + others.getMal_id());
                    }
                }

                if (animeByIDBody.getRelated().getSequel()!=null) {
                    for (GETAnimeByID.RelatedAnimes.Sequels sequels : animeByIDBody.getRelated().getSequel()) {
                        Log.d(TAG, "Sequels: " + sequels.getName() + " " + sequels.getMal_id());
                    }
                }*/

        return v;
    }
}
