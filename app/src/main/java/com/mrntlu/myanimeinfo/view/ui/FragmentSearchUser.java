package com.mrntlu.myanimeinfo.view.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.mrntlu.myanimeinfo.R;

public class FragmentSearchUser extends Fragment {

    public FragmentSearchUser() {
        // Required empty public constructor
    }

    public static FragmentSearchUser newInstance() {
        return new FragmentSearchUser();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search_user, container, false);
        SearchView searchView = v.findViewById(R.id.searchUser);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length()>=3) {
                    AppCompatActivity activity = (AppCompatActivity) getContext();
                    if (activity != null) {
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, FragmentUserInfo.newInstance(query)).addToBackStack(null).commit();
                    }
                } else{
                    Toast.makeText(getContext(), "Search length should be bigger than or equal to 3", Toast.LENGTH_SHORT).show();
                }
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
