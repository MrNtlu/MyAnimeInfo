package com.mrntlu.myanimeinfo.view.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import com.mrntlu.myanimeinfo.R;

public class FragmentSearchUser extends Fragment {

    private View v;
    private SearchView searchView;

    public FragmentSearchUser() {
        // Required empty public constructor
    }

    public static FragmentSearchUser newInstance() {
        FragmentSearchUser fragment = new FragmentSearchUser();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_search_user, container, false);
        searchView=v.findViewById(R.id.searchUser);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                AppCompatActivity activity=(AppCompatActivity)v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, FragmentUserInfo.newInstance(query)).addToBackStack(null).commit();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return v;
    }

}
