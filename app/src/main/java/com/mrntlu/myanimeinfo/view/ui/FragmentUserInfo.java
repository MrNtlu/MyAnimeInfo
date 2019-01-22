package com.mrntlu.myanimeinfo.view.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.UserProfileResponseBody;
import com.mrntlu.myanimeinfo.view.OnUserInfoLoaded;
import com.mrntlu.myanimeinfo.view.adapter.UserInfoPagerAdapter;
import com.mrntlu.myanimeinfo.viewmodel.AnimeViewModel;

public class FragmentUserInfo extends Fragment implements OnUserInfoLoaded {

    private View v;
    private String username;
    private AnimeViewModel viewModel;
    private TextView usernameText;
    private ProgressBar userImageProgress;
    private Button animelistButton;
    private ImageView userImage;
    private ConstraintLayout loadingScreen;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private UserInfoPagerAdapter pagerAdapter;

    public FragmentUserInfo() {
        // Required empty public constructor
    }

    public static FragmentUserInfo newInstance(String username) {
        FragmentUserInfo fragment = new FragmentUserInfo();
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
        v=inflater.inflate(R.layout.fragment_user_info, container, false);
        usernameText=v.findViewById(R.id.usernameText);

        userImageProgress=v.findViewById(R.id.userImageProgress);
        animelistButton=v.findViewById(R.id.animelistButton);
        userImage=v.findViewById(R.id.userImage);
        loadingScreen=v.findViewById(R.id.loadingScreen);
        viewPager=v.findViewById(R.id.userViewPager);
        tabLayout=v.findViewById(R.id.userTabLayout);

        animelistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, FragmentUserAnimeList.newInstance(username)).addToBackStack(null).commit();
            }
        });

        viewModel= ViewModelProviders.of(getActivity()).get(AnimeViewModel.class);
        viewModel.getUserProfile(username,this);
        return v;
    }

    @Override
    public void onUserInfoLoaded(UserProfileResponseBody profileBody) {
        pagerAdapter=new UserInfoPagerAdapter(getChildFragmentManager(),profileBody);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        usernameText.setText(profileBody.getUsername());
        Glide.with(v).load(profileBody.getImage_url()).addListener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                //TODO NoPicture
                Log.d("testJSON", "onLoadFailed: "+e.getMessage());
                userImageProgress.setVisibility(View.GONE);
                loadingScreen.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                userImageProgress.setVisibility(View.GONE);
                loadingScreen.setVisibility(View.GONE);
                return false;
            }
        }).into(userImage);
    }

    @Override
    public void onUserNotFound() {
        //TODO Onusernotfound
        Toast.makeText(getContext(), "User Not Found", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().popBackStackImmediate();
    }
}
