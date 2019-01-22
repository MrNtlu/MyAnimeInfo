package com.mrntlu.myanimeinfo.view.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mrntlu.myanimeinfo.R;
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.UserProfileResponseBody;
import com.mrntlu.myanimeinfo.view.adapter.UserFavsAdapter;

public class InnerFragmentUserFavs extends Fragment {

    private View v;
    private int status;
    private UserProfileResponseBody.Favorites favorites;
    private RecyclerView favsRV;
    private UserFavsAdapter adapter;
    private TextView nothingHereText;

    public InnerFragmentUserFavs() {
        // Required empty public constructor
    }

    public static InnerFragmentUserFavs newInstance(UserProfileResponseBody.Favorites favorites,int status) {
        InnerFragmentUserFavs fragment = new InnerFragmentUserFavs();
        fragment.favorites=favorites;
        fragment.status=status;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_inner_user_favs, container, false);
        favsRV=v.findViewById(R.id.favsRV);
        nothingHereText=v.findViewById(R.id.nothingHereText);

        adapter=new UserFavsAdapter(status,getContext(),favorites);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),3);
        favsRV.setLayoutManager(gridLayoutManager);
        favsRV.setAdapter(adapter);

        if (adapter.getItemCount()==0){
            nothingHereText.setVisibility(View.VISIBLE);
        }
        return v;
    }

}
