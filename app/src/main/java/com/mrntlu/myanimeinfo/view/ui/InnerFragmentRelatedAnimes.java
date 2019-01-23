package com.mrntlu.myanimeinfo.view.ui;

import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.mrntlu.myanimeinfo.R;
import com.mrntlu.myanimeinfo.service.model.jsonbody.GETAnimeByID;
import com.mrntlu.myanimeinfo.view.adapter.InnerAnimeRelationsAdapter;

import java.lang.ref.WeakReference;

public class InnerFragmentRelatedAnimes extends Fragment {

    private GETAnimeByID.RelatedAnimes relatedAnimes;

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
        View v = inflater.inflate(R.layout.fragment_inner_related_animes, container, false);
        ConstraintLayout othersLayout = v.findViewById(R.id.adaptationsLayout);
        ConstraintLayout prequelsLayout = v.findViewById(R.id.prequelsLayout);
        ConstraintLayout sequelsLayout = v.findViewById(R.id.sequelsLayout);
        RecyclerView othersRV = v.findViewById(R.id.adaptationsRV);
        RecyclerView prequelsRV= v.findViewById(R.id.prequelsRV);
        RecyclerView sequelsRV= v.findViewById(R.id.sequelsRV);
        TextView noRelationText= v.findViewById(R.id.noRelationText);
        ImageButton expandButton= v.findViewById(R.id.expandButton);
        ImageButton prequelExpand= v.findViewById(R.id.prequelExpand);
        ImageButton sequelExpand= v.findViewById(R.id.sequelExpand);

        WeakReference<RecyclerView> weakOtherRV = new WeakReference<>(othersRV);
        WeakReference<RecyclerView> weakPrequelsRV = new WeakReference<>(prequelsRV);
        WeakReference<RecyclerView> weakSequels = new WeakReference<>(sequelsRV);

        WeakReference<ImageButton> weakExpandButton = new WeakReference<>(expandButton);
        WeakReference<ImageButton> weakPrequelExpand = new WeakReference<>(prequelExpand);
        WeakReference<ImageButton> weakSequelExpand = new WeakReference<>(sequelExpand);

        InnerAnimeRelationsAdapter adapter;
        if (relatedAnimes.getOther()==null){
            othersLayout.setVisibility(View.GONE);
        }else{
            adapter =new InnerAnimeRelationsAdapter(getContext(),relatedAnimes,0);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            othersRV.setLayoutManager(linearLayoutManager);
            othersRV.setAdapter(adapter);
        }

        if (relatedAnimes.getPrequel()==null){
            prequelsLayout.setVisibility(View.GONE);
        }else{
            adapter =new InnerAnimeRelationsAdapter(getContext(),relatedAnimes,1);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            prequelsRV.setLayoutManager(linearLayoutManager);
            prequelsRV.setAdapter(adapter);
        }

        if (relatedAnimes.getSequel()==null){
            sequelsLayout.setVisibility(View.GONE);
        }else{
            adapter =new InnerAnimeRelationsAdapter(getContext(),relatedAnimes,2);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            sequelsRV.setLayoutManager(linearLayoutManager);
            sequelsRV.setAdapter(adapter);
        }

        if (relatedAnimes.getOther()==null && relatedAnimes.getPrequel()==null && relatedAnimes.getSequel()==null){
            noRelationText.setVisibility(View.VISIBLE);
        }

        final RecyclerView weakOther = weakOtherRV.get();
        final RecyclerView weakPrequel= weakPrequelsRV.get();
        final RecyclerView weakSequel= weakSequels.get();
        final ImageButton weakExpButton=weakExpandButton.get();
        final ImageButton weakPreButton=weakPrequelExpand.get();
        final ImageButton weakSeButton=weakSequelExpand.get();

        if (weakExpButton!=null) {
            weakExpButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (weakOther != null) {
                        if (weakOther.getVisibility() == View.VISIBLE) {
                            weakOther.setVisibility(View.GONE);
                            weakExpButton.setImageResource(android.R.drawable.arrow_down_float);
                        } else if (weakOther.getVisibility() == View.GONE) {
                            weakOther.setVisibility(View.VISIBLE);
                            weakExpButton.setImageResource(android.R.drawable.arrow_up_float);
                        }
                    }
                }
            });
        }

        if (weakPreButton!=null) {
            weakPreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (weakPrequel != null) {
                        if (weakPrequel.getVisibility() == View.VISIBLE) {
                            weakPrequel.setVisibility(View.GONE);
                            weakPreButton.setImageResource(android.R.drawable.arrow_down_float);
                        } else if (weakPrequel.getVisibility() == View.GONE) {
                            weakPrequel.setVisibility(View.VISIBLE);
                            weakPreButton.setImageResource(android.R.drawable.arrow_up_float);
                        }
                    }
                }
            });
        }

        if (weakSeButton!=null) {
            weakSeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (weakSequel != null) {
                        if (weakSequel.getVisibility() == View.VISIBLE) {
                            weakSequel.setVisibility(View.GONE);
                            weakSeButton.setImageResource(android.R.drawable.arrow_down_float);
                        } else if (weakSequel.getVisibility() == View.GONE) {
                            weakSequel.setVisibility(View.VISIBLE);
                            weakSeButton.setImageResource(android.R.drawable.arrow_up_float);
                        }
                    }
                }
            });
        }

        return v;
    }
}
